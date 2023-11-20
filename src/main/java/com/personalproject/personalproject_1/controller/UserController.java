package com.personalproject.personalproject_1.controller;

import com.personalproject.personalproject_1.dto.SignupRequestDto;
import com.personalproject.personalproject_1.dto.SignupResponseDto;
import com.personalproject.personalproject_1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto requestDto) {
        SignupResponseDto signupResponseDto = userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(signupResponseDto);
    }

}
