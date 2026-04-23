package com.example.scheduledevelopproject.global.exception;

import org.springframework.http.HttpStatus;

/**
 * 잘못된 요청 예외
 */
public class BadRequestException extends ServiceException {
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}