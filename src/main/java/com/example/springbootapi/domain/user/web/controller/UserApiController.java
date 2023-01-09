package com.example.springbootapi.domain.user.web.controller;

import com.example.springbootapi.domain.user.entity.User;
import com.example.springbootapi.domain.user.service.UserService;
import com.example.springbootapi.domain.user.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @GetMapping("/greeting")
    public String greeting() {
        return "test";
    }

    @PostMapping("/sign-up")
    public void userSignUp(
            @RequestBody UserDto dto
            ) {

        User user = userService.userSignUp(dto.toEntity());
        userService.addAuthority(user.getId(), "ROLE_USER");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.login(userDto));
    }

}


