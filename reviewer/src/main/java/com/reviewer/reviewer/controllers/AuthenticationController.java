package com.reviewer.reviewer.controllers;


import com.reviewer.reviewer.dto.users.LoginDto;
import com.reviewer.reviewer.dto.users.LoginResponseDTO;
import com.reviewer.reviewer.dto.users.RegisterDto;
import com.reviewer.reviewer.dto.users.RegisterResponse;
import com.reviewer.reviewer.infra.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDto loginDto){
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(tokenService.login(loginDto)));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterDto registerDto, UriComponentsBuilder uriBuilder){
        var uri = uriBuilder.path("/register/{id}").buildAndExpand(registerDto.user()).toUri();
        return ResponseEntity.created(uri).body(new RegisterResponse(tokenService.register(registerDto)));
    }
}