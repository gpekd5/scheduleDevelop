package com.example.scheduledevelopproject.User.controller;

import com.example.scheduledevelopproject.User.dto.*;
import com.example.scheduledevelopproject.User.service.UserService;
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
    public ResponseEntity<UpdateUserResponseDto> update(@PathVariable Long id, @RequestBody UpdateUserRequestDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody DeleteUserRequestDto request) {
        userService.delete(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
