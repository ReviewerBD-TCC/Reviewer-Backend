package com.reviewer.reviewer.controllers;

import com.reviewer.reviewer.dto.forms.IndicationFormDto;
import com.reviewer.reviewer.dto.forms.IndicationFormResponseDto;
import com.reviewer.reviewer.dto.forms.QuestionFormListDto;
import com.reviewer.reviewer.infra.functionsConfig.CompareRoles;
import com.reviewer.reviewer.repositories.UserRepository;
import com.reviewer.reviewer.services.IndicationFormService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;


@RestController
@CrossOrigin( origins = "*")
@SecurityRequirement(name = "bearer-key")
@RequestMapping("api/v1/indication_form")
public class IndicationFormController {

    @Resource
    private IndicationFormService indicationFormService;

    @Autowired
    private CompareRoles roles;

    @PostMapping
    @Transactional
    public ResponseEntity<IndicationFormResponseDto> create(@RequestBody @Valid IndicationFormDto data, @AuthenticationPrincipal Jwt tokenJWT) throws UnknownHostException {
        return ResponseEntity.status(HttpStatus.CREATED).body(indicationFormService.create(data, tokenJWT));
    }
    
    @GetMapping("/user/{id}")
    @Transactional
    public ResponseEntity<List<QuestionFormListDto>> getFormByIndicated(@PathVariable(name = "id") String id, @AuthenticationPrincipal Jwt tokenJWT){
        if (!roles.compareRoles(tokenJWT).equals("ROLE_ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(indicationFormService.getIndicatedWithForm(id));

    }
    @GetMapping("/pending/{id}")
    @Transactional
    public ResponseEntity<List<QuestionFormListDto>> hasPendingForm(@PathVariable(name = "id")String id,@AuthenticationPrincipal Jwt tokenJWT){
        if (!roles.compareRoles(tokenJWT).equals("ROLE_ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(indicationFormService.pendingFormToRespond(id));

    }
    
}
