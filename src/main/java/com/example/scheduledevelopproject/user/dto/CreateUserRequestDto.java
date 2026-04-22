package com.example.scheduledevelopproject.user.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class CreateUserRequestDto {

    @NotBlank(message = "유저명은 필수입니다.")
    @Size(max = 10, message = "유저명은 10자 이하여야 합니다.")
    private final String userName;
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    @Size(max = 100, message = "이메일은 100자 이하여야 합니다.")
    private final String email;
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 100, message = "비밀번호는 8자 이상 100자 이하여야 합니다.")
    private final String password;

    public CreateUserRequestDto(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
