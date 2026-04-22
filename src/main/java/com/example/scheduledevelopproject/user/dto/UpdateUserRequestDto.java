package com.example.scheduledevelopproject.user.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    private final String email;
    private final String password;

    public UpdateUserRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
