package com.example.scheduledevelopproject.User.service;

import com.example.scheduledevelopproject.User.dto.*;
import com.example.scheduledevelopproject.User.entity.User;
import com.example.scheduledevelopproject.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponseDto save(CreateUserRequestDto request) {
        User user = new User(
                request.getUserName(),
                request.getEmail()
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
                () -> new IllegalStateException("User whit ID " + userId + "not found.")
        );

        return GetUserResponseDto.from(user);
    }

    @Transactional
    public UpdateUserResponseDto update(Long userId, UpdateUserRequestDto request) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("User whit ID " + userId + "not found.")
        );

        user.update(request.getEmail());

        return UpdateUserResponseDto.from(user);
    }

    @Transactional
    public void delete(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("User whit ID " + userId + "not found.")
        );

        userRepository.deleteById(userId);

    }


}
