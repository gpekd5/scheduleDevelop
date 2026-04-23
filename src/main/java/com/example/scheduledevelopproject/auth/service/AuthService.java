package com.example.scheduledevelopproject.auth.service;

import com.example.scheduledevelopproject.auth.dto.LoginRequestDto;
import com.example.scheduledevelopproject.auth.dto.SessionUserDto;
import com.example.scheduledevelopproject.global.encoder.PasswordEncoder;
import com.example.scheduledevelopproject.global.exception.*;
import com.example.scheduledevelopproject.user.entity.User;
import com.example.scheduledevelopproject.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 인증 관련 비즈니스 로직 서비스
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인 요청 검증 및 세션 사용자 정보 반환
     *
     * @param request 로그인 요청 정보
     * @return 세션 사용자 정보
     */
    @Transactional
    public SessionUserDto login(@Valid LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new UnauthorizedException("이메일 또는 비밀번호가 일치하지 않습니다.")
        );

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        return SessionUserDto.from(user);
    }
}
