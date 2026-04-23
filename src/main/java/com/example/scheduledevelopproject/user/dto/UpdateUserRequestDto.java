package com.example.scheduledevelopproject.user.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

/**
 * 사용자 수정 요청 데이터 전달 객체
 */
@Getter
public class UpdateUserRequestDto {

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    @Size(max = 100, message = "이메일은 100자 이하여야 합니다.")
    private final String email;
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 100, message = "비밀번호는 8자 이상 100자 이하여야 합니다.")
    private final String password;

    public UpdateUserRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
