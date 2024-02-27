package com.reviewer.reviewer.controllers;


import com.reviewer.reviewer.dto.users.LoginDto;
import com.reviewer.reviewer.dto.users.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "auth")
public class AuthenticationController {
    @Autowired
    private TokenService tokenService;

    @PostMapping(name = "/login")
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.status(HttpStatus.OK).body(tokenService.login(loginDto));
    }

    @PostMapping(name = "/register")
    public ResponseEntity<LoginDto> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(tokenService.register(registerDto));
    }


}
