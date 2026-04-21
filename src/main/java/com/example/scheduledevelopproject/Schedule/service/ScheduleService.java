package com.example.scheduledevelopproject.Schedule.service;

import com.example.scheduledevelopproject.Schedule.dto.*;
import com.example.scheduledevelopproject.Schedule.entity.Schedule;
import com.example.scheduledevelopproject.Schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponseDto save(CreateScheduleRequestDto request) {
        Schedule schedule = new Schedule(
                request.getUserName(),
                request.getTitle(),
                request.getContents());

        Schedule saveSchedule = scheduleRepository.save(schedule);

        return CreateScheduleResponseDto.from(saveSchedule);
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponseDto> findSchedules() {
        return scheduleRepository.findAll().stream()
                .map(GetScheduleResponseDto::from)
                .toList();

    }

    @Transactional(readOnly = true)
    public GetScheduleResponseDto findById(Long scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("Schedule whit ID " + scheduleId + "not found.")
        );

        return GetScheduleResponseDto.from(schedule);
    }

    @Transactional
    public UpdateScheduleResponseDto update(Long scheduleId, UpdateScheduleRequestDto request) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("Schedule whit ID " + scheduleId + "not found.")
        );

        schedule.update(request.getTitle(), request.getContents());

        return UpdateScheduleResponseDto.from(schedule);
    }

    @Transactional
    public void delete(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("Schedule whit ID " + scheduleId + "not found.")
        );

        scheduleRepository.deleteById(scheduleId);
    }
}
