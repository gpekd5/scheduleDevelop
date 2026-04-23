package com.example.scheduledevelopproject.schedule.controller;

import com.example.scheduledevelopproject.auth.dto.SessionUserDto;
import com.example.scheduledevelopproject.global.exception.ForbiddenException;
import com.example.scheduledevelopproject.global.exception.UnauthorizedException;
import com.example.scheduledevelopproject.schedule.dto.*;
import com.example.scheduledevelopproject.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 일정 관련 요청 처리 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 일정 생성 요청 처리
     *
     * @param request 일정 생성 요청 정보
     * @return 일정 생성 응답
     */
    @PostMapping
    public ResponseEntity<CreateScheduleResponseDto> createSchedule(
            @Valid @RequestBody CreateScheduleRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

//    /**
//     * 일정 목록 조회 요청 처리
//     *
//     * @return 일정 목록 조회 응답
//     */
//    @GetMapping
//    public ResponseEntity<List<GetScheduleResponseDto>> findSchedules() {
//        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findSchedules());
//    }

    /**
     * 일정 단건 조회 요청 처리
     *
     * @param id 일정 식별자
     * @return 일정 조회 응답
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetScheduleResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findById(id));
    }

    /**
     * 일정 수정 요청 처리
     *
     * @param id 일정 식별자
     * @param request 일정 수정 요청 정보
     * @param sessionUser 세션 로그인 사용자 정보
     * @return 일정 수정 응답
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateScheduleResponseDto> UpdateSchedule(
            @PathVariable Long id,
            @Valid @RequestBody UpdateScheduleRequestDto request,
            @SessionAttribute(name = "loginUser", required = false) SessionUserDto sessionUser) {

        if (sessionUser == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        if (!sessionUser.getId().equals(id)) {
            throw new ForbiddenException("본인만 수정할 수 있습니다.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(id, request));
    }

    /**
     * 일정 삭제 요청 처리
     *
     * @param id 일정 식별자
     * @param sessionUser 세션 로그인 사용자 정보
     * @return 일정 삭제 응답
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @SessionAttribute(name = "loginUser", required = false) SessionUserDto sessionUser) {

        if (sessionUser == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        if (!sessionUser.getId().equals(id)) {
            throw new ForbiddenException("본인만 삭제할 수 있습니다.");
        }

        scheduleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 일정 목록 페이징 조회 요청 처리
     *
     * @param page 페이지 번호
     * @param size 페이지 크기
     * @return 일정 목록 조회 응답
     */
    @GetMapping
    public ResponseEntity<Page<GetScheduleResponseDto>> getSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("modifiedAt").descending()
        );

        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getSchedules(pageable));
    }


}
