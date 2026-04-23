package com.example.scheduledevelopproject.global.exception;

import org.springframework.http.HttpStatus;

/**
 * 리소스 조회 실패 예외
 */
public class NotFoundException extends ServiceException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}