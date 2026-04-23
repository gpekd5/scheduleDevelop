package com.example.scheduledevelopproject.auth.controller;

import com.example.scheduledevelopproject.auth.dto.LoginRequestDto;
import com.example.scheduledevelopproject.auth.dto.SessionUserDto;
import com.example.scheduledevelopproject.auth.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * 인증 관련 요청을 처리하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 로그인 요청 처리
     *
     * @param request 로그인 요청 정보
     * @param session 현재 HTTP 세션
     * @return 로그인 성공 응답
     */
    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequestDto request, HttpSession session) {
        SessionUserDto sessionUser = authService.login(request);
        session.setAttribute("loginUser", sessionUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 로그아웃 요청 처리
     *
     * @param sessionUser 세션 로그인 사용자 정보
     * @param session 현재 HTTP 세션
     * @return 로그아웃 결과 응답
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @SessionAttribute(name = "loginUser", required = false) SessionUserDto sessionUser,
            HttpSession session) {
        if (sessionUser == null) {
            return ResponseEntity.badRequest().build();
        }
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
