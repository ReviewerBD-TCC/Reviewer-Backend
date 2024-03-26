package com.reviewer.reviewer.controllers;

import com.reviewer.reviewer.dto.forms.*;
import com.reviewer.reviewer.models.FormIndication;
import com.reviewer.reviewer.repositories.UserRepository;
import com.reviewer.reviewer.services.FormIndicationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/form_indication")
@SecurityRequirement(name = "bearer-key")
public class FormIndicationController {

    @Autowired
    private UserRepository userRepository;

    @Resource
    private FormIndicationService formIndicationService;

    @PostMapping
    @Transactional
    public ResponseEntity<FormIndicationResponseDto> create(@RequestBody @Valid FormIndicationDto data, UriComponentsBuilder uriBuilder){

        var userIndication = userRepository.findById(data.userIndication());
        var formIndication = new FormIndication(userIndication.get());

        var uri = uriBuilder.path("/form_indication/{id}").buildAndExpand(formIndication.getId()).toUri();

        return ResponseEntity.created(uri).body(formIndicationService.create(data));
    }

}
