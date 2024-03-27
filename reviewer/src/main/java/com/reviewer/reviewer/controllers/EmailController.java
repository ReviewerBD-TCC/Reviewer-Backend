package com.reviewer.reviewer.controllers;




import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.reviewer.reviewer.dto.email.EmailRecordDto;
import com.reviewer.reviewer.dto.email.EmailResponseDto;
import com.reviewer.reviewer.services.EmailService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("email")
@SecurityRequirement(name = "bearer_key")
@CrossOrigin
public class EmailController {

	@Autowired
	private EmailService service;
	
	@PostMapping
	public ResponseEntity<EmailResponseDto> sendEmail(@ModelAttribute EmailRecordDto data){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.sendMail(data));
	}
}
