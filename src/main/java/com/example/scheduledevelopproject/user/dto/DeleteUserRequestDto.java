package com.example.scheduledevelopproject.user.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

/**
 * 사용자 삭제 요청 데이터 전달 객체
 */
@Getter
public class DeleteUserRequestDto {

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 100, message = "비밀번호는 8자 이상 100자 이하여야 합니다.")
    private final String password;

    /**
     * 사용자 삭제 요청 객체 생성자
     *
     * @param password 비밀번호
     */
    public DeleteUserRequestDto(String password) {
        this.password = password;
    }
}
