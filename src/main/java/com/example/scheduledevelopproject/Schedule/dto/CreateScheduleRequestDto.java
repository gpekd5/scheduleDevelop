package com.example.scheduledevelopproject.Schedule.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private final String userName;
    private final String title;
    private final String contents;

    public CreateScheduleRequestDto(String userName, String title, String contents) {
        this.userName = userName;
        this.title = title;
        this.contents = contents;
    }
}
