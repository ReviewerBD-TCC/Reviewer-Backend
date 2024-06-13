package com.reviewer.reviewer.services;


import com.reviewer.reviewer.dto.users.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.reviewer.reviewer.dto.email.EmailRecordDto;
import jakarta.mail.internet.MimeMessage;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private UserService userService;
	private ExecutorService executorService = Executors.newCachedThreadPool();

	@Value("${spring.mail.username}")
	private String sender;

	public void sendMail(EmailRecordDto data, Jwt jwtUser){
		userService.isInDatabase(new UserResponseDto(jwtUser));
		CompletableFuture<Void> future = sendMailAsync(data);
		future.thenRun(()-> System.out.println("Email enviado!")).exceptionally(ex->{
			System.out.println("Ocorreu um erro ao enviar o email"+ ex.getMessage());
			return null;
		});
	}
	@Async
	public CompletableFuture<Void> sendMailAsync(EmailRecordDto data) {
		return CompletableFuture.runAsync(() -> {
			try {
				MimeMessage mimeMessage = javaMailSender.createMimeMessage();
				MimeMessageHelper mimeMessageHelper;
				mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
				mimeMessageHelper.setBcc(data.bcc());
				mimeMessageHelper.setFrom(sender);
				mimeMessageHelper.setTo(data.to());
				mimeMessageHelper.setText(data.body(), true);
				mimeMessageHelper.setSubject(data.subject());

				javaMailSender.send(mimeMessage);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}, executorService);

	}
}




