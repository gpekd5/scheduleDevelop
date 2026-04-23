package com.example.scheduledevelopproject.schedule.dto;

import com.example.scheduledevelopproject.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 일정 생성 응답 데이터 전달 객체
 */
@Getter
public class CreateScheduleResponseDto {

    private final Long id;
    private final Long userId;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    /**
     * 일정 생성 응답 객체 생성자
     *
     * @param id 일정 식별자
     * @param userId 사용자 식별자
     * @param title 일정 제목
     * @param contents 일정 내용
     * @param createdAt 생성 일시
     * @param modifiedAt 수정 일시
     */
    public CreateScheduleResponseDto(Long id, Long userId, String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    /**
     * Schedule 엔티티 기반 CreateScheduleResponseDto 생성
     *
     * @param schedule 일정 엔티티
     * @return 일정 생성 응답 객체
     */
    public static CreateScheduleResponseDto from(Schedule schedule) {
        return new CreateScheduleResponseDto(
                schedule.getId(),
                schedule.getUser().getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }
}
