package com.reviewer.reviewer.controllers;

import com.reviewer.reviewer.infra.functionsConfig.CompareRoles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.reviewer.reviewer.dto.email.EmailRecordDto;
import com.reviewer.reviewer.services.EmailService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/email")
@SecurityRequirement(name = "bearer_key")
@CrossOrigin( origins = "*")
public class EmailController {

	@Autowired
	private EmailService service;

	@Autowired
	private CompareRoles roles;

	@PostMapping
	public ResponseEntity<?> sendEmail(@RequestBody EmailRecordDto data, @AuthenticationPrincipal Jwt tokenJWT){
		if (!roles.compareRoles(tokenJWT).equals("ROLE_ADMIN")){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		service.sendMail(data);
		return ResponseEntity.status(HttpStatus.CREATED).body("Email sent successfully!");
	}
}
