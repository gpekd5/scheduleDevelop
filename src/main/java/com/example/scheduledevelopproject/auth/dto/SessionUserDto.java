package com.example.scheduledevelopproject.auth.dto;

import com.example.scheduledevelopproject.user.entity.User;
import lombok.Getter;

@Getter
public class SessionUserDto {

    private final Long id;
    private final String email;

    public SessionUserDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static SessionUserDto from(User user) {
        return new SessionUserDto(user.getId(), user.getEmail());
    }

}
