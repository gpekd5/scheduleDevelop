package com.example.scheduledevelopproject.user.controller;

import com.example.scheduledevelopproject.auth.dto.SessionUserDto;
import com.example.scheduledevelopproject.global.exception.ForbiddenException;
import com.example.scheduledevelopproject.global.exception.UnauthorizedException;
import com.example.scheduledevelopproject.user.dto.*;
import com.example.scheduledevelopproject.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 사용자 관련 요청 처리 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * 사용자 생성 요청 처리
     *
     * @param request 사용자 생성 요청 정보
     * @return 사용자 생성 응답
     */
    @PostMapping
    public ResponseEntity<CreateUserResponseDto> createUser(@Valid @RequestBody CreateUserRequestDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    /**
     * 사용자 목록 조회 요청 처리
     *
     * @return 사용자 목록 조회 응답
     */
    @GetMapping
    public ResponseEntity<List<GetUserResponseDto>> findUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUsers());
    }

    /**
     * 사용자 단건 조회 요청 처리
     *
     * @param id 사용자 식별자
     * @return 사용자 조회 응답
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    /**
     * 사용자 수정 요청 처리
     *
     * @param id 사용자 식별자
     * @param request 사용자 수정 요청 정보
     * @param sessionUser 세션 로그인 사용자 정보
     * @return 사용자 수정 응답
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateUserResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequestDto request,
            @SessionAttribute(name="loginUser", required = false)SessionUserDto sessionUser) {

        if (sessionUser == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        if (!sessionUser.getId().equals(id)) {
            throw new ForbiddenException("본인만 수정할 수 있습니다.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id,request));
    }

    /**
     * 사용자 삭제 요청 처리
     *
     * @param id 사용자 식별자
     * @param request 사용자 삭제 요청 정보
     * @param sessionUser 세션 로그인 사용자 정보
     * @return 사용자 삭제 응답
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @Valid @RequestBody DeleteUserRequestDto request,
            @SessionAttribute(name="loginUser", required = false)SessionUserDto sessionUser) {

        if (sessionUser == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        if (!sessionUser.getId().equals(id)) {
            throw new ForbiddenException("본인만 삭제할 수 있습니다.");
        }

        userService.delete(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
