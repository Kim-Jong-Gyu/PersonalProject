package com.personalproject.personalproject_1.service;

import com.personalproject.personalproject_1.dto.TodoRequestDto;
import com.personalproject.personalproject_1.dto.TodoResponseDto;
import com.personalproject.personalproject_1.entity.Todo;
import com.personalproject.personalproject_1.entity.User;
import com.personalproject.personalproject_1.exception.ErrorCode;
import com.personalproject.personalproject_1.exception.Exception;
import com.personalproject.personalproject_1.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

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
            if(usertodoMap.containsKey(todoResponseDto.getUsername())){
                usertodoMap.get(todoResponseDto.getUsername()).add(todoResponseDto);
            }
            else{
                usertodoMap.put(todoResponseDto.getUsername(), new ArrayList<>(List.of(todoResponseDto)));
            }
        });
        return usertodoMap;
    }


    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto, User user) {
        Todo todo = findTodo(id);
        if(Objects.equals(todo.getUser().getId(), user.getId())){
            todo.update(requestDto);
        }
        else {
            throw new Exception(ErrorCode.DO_NOT_MATCH_ID);
        }
        return new TodoResponseDto(todo);
    }
    @Transactional
    public void updateComplete(Long id, User user) {
        Todo todo = findTodo(id);
        if(Objects.equals(todo.getUser().getId(), user.getId())){
            todo.complete();
        }
        else {
            throw new Exception(ErrorCode.DO_NOT_MATCH_ID);
        }
    }

    public Todo findTodo(Long id) {
        return todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 Id To-do가 없습니다.")
        );
    }
}
