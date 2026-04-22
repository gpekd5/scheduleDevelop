package com.example.scheduledevelopproject.global.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ServiceException{
    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
