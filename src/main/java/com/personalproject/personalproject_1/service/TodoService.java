package com.personalproject.personalproject_1.service;

import com.personalproject.personalproject_1.dto.TodoRequestDto;
import com.personalproject.personalproject_1.dto.TodoResponseDto;
import com.personalproject.personalproject_1.entity.Todo;
import com.personalproject.personalproject_1.entity.User;
import com.personalproject.personalproject_1.exception.TodoNotFoundException;
import com.personalproject.personalproject_1.exception.UserNotMatchException;
import com.personalproject.personalproject_1.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MessageSource messageSource;

    public TodoResponseDto createTodo(TodoRequestDto requestDto, User user) {
        Todo todo = new Todo();
        todo.setContent(requestDto.getContent());
        todo.setTitle(requestDto.getTitle());
        todo.setIsComplete(false);
        todo.setUser(user);
        Todo saveTodo = todoRepository.save(todo);
        return new TodoResponseDto(saveTodo);
    }

    public TodoResponseDto getTodo(Long id) {
        return new TodoResponseDto(findTodo(id));
    }

    public Map<String, List<TodoResponseDto>> getTotalTodoList() {
        Map<String, List<TodoResponseDto>> usertodoMap = new HashMap<>();
        List<TodoResponseDto> todoList = todoRepository.findAllByOrderByCreatedAtDesc().stream().map(TodoResponseDto::new).toList();
        todoList.forEach(todoResponseDto -> {
            if (usertodoMap.containsKey(todoResponseDto.getUsername())) {
                usertodoMap.get(todoResponseDto.getUsername()).add(todoResponseDto);
            } else {
                usertodoMap.put(todoResponseDto.getUsername(), new ArrayList<>(List.of(todoResponseDto)));
            }
        });
        return usertodoMap;
    }


    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto, User user) {
        Todo todo = findTodo(id);
        if (matchUser(todo.getUser().getId(), user.getId())) {
            todo.update(requestDto);
        }
        return new TodoResponseDto(todo);
    }

    @Transactional
    public void updateComplete(Long id, User user) {
        Todo todo = findTodo(id);
        if (matchUser(todo.getUser().getId(), user.getId())) {
            todo.complete();
        }
    }

    public Todo findTodo(Long id) {
        return todoRepository.findById(id).orElseThrow(() ->
                new TodoNotFoundException(messageSource.getMessage(
                        "not.found.todo",
                        null,
                        "Not found Todo",
                        Locale.getDefault()
                ))
        );
    }

    public boolean matchUser(Long authorId, Long userId) {
        if (Objects.equals(authorId, userId)) {
            return true;
        } else {
            throw new UserNotMatchException(messageSource.getMessage(
                    "not.match.user",
                    null,
                    "Not match User",
                    Locale.getDefault()
            ));
        }
    }
}
