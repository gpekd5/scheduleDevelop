package com.example.scheduledevelopproject.Schedule.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {

    private final Long userId;
    private final String title;
    private final String contents;

    public UpdateScheduleRequestDto(Long userId, String title, String contents) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
    }


}
