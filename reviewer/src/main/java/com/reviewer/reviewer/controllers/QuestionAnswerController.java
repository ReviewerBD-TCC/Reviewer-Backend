package com.reviewer.reviewer.controllers;


import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import com.reviewer.reviewer.dto.questions.QuestionAnswerResponseDto;
import com.reviewer.reviewer.services.QuestionAnswerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearer-key")
@CrossOrigin( origins = "*")
@RequestMapping("api/v1/answer_form")
public class QuestionAnswerController {
    @Autowired
    private QuestionAnswerService service;

    @PostMapping
    @Transactional
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<QuestionAnswerResponseDto>> create(@RequestBody @Valid QuestionAnswerDto data){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));
    }
//    @GetMapping
//    @Secured("ROLE_ADMIN")
//    public ResponseEntity<List<QuestionAnswerResponseDto>> findAll(){
//        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
//    }

    @GetMapping("/user/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<QuestionAnswerResponseDto>> findByUserId(@PathVariable(name = "id")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByUserId(id));
    }
    @GetMapping("/question/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<QuestionAnswerResponseDto>> findByQuestionId(@PathVariable(name = "id")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllByQuestionId(id));
    }

}
