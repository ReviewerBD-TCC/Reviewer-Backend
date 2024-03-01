package com.reviewer.reviewer.controllers;


import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import com.reviewer.reviewer.services.QuestionAnswerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("answer_form")
public class QuestionAnswerController {
    @Autowired
    private QuestionAnswerService service;

    @PostMapping
    public ResponseEntity<QuestionAnswerDto> create(@RequestBody @Valid QuestionAnswerDto data){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));
    }

}
