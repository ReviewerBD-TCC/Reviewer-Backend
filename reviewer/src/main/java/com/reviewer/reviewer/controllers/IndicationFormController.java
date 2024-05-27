package com.reviewer.reviewer.controllers;
import com.reviewer.reviewer.dto.forms.*;
import com.reviewer.reviewer.models.IndicationForm;
import com.reviewer.reviewer.repositories.UserRepository;
import com.reviewer.reviewer.services.IndicationFormService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.UnknownHostException;
import java.util.List;


@RestController
@CrossOrigin( origins = "*")
@SecurityRequirement(name = "bearer-key")
@RequestMapping("api/v1/indication_form")
public class IndicationFormController {

    @Autowired
    private UserRepository userRepository;

    @Resource
    private IndicationFormService indicationFormService;
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping
    @Transactional
   
    public ResponseEntity<IndicationFormResponseDto> create(@RequestBody @Valid IndicationFormDto data, UriComponentsBuilder uriBuilder) throws UnknownHostException {

        var userIndication = userRepository.findById(data.userIndication());
        var indicationForm = new IndicationForm(userIndication.get());

        var uri = uriBuilder.path("/indication_form/{id}").buildAndExpand(indicationForm.getId()).toUri();


        return ResponseEntity.created(uri).body(indicationFormService.create(data));
    }
    
    @GetMapping("/user/{id}")
    @Transactional
    public ResponseEntity<List<QuestionFormListDto>> getFormByIndicated(@PathVariable(name = "id")Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(indicationFormService.getIndicatedWithForm(id));

    }
    
}
