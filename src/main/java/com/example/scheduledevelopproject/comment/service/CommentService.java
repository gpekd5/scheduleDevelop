package com.example.scheduledevelopproject.comment.service;

import com.example.scheduledevelopproject.comment.dto.*;
import com.example.scheduledevelopproject.comment.entity.Comment;
import com.example.scheduledevelopproject.comment.repository.CommentRepository;
import com.example.scheduledevelopproject.global.exception.NotFoundException;
import com.example.scheduledevelopproject.schedule.entity.Schedule;
import com.example.scheduledevelopproject.schedule.repository.ScheduleRepository;
import com.example.scheduledevelopproject.user.entity.User;
import com.example.scheduledevelopproject.user.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateCommentResponseDto save(Long scheduleId,CreateCommentRequestDto request) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new NotFoundException("존재하지 않는 일정입니다."));

        User user = userRepository.findById(schedule.getUser().getId()).orElseThrow(
                () -> new NotFoundException("존재하지 않는 유저입니다."));

        Comment comment = new Comment(request.getContents(), user, schedule);
        Comment savedComment = commentRepository.save(comment);
        return CreateCommentResponseDto.from(savedComment);
    }

    @Transactional(readOnly = true )
    public List<GetCommentResponseDto> findComments(Long scheduleId) {
        return commentRepository.findAllByScheduleId(scheduleId)
                .stream().map(GetCommentResponseDto::from)
                .toList();
    }
}
