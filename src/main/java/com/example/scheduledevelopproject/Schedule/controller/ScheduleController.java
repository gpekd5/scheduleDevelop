package com.example.scheduledevelopproject.Schedule.controller;

import com.example.scheduledevelopproject.Schedule.dto.*;
import com.example.scheduledevelopproject.Schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponseDto> createSchedule(@RequestBody CreateScheduleRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponseDto>> findSchedules() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findSchedules());
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<GetScheduleResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findById(id));
    }

    @PatchMapping("/schedules/{id}")
    public ResponseEntity<UpdateScheduleResponseDto> UpdateSchedule(
            @PathVariable Long id,
            @RequestBody UpdateScheduleRequestDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(id, request));
    }


    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
