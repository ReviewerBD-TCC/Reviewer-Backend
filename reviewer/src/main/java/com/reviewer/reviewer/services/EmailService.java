package com.reviewer.reviewer.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.reviewer.reviewer.dto.email.EmailRecordDto;
import com.reviewer.reviewer.dto.email.EmailResponseDto;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;
	
		
	public EmailResponseDto sendMail(EmailRecordDto data) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper;
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			if(data.bcc()!=null) mimeMessageHelper.setBcc(data.bcc());
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(data.to());
			mimeMessageHelper.setText(data.body(), true);
			mimeMessageHelper.setSubject(data.subject());
			mimeMessageHelper.setCc(data.cc());
			
			var file = data.attchment();
			if(file != null) {
				for(int i = 0; i < data.attchment().length; i++) {
					mimeMessageHelper.addAttachment(file[i].getOriginalFilename(), new ByteArrayResource(file[i].getBytes()));
				}
			}
			
			
			javaMailSender.send(mimeMessage);
			
			return new EmailResponseDto("Email send successfully!");
		}
		
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	
		
	}
	

}
