package com.example.scheduledevelopproject.schedule.service;

import com.example.scheduledevelopproject.schedule.dto.*;
import com.example.scheduledevelopproject.schedule.entity.Schedule;
import com.example.scheduledevelopproject.schedule.repository.ScheduleRepository;
import com.example.scheduledevelopproject.user.entity.User;
import com.example.scheduledevelopproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateScheduleResponseDto save(CreateScheduleRequestDto request) {

        if (request.getUserId() == null) {
            throw new IllegalArgumentException("User ID not found.");
        }

        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalStateException("User ID " + request.getUserId() + " not found.")
        );

        Schedule schedule = new Schedule(
                user,
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
