package com.example.scheduledevelopproject.schedule.repository;

import com.example.scheduledevelopproject.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 일정 데이터 접근 리포지토리
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> { }
