package com.example.scheduledevelopproject.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 서비스 공통 예외
 */
@Getter
public class ServiceException extends RuntimeException {

    private final HttpStatus status;

    public ServiceException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
