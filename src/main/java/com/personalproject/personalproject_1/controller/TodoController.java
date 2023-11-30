package com.personalproject.personalproject_1.controller;


import com.personalproject.personalproject_1.dto.TodoRequestDto;
import com.personalproject.personalproject_1.dto.TodoResponseDto;
import com.personalproject.personalproject_1.exception.Exception;
import com.personalproject.personalproject_1.exception.ExceptionResponseDto;
import com.personalproject.personalproject_1.impl.UserDetailsImpl;
import com.personalproject.personalproject_1.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    //To do 만들기
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody TodoRequestDto requestDto){
        TodoResponseDto todoResponseDto = todoService.createTodo(requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(todoResponseDto);
    }

    // 특정 To do 가져오기
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable Long todoId) {
        TodoResponseDto todoResponseDto = todoService.getTodo(todoId);
        return ResponseEntity.ok(todoResponseDto);
    }
    // 전체 To do List가져오기
    @GetMapping("/total")
    public ResponseEntity<Map<String, List<TodoResponseDto>>> getTotalTodoList(){
        Map<String, List<TodoResponseDto>> todoResponseDto = todoService.getTotalTodoList();
        return ResponseEntity.ok(todoResponseDto);
    }

    // 유저의 특정 todo 수정
    @PatchMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> updateTodo(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long todoId, @RequestBody TodoRequestDto requestDto){
        TodoResponseDto todoResponseDto = todoService.updateTodo(todoId,requestDto, userDetails.getUser());
        return ResponseEntity.ok(todoResponseDto);
    }

    // todo 완료로 변경
    @PatchMapping("/completed/{todoId}")
    public ResponseEntity<Void> updateComplete(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long todoId){
        todoService.updateComplete(todoId, userDetails.getUser());
        return ResponseEntity.ok().build();
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> exceptionHandler(Exception e){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(e.getErrorCode());
        return ResponseEntity.status(exceptionResponseDto.getStatus()).body(exceptionResponseDto);
    }
}
