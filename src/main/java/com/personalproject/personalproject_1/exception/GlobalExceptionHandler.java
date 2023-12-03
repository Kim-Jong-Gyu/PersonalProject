package com.personalproject.personalproject_1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// json 형태로 반환하기 떄문에
// Class 단위 애노테이션
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({CommentNotFoundException.class})
    public ResponseEntity<RestApiException> commentNotFoundExceptionHandler(CommentNotFoundException ex){
        RestApiException restApiException = new RestApiException(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(
                restApiException,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({TodoNotFoundException.class})
    public ResponseEntity<RestApiException> todoNotFoundExceptionHandler(TodoNotFoundException ex){
        RestApiException restApiException = new RestApiException(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(
                restApiException,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({UserDuplicateException.class})
    public ResponseEntity<RestApiException> userDuplicateExceptionHandler(UserDuplicateException ex){
        RestApiException restApiException = new RestApiException(ex.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(
                restApiException,
                HttpStatus.CONFLICT
        );
    }
    @ExceptionHandler({UserNotMatchException.class})
    public ResponseEntity<RestApiException> userNotMatchExceptionHandler(UserNotMatchException ex){
        RestApiException restApiException = new RestApiException(ex.getMessage(), HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(
                restApiException,
                HttpStatus.FORBIDDEN
        );
    }
}
