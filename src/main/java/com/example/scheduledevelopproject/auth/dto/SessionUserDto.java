package com.example.scheduledevelopproject.auth.dto;

import com.example.scheduledevelopproject.user.entity.User;
import lombok.Getter;

/**
 * 세션 사용자 정보 전달 객체
 */
@Getter
public class SessionUserDto {

    private final Long id;
    private final String email;

    /**
     * 세션 사용자 정보 객체 생성자
     *
     * @param id 사용자 식별자
     * @param email 사용자 이메일
     */
    public SessionUserDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    /**
     * User 엔티티 기반 SessionUserDto 생성
     *
     * @param user 사용자 엔티티
     * @return 세션 사용자 정보 객체
     */
    public static SessionUserDto from(User user) {
        return new SessionUserDto(user.getId(), user.getEmail());
    }

}
