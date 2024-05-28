package com.reviewer.reviewer.controllers;


import java.util.List;

import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
import com.reviewer.reviewer.infra.functionsConfig.CompareRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.reviewer.reviewer.dto.questions.QuestionActiveDto;
import com.reviewer.reviewer.dto.questions.QuestionDto;
import com.reviewer.reviewer.services.QuestionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearer-key")
@CrossOrigin( origins = "*")
@RequestMapping("api/v1/question")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @Autowired
    private CompareRoles roles;


    @PostMapping
    @Transactional
    public ResponseEntity<QuestionResponseDto> create(@RequestBody @Valid QuestionDto data, @AuthenticationPrincipal Jwt tokenJWT){
        if (!roles.compareRoles(tokenJWT).equals("ROLE_ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));
    }

   @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> findById(@PathVariable(name = "id") Long id, @AuthenticationPrincipal Jwt tokenJWT){
       if (!roles.compareRoles(tokenJWT).equals("ROLE_ADMIN")){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
       }
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<QuestionResponseDto>> findAll(@AuthenticationPrincipal Jwt tokenJWT){
        if (roles.compareRoles(tokenJWT).equals("ROLE_ADMIN") || roles.compareRoles(tokenJWT).equals("ROLE_BDUSERSWD")){
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> update(@PathVariable(name = "id") Long id, @RequestBody @Valid QuestionDto data, @AuthenticationPrincipal Jwt tokenJWT){
        if (!roles.compareRoles(tokenJWT).equals("ROLE_ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, data));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> partialUpdate(@PathVariable(name = "id") Long id, @RequestBody @Valid QuestionActiveDto data, @AuthenticationPrincipal Jwt tokenJWT){
        if (!roles.compareRoles(tokenJWT).equals("ROLE_ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.partialUpdate(id, data));
    }


}
