package com.reviewer.reviewer.controllers;




import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.reviewer.reviewer.dto.email.EmailRecordDto;
import com.reviewer.reviewer.services.EmailService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/email")
@SecurityRequirement(name = "bearer_key")
@CrossOrigin( origins = "*")
public class EmailController {

	@Autowired
	private EmailService service;
	
	@PostMapping
	public ResponseEntity<?> sendEmail(@RequestBody EmailRecordDto data){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.sendMail(data));
	}
}
