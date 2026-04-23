package com.example.scheduledevelopproject.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 전역 예외 처리 핸들러
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {

    /**
     * 서비스 예외 처리
     *
     * @param ex 서비스 예외 정보
     * @return 예외 응답
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> handleServiceException(ServiceException ex) {
        log.error("ServiceException: {}", ex.getMessage());
        return ResponseEntity
                .status(ex.getStatus())
                .body(ex.getMessage());
    }

    /**
     * 요청 값 검증 예외 처리
     *
     * @param ex 요청 값 검증 예외 정보
     * @return 예외 응답
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("입력 값이 올바르지 않습니다.");
        log.error("ValidationException: {}", errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
