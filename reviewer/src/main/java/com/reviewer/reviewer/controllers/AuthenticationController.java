package com.reviewer.reviewer.controllers;


import com.reviewer.reviewer.dto.users.LoginDto;
import com.reviewer.reviewer.dto.users.LoginResponseDTO;
import com.reviewer.reviewer.dto.users.RegisterDto;
import com.reviewer.reviewer.dto.users.RegisterResponse;
import com.reviewer.reviewer.infra.security.service.TokenService;
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
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(tokenService.login(loginDto)));
    }

    @PostMapping(name = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponse(tokenService.register(registerDto)));
    }


}
