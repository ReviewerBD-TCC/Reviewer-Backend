package com.reviewer.reviewer.controllers;

import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import com.reviewer.reviewer.dto.questions.QuestionAnswerResponseDto;
import com.reviewer.reviewer.infra.functionsConfig.CompareRoles;
import com.reviewer.reviewer.services.QuestionAnswerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearer-key")
@CrossOrigin( origins = "*")
@RequestMapping("api/v1/answer_form")
public class QuestionAnswerController {
    @Autowired
    private QuestionAnswerService service;

    @Autowired
    private CompareRoles roles;

    @PostMapping
    @Transactional
    public ResponseEntity<List<QuestionAnswerResponseDto>> create(@RequestBody @Valid QuestionAnswerDto data, @AuthenticationPrincipal Jwt tokenJWT){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));
    }

//    @GetMapping
//    @Secured("ROLE_ADMIN")
//    public ResponseEntity<List<QuestionAnswerResponseDto>> findAll(){
//        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
//    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<QuestionAnswerResponseDto>> findByUserId(@PathVariable(name = "id")Long id, @AuthenticationPrincipal Jwt tokenJWT){
        if (!roles.compareRoles(tokenJWT).equals("ROLE_ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.findByUserId(id));
    }
    @GetMapping
    public ResponseEntity<List<QuestionAnswerResponseDto>> findByQuestionId(@RequestParam(name = "userId")Long userId, @RequestParam(name = "questionId")Long questionId, @AuthenticationPrincipal Jwt tokenJWT){
        if (!roles.compareRoles(tokenJWT).equals("ROLE_ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllByQuestionId(userId, questionId));
    }


}
