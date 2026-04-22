package com.example.scheduledevelopproject.auth.service;

import com.example.scheduledevelopproject.auth.dto.LoginRequestDto;
import com.example.scheduledevelopproject.auth.dto.SessionUserDto;
import com.example.scheduledevelopproject.user.entity.User;
import com.example.scheduledevelopproject.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public SessionUserDto login(@Valid LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalStateException("이메일 또는 비밀번호가 일치하지 않습니다.")
        );

        if (!request.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        return SessionUserDto.from(user);
    }
}
