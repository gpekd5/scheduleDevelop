package com.example.scheduledevelopproject.User.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    private final String email;

    public UpdateUserRequestDto(String email) {
        this.email = email;
    }
}
