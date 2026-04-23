package com.example.scheduledevelopproject.user.service;

import com.example.scheduledevelopproject.global.encoder.PasswordEncoder;
import com.example.scheduledevelopproject.global.exception.*;
import com.example.scheduledevelopproject.user.dto.*;
import com.example.scheduledevelopproject.user.entity.User;
import com.example.scheduledevelopproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 사용자 관련 비즈니스 로직 서비스
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 사용자 저장 처리
     *
     * @param request 사용자 생성 요청 정보
     * @return 사용자 생성 응답 정보
     */
    @Transactional
    public CreateUserResponseDto save(CreateUserRequestDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("이미 사용 중인 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getUserName(),
                request.getEmail(),
                encodedPassword
        );

        User saveUser = userRepository.save(user);

        return CreateUserResponseDto.from(saveUser);
    }

    /**
     * 사용자 목록 조회
     *
     * @return 사용자 목록 응답 정보
     */
    @Transactional(readOnly = true)
    public List<GetUserResponseDto> findUsers() {
        return userRepository.findAll().stream()
                .map(GetUserResponseDto::from)
                .toList();
    }

    /**
     * 사용자 단건 조회
     *
     * @param userId 사용자 식별자
     * @return 사용자 조회 응답 정보
     */
    @Transactional(readOnly = true)
    public GetUserResponseDto findById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User whit ID " + userId + "not found.")
        );

        return GetUserResponseDto.from(user);
    }

    /**
     * 사용자 정보 수정 처리
     *
     * @param userId  사용자 식별자
     * @param request 사용자 수정 요청 정보
     * @return 사용자 수정 응답 정보
     */
    @Transactional
    public UpdateUserResponseDto update(Long userId, UpdateUserRequestDto request) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User whit ID " + userId + "not found.")
        );

        if (!user.getPassword().equals(request.getPassword())) {
            throw new BadRequestException("비밀번호가 일치하지 않습니다.");
        }

        user.update(request.getEmail());

        return UpdateUserResponseDto.from(user);
    }

    /**
     * 사용자 삭제 처리
     *
     * @param userId  사용자 식별자
     * @param request 사용자 삭제 요청 정보
     */
    @Transactional
    public void delete(Long userId, DeleteUserRequestDto request) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User whit ID " + userId + "not found.")
        );

        if (!user.getPassword().equals(request.getPassword())) {
            throw new BadRequestException("비밀번호가 일치하지 않습니다.");
        }

        userRepository.deleteById(userId);
    }
}
