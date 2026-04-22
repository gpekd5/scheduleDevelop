package com.example.scheduledevelopproject.comment.dto;

import com.example.scheduledevelopproject.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentResponseDto {

    private final Long id;
    private final String contents;
    private final Long userId;
    private final Long scheduleId;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetCommentResponseDto(
            Long id,
            String contents,
            Long userId,
            Long scheduleId,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.contents = contents;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static GetCommentResponseDto from(Comment comment) {
        return new GetCommentResponseDto(
                comment.getId(),
                comment.getContents(),
                comment.getUser().getId(),
                comment.getSchedule().getId(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
