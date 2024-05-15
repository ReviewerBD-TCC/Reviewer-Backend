package com.reviewer.reviewer.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reviewer.reviewer.dto.forms.FormUpdateDto;
import com.reviewer.reviewer.dto.forms.QuestionFormCreatedDto;
import com.reviewer.reviewer.dto.forms.QuestionFormListDto;
import com.reviewer.reviewer.dto.forms.QuestionFormResponseDto;
import com.reviewer.reviewer.services.FormService;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@SecurityRequirement(name = "bearer-key")
@RequestMapping("api/v1/form")
public class FormController {

    @Autowired
    private FormService service;

    @PostMapping
    @Transactional
    @Secured("ROLE_ADMIN")
    public ResponseEntity<QuestionFormListDto> createForm(@RequestBody @Valid QuestionFormCreatedDto data){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));

    }
    @GetMapping("/{formId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<QuestionFormResponseDto>> listFormQuestion(@PathVariable(name = "formId") Long formId){
        return ResponseEntity.status(HttpStatus.OK).body(service.listFormQuestion(formId));

    }
    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<QuestionFormResponseDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());

    }
    @DeleteMapping("/{formId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> deleteForm(@PathVariable(name = "formId") Long formId){
        service.deleteForm(formId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    @PatchMapping("/{formId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> updateForm(@PathVariable(name = "formId") Long formId, @RequestBody @Valid FormUpdateDto data){
        service.updateForm(formId, data);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
