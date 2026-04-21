package com.example.scheduledevelopproject.Schedule.entity;

import com.example.scheduledevelopproject.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = false)
    private String userName;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 200, nullable = false)
    private String contents;

    public Schedule(String userName, String title, String contents) {
        this.userName = userName;
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
