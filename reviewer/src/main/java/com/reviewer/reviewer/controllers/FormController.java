package com.reviewer.reviewer.controllers;
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
    public ResponseEntity<QuestionFormCreatedDto> createForm(@RequestBody @Valid QuestionFormListDto data){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));

    }
    @GetMapping("/{formId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<QuestionFormResponseDto>> listFormQuestion(@PathVariable(name = "formId") Long formId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.listFormQuestion(formId));

    }
}
