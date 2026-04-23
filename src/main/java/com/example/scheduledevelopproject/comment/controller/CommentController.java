package com.example.scheduledevelopproject.comment.controller;


import com.example.scheduledevelopproject.comment.dto.CreateCommentRequestDto;
import com.example.scheduledevelopproject.comment.dto.CreateCommentResponseDto;
import com.example.scheduledevelopproject.comment.dto.GetCommentResponseDto;
import com.example.scheduledevelopproject.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 댓글 관련 요청 처리 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules/{scheduleId}/comments")
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 생성 요청 처리
     *
     * @param scheduleId 일정 식별자
     * @param request 댓글 생성 요청 정보
     * @return 댓글 생성 응답
     */
    @PostMapping
    public ResponseEntity<CreateCommentResponseDto> createComment(
            @PathVariable Long scheduleId,
            @Valid @RequestBody CreateCommentRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(scheduleId, request));
    }


    /**
     * 댓글 목록 조회 요청 처리
     *
     * @param scheduleId 일정 식별자
     * @return 댓글 목록 응답
     */
    @GetMapping
    public ResponseEntity<List<GetCommentResponseDto>> findComments(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findComments(scheduleId));
    }
}
