package com.example.scheduledevelopproject.comment.entity;

import com.example.scheduledevelopproject.global.entity.BaseEntity;
import com.example.scheduledevelopproject.schedule.entity.Schedule;
import com.example.scheduledevelopproject.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(length = 200, nullable = false)
    private String contents;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    /**
     * 댓글 객체 생성자
     *
     * @param contents 댓글 내용
     * @param user 작성자 정보
     * @param schedule 일정 정보
     */
    public Comment(String contents, User user, Schedule schedule) {
        this.contents = contents;
        this.user = user;
        this.schedule = schedule;
    }

    /**
     * 댓글 내용 수정
     *
     * @param contents 댓글 내용
     */
    public void update(String contents) {
        this.contents = contents;
    }

}
