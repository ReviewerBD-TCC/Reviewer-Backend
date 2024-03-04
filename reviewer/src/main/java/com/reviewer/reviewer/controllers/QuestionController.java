package com.reviewer.reviewer.controllers;


import java.util.List;

import com.reviewer.reviewer.dto.questions.QuestionDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.reviewer.reviewer.dto.questions.QuestionDto;
import com.reviewer.reviewer.services.QuestionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @PostMapping
    public ResponseEntity<QuestionDetailsDto> create(@RequestBody @Valid QuestionDto data){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));
    }


   @GetMapping("/{id}")
    public ResponseEntity<QuestionDetailsDto> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<QuestionDetailsDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<QuestionDetailsDto> update(@PathVariable(name = "id") Long id, @RequestBody @Valid QuestionDto data){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, data));
    }


}
