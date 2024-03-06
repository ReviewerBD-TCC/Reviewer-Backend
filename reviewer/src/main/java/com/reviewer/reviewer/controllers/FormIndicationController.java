package com.reviewer.reviewer.controllers;

import com.reviewer.reviewer.dto.forms.FormIndicationDto;
import com.reviewer.reviewer.dto.forms.FormIndicationResponseDto;
import com.reviewer.reviewer.dto.forms.IndicatedUserDto;
import com.reviewer.reviewer.dto.forms.IndicatedUserResponseDto;
import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import com.reviewer.reviewer.dto.questions.QuestionAnswerResponseDto;
import com.reviewer.reviewer.models.FormIndication;
import com.reviewer.reviewer.models.IndicatedUsers;
import com.reviewer.reviewer.repositories.FormIndicationRepository;
import com.reviewer.reviewer.repositories.IndicatedUserRepository;
import com.reviewer.reviewer.repositories.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/form_indication")
@SecurityRequirement(name = "bearer-key")
public class FormIndicationController {

    @Autowired
    private FormIndicationRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IndicatedUserRepository indicatedUserRepository;

    @PostMapping("/indication")
    @Transactional
    public ResponseEntity<FormIndicationResponseDto> create(@RequestBody @Valid FormIndicationDto dados, UriComponentsBuilder uriBuilder){

        var indicatingUser = userRepository.findById(dados.indicatingUser());
        var indicatedUsers = indicatedUserRepository.findById(dados.indicatedUsers());

        var indicated = new IndicatedUserDto(indicatedUsers.get());

        var formIndicated = new FormIndication(dados.id(), indicatingUser.get(), indicatedUsers.get());

        repository.save(formIndicated);

        var uri = uriBuilder.path("/form_indication/{id}").buildAndExpand(formIndicated.getId()).toUri();

        return ResponseEntity.created(uri).body(new FormIndicationResponseDto(formIndicated, indicated));
    }

    @PostMapping("/indicated_user")
    @Transactional
    public ResponseEntity<IndicatedUserResponseDto> create(@RequestBody @Valid IndicatedUserDto dados, UriComponentsBuilder uriBuilder){

        var user = userRepository.findById(dados.user_id());

        var indicatedUser = new IndicatedUsers(dados.id(), user.get());

        indicatedUserRepository.save(indicatedUser);

        var uri = uriBuilder.path("/indicated_user/{id}").buildAndExpand(indicatedUser.getId()).toUri();

        return ResponseEntity.created(uri).body(new IndicatedUserResponseDto(indicatedUser));
    }


}
