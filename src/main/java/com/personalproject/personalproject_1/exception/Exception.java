package com.personalproject.personalproject_1.exception;

public class Exception extends RuntimeException{
    private final ErrorCode errorCode;

    public Exception(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
