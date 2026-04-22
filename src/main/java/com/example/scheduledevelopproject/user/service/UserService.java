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

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    @Transactional(readOnly = true)
    public List<GetUserResponseDto> findUsers() {
        return userRepository.findAll().stream()
                .map(GetUserResponseDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public GetUserResponseDto findById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User whit ID " + userId + "not found.")
        );

        return GetUserResponseDto.from(user);
    }

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
