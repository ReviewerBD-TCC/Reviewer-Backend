package com.reviewer.reviewer.controllers;

import com.reviewer.reviewer.dto.users.UserResponseDto;
import com.reviewer.reviewer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUserLogged(@AuthenticationPrincipal Jwt jwtToken){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.isInDatabase(new UserResponseDto(jwtToken)));
    }
}
