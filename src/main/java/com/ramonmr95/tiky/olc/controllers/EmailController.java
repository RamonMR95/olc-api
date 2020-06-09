package com.ramonmr95.tiky.olc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramonmr95.tiky.olc.entities.ContactMessage;
import com.ramonmr95.tiky.olc.services.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@Autowired
	private Environment env;

	@PostMapping("/contact-us")
	public ResponseEntity<?> contactUs(@RequestBody ContactMessage contactMessage) {
		this.emailService.sendEmail(env.getProperty("spring.mail.username"), contactMessage.getSubject(), "Name: "
				+ contactMessage.getName() + ", email: " + contactMessage.getEmail() + "\n" + contactMessage.getBody());
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
