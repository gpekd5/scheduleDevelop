package com.example.scheduledevelopproject.global.exception;

import org.springframework.http.HttpStatus;

/**
 * 접근 권한 없음 예외
 */
public class ForbiddenException extends ServiceException{
    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
