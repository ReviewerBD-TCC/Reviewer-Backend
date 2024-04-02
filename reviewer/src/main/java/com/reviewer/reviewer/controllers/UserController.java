package com.reviewer.reviewer.controllers;

import com.reviewer.reviewer.dto.users.UserDetailsResponseDto;
import com.reviewer.reviewer.dto.users.UserResponseDto;
import com.reviewer.reviewer.services.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@CrossOrigin(origins = "*")
@SecurityRequirement(name="bearer_key")
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<UserResponseDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/me")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<UserDetailsResponseDto> getCurrentUser(Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            return ResponseEntity.status(HttpStatus.OK).body(service.findMe(principal));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
