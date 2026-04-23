package com.example.scheduledevelopproject.schedule.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

/**
 * 일정 수정 요청 데이터 전달 객체
 */
@Getter
public class UpdateScheduleRequestDto {

    @NotBlank(message = "일정 제목은 필수입니다.")
    @Size(max = 50, message = "일정 제목은 50자 이하여야 합니다.")
    private final String title;
    @NotBlank(message = "일정 내용은 필수입니다.")
    @Size(max = 200, message = "일정 내용은 200자 이하여야 합니다.")
    private final String contents;

    /**
     * 일정 수정 요청 객체 생성자
     *
     * @param title 일정 제목
     * @param contents 일정 내용
     */
    public UpdateScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }


}
