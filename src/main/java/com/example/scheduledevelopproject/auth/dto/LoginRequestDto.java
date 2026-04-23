package com.example.scheduledevelopproject.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 로그인 요청 데이터 전달 객체
 */
@Getter
public class LoginRequestDto {

    @NotBlank
    private final String email;
    @NotBlank
    private final String password;

    /**
     * 로그인 요청 객체 생성자
     *
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     */
    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
