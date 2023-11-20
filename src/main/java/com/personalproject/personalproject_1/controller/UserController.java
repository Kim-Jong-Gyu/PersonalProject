package com.personalproject.personalproject_1.controller;

import com.personalproject.personalproject_1.dto.CommonResponseDto;
import com.personalproject.personalproject_1.dto.LoginRequestDto;
import com.personalproject.personalproject_1.dto.SignupRequestDto;
import com.personalproject.personalproject_1.dto.SignupResponseDto;
import com.personalproject.personalproject_1.service.UserService;
import com.personalproject.personalproject_1.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto> Login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res){
        try{
            userService.login(requestDto);
        } catch (Exception e){
            return null;
        }
        res.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(requestDto.getUsername()));
        return ResponseEntity.ok().body(new CommonResponseDto("로그인 성공", HttpStatus.OK.value()));
    }
}
