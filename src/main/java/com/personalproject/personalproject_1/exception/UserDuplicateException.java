package com.personalproject.personalproject_1.exception;

public class UserDuplicateException extends RuntimeException{
    public UserDuplicateException(String message){
        super(message);
    }
}
