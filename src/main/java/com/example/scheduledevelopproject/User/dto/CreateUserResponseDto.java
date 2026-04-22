package com.example.scheduledevelopproject.User.dto;

import com.example.scheduledevelopproject.User.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateUserResponseDto {

    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateUserResponseDto(Long id, String userName, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static CreateUserResponseDto from(User user) {
        return new CreateUserResponseDto(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );

    }

}
