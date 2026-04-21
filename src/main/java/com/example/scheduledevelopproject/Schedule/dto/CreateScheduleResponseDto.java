package com.example.scheduledevelopproject.Schedule.dto;

import com.example.scheduledevelopproject.Schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleResponseDto {

    private final Long id;
    private final String userName;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateScheduleResponseDto(Long id, String userName, String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static CreateScheduleResponseDto from(Schedule schedule) {
        return new CreateScheduleResponseDto(
                schedule.getId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }
}
