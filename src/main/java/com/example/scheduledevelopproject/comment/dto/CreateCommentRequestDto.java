package com.example.scheduledevelopproject.comment.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

/**
 * 댓글 생성 요청 데이터 전달 객체
 */
@Getter
public class CreateCommentRequestDto {

    @NotBlank(message = "댓글 내용은 필수입니다.")
    @Size(max = 200, message = "댓글 내용은 200자 이하여야 합니다.")
    private final String contents;

    public CreateCommentRequestDto(String contents) {
        this.contents = contents;
    }
}
