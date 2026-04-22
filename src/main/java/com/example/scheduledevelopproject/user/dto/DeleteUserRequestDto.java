package com.example.scheduledevelopproject.user.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class DeleteUserRequestDto {

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 100, message = "비밀번호는 8자 이상 100자 이하여야 합니다.")
    private final String password;

    public DeleteUserRequestDto(String password) {
        this.password = password;
    }
}
