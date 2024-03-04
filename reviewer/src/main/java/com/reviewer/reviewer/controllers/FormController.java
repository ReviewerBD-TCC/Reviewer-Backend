package com.reviewer.reviewer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reviewer.reviewer.dto.forms.FormQuestionDto;
import com.reviewer.reviewer.dto.forms.FormsDto;
import com.reviewer.reviewer.services.FormService;

@RestController
@RequestMapping("form")
public class FormController {

    @Autowired
    private FormService service;

    @PostMapping
    public ResponseEntity<FormsDto> create(){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create());
    }

    @PostMapping("/{formId}/{questionId}")
    public ResponseEntity<FormQuestionDto> createFormQuestion(@PathVariable(name = "formId") Long formId, @PathVariable(name = "questionId") Long questionId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFormQuestion(formId, questionId));

    }
    
}
