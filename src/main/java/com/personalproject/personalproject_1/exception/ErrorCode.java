package com.personalproject.personalproject_1.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "해당 Post를 찾을수 없습니다.") ,
    DO_NOT_MATCH_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
