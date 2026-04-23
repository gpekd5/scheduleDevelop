package com.example.scheduledevelopproject.user.entity;

import com.example.scheduledevelopproject.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 엔티티
 */
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = false)
    private String userName;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(length = 100, nullable = false)
    private String password;

    /**
     * 사용자 객체 생성자
     *
     * @param userName 사용자명
     * @param email 이메일
     * @param password 비밀번호
     */
    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    /**
     * 사용자 정보 수정
     *
     * @param email 이메일
     */
    public void update(String email) {
        this.email = email;
    }
}
