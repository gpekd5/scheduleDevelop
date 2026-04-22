package com.example.scheduledevelopproject.schedule.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {

    @NotBlank(message = "일정 제목은 필수입니다.")
    @Size(max = 50, message = "일정 제목은 50자 이하여야 합니다.")
    private final String title;
    @NotBlank(message = "일정 내용은 필수입니다.")
    @Size(max = 200, message = "일정 내용은 200자 이하여야 합니다.")
    private final String contents;

    public UpdateScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }


}
