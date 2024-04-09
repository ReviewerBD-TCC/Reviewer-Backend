package com.reviewer.reviewer.controllers;


import java.util.List;

import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
@CrossOrigin
@RequestMapping("api/v1/question")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @PostMapping
    @Transactional
    @Secured("ROLE_ADMIN")
    public ResponseEntity<QuestionResponseDto> create(@RequestBody @Valid QuestionDto data){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));
    }

   @GetMapping("/{id}")
   @Secured("ROLE_ADMIN")
    public ResponseEntity<QuestionResponseDto> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
    
    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<QuestionResponseDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    
    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<QuestionResponseDto> update(@PathVariable(name = "id") Long id, @RequestBody @Valid QuestionDto data){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, data));
    }
    @PatchMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<QuestionResponseDto> partialUpdate(@PathVariable(name = "id") Long id, @RequestBody @Valid QuestionActiveDto data){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.partialUpdate(id, data));
    }


}
