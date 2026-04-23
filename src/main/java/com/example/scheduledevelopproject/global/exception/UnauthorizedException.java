package com.example.scheduledevelopproject.global.exception;

import org.springframework.http.HttpStatus;

/**
 * 인증 실패 예외
 */
public class UnauthorizedException extends ServiceException {
    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}