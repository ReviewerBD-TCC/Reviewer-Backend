package com.reviewer.reviewer.controllers;

import com.reviewer.reviewer.dto.forms.FormUpdateDto;
import com.reviewer.reviewer.dto.forms.QuestionFormCreateDto;
import com.reviewer.reviewer.dto.forms.QuestionFormResponseDto;
import com.reviewer.reviewer.infra.functionsConfig.CompareRoles;
import com.reviewer.reviewer.services.FormService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@SecurityRequirement(name = "bearer-key")
@RequestMapping("api/v1/form")
public class FormController {

    @Autowired
    private FormService service;

    @Autowired
    private CompareRoles roles;

    @PostMapping
    @Transactional
    public ResponseEntity<QuestionFormResponseDto> createForm(@RequestBody @Valid QuestionFormCreateDto data, @AuthenticationPrincipal Jwt tokenJWT){
        if (!roles.compareRoles(tokenJWT).equals("ROLE_ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));

    }
    @GetMapping("/{formId}")
    public ResponseEntity<List<QuestionFormResponseDto>> listFormQuestion(@PathVariable(name = "formId") Long formId, @AuthenticationPrincipal Jwt tokenJWT){
        if (!roles.compareRoles(tokenJWT).equals("ROLE_ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.listFormQuestion(formId));

    }
    @GetMapping
    public ResponseEntity<List<QuestionFormResponseDto>> findAll(@AuthenticationPrincipal Jwt tokenJWT){
        if (!roles.compareRoles(tokenJWT).equals("ROLE_ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());

    }
    @DeleteMapping("/{formId}")
    public ResponseEntity<?> deleteForm(@PathVariable(name = "formId") Long formId, @AuthenticationPrincipal Jwt tokenJWT){
        if (!roles.compareRoles(tokenJWT).equals("ROLE_ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        service.deleteForm(formId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    @PatchMapping("/{formId}")
    public ResponseEntity<?> updateForm(@PathVariable(name = "formId") Long formId, @RequestBody @Valid FormUpdateDto data, @AuthenticationPrincipal Jwt tokenJWT){
        if (!roles.compareRoles(tokenJWT).equals("ROLE_ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        service.updateForm(formId, data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Form edited successfully!");

    }
}
