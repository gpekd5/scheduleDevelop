package com.example.scheduledevelopproject.comment.controller;


import com.example.scheduledevelopproject.comment.dto.CreateCommentRequestDto;
import com.example.scheduledevelopproject.comment.dto.CreateCommentResponseDto;
import com.example.scheduledevelopproject.comment.dto.GetCommentResponseDto;
import com.example.scheduledevelopproject.comment.service.CommentService;
import com.example.scheduledevelopproject.schedule.dto.CreateScheduleRequestDto;
import com.example.scheduledevelopproject.schedule.dto.CreateScheduleResponseDto;
import com.example.scheduledevelopproject.schedule.dto.GetScheduleResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules/{scheduleId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CreateCommentResponseDto> createComment(
            @PathVariable Long scheduleId,
            @Valid @RequestBody CreateCommentRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(scheduleId, request));
    }


    @GetMapping
    public ResponseEntity<List<GetCommentResponseDto>> findComments(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findComments(scheduleId));
    }
}
