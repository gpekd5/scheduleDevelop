package com.example.scheduledevelopproject.user.dto;

import com.example.scheduledevelopproject.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 사용자 조회 응답 데이터 전달 객체
 */
@Getter
public class GetUserResponseDto {

    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    /**
     * 사용자 조회 응답 객체 생성자
     *
     * @param id 사용자 식별자
     * @param userName 사용자명
     * @param email 이메일
     * @param createdAt 생성 일시
     * @param modifiedAt 수정 일시
     */
    public GetUserResponseDto(Long id, String userName, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    /**
     * User 엔티티 기반 GetUserResponseDto 생성
     *
     * @param user 사용자 엔티티
     * @return 사용자 조회 응답 객체
     */
    public static GetUserResponseDto from(User user) {
        return new GetUserResponseDto(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );

    }
}
