package com.example.scheduledevelopproject.Schedule.repository;

import com.example.scheduledevelopproject.Schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
