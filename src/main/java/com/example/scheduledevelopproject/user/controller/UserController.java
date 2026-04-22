package com.example.scheduledevelopproject.user.controller;

import com.example.scheduledevelopproject.auth.dto.SessionUserDto;
import com.example.scheduledevelopproject.user.dto.*;
import com.example.scheduledevelopproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateUserRequestDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    @GetMapping
    public ResponseEntity<List<GetUserResponseDto>> findUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateUserResponseDto> update(
            @PathVariable Long id,
            @RequestBody UpdateUserRequestDto request,
            @SessionAttribute(name="loginUser", required = false)SessionUserDto sessionUser) {

        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        if (!sessionUser.getId().equals(id)) {
            throw new IllegalStateException("본인만 수정할 수 있습니다.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @RequestBody DeleteUserRequestDto request,
            @SessionAttribute(name="loginUser", required = false)SessionUserDto sessionUser) {

        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        if (!sessionUser.getId().equals(id)) {
            throw new IllegalStateException("본인만 수정할 수 있습니다.");
        }

        userService.delete(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
