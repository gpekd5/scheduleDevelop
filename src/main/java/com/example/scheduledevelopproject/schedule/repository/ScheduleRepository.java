package com.example.scheduledevelopproject.schedule.repository;

import com.example.scheduledevelopproject.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
