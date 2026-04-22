package com.example.scheduledevelopproject.User.entity;

import com.example.scheduledevelopproject.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Column(length = 100, nullable = false)
    private String email;
    @Column(length = 100, nullable = false)
    private String password;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void update(String email) {
        this.email = email;
    }
}
