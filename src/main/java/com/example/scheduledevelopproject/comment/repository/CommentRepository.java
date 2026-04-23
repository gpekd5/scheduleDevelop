package com.example.scheduledevelopproject.comment.repository;

import com.example.scheduledevelopproject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 댓글 데이터 접근 리포지토리
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByScheduleId(Long scheduleId);
}
