package com.example.scheduledevelopproject.schedule.controller;

import com.example.scheduledevelopproject.auth.dto.SessionUserDto;
import com.example.scheduledevelopproject.global.exception.ForbiddenException;
import com.example.scheduledevelopproject.global.exception.UnauthorizedException;
import com.example.scheduledevelopproject.schedule.dto.*;
import com.example.scheduledevelopproject.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<CreateScheduleResponseDto> createSchedule(
            @Valid @RequestBody CreateScheduleRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    @GetMapping
    public ResponseEntity<List<GetScheduleResponseDto>> findSchedules() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findSchedules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetScheduleResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateScheduleResponseDto> UpdateSchedule(
            @PathVariable Long id,
            @Valid @RequestBody UpdateScheduleRequestDto request,
            @SessionAttribute(name="loginUser", required = false) SessionUserDto sessionUser) {

        if (sessionUser == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        if (!sessionUser.getId().equals(id)) {
            throw new ForbiddenException("본인만 수정할 수 있습니다.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @SessionAttribute(name="loginUser", required = false)SessionUserDto sessionUser) {

        if (sessionUser == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        if (!sessionUser.getId().equals(id)) {
            throw new ForbiddenException("본인만 삭제할 수 있습니다.");
        }

        scheduleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
