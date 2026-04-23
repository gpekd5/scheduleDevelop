package com.example.scheduledevelopproject.comment.dto;

import com.example.scheduledevelopproject.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 댓글 생성 응답 데이터 전달 객체
 */
@Getter
public class CreateCommentResponseDto {

    private final Long id;
    private final String contents;
    private final Long userId;
    private final Long scheduleId;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    /**
     * 댓글 생성 응답 객체 생성자
     *
     * @param id 댓글 식별자
     * @param contents 댓글 내용
     * @param userId 작성자 식별자
     * @param scheduleId 일정 식별자
     * @param createdAt 댓글 생성일시
     * @param modifiedAt 댓글 수정일시
     */
    public CreateCommentResponseDto(
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

    /**
     * Comment 엔티티 기반 CreateCommentResponseDto 생성
     *
     * @param comment 댓글 엔티티
     * @return 댓글 생성 응답 객체
     */
    public static CreateCommentResponseDto from(Comment comment) {
        return new CreateCommentResponseDto(
                comment.getId(),
                comment.getContents(),
                comment.getUser().getId(),
                comment.getSchedule().getId(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
