package com.example.scheduledevelopproject.schedule.entity;

import com.example.scheduledevelopproject.user.entity.User;
import com.example.scheduledevelopproject.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 일정 엔티티
 */
@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 200, nullable = false)
    private String contents;

    public Schedule(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    public void update(String title, String contents) {

        if (title != null) {
            this.title = title;
        }

        if (contents != null) {
            this.contents = contents;
        }
    }
}
