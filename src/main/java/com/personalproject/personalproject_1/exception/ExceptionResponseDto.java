package com.personalproject.personalproject_1.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class ExceptionResponseDto {
    private final HttpStatus status;
    private final String message;

    public ExceptionResponseDto(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
    }

}
