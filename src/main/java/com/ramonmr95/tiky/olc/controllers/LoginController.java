package com.ramonmr95.tiky.olc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramonmr95.tiky.olc.entities.Login;
import com.ramonmr95.tiky.olc.exceptions.InvalidCredentialsException;
import com.ramonmr95.tiky.olc.parsers.JsonParser;
import com.ramonmr95.tiky.olc.services.interfaces.ILoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private ILoginService loginService;

	private JsonParser parser = new JsonParser();

	@PostMapping
	public ResponseEntity<?> login(@RequestBody Login login) {
		try {
			String jwtToken = this.loginService.login(login.getEmail(), login.getPassword());
			return new ResponseEntity<>(this.parser.parseToMap("jwt", jwtToken), HttpStatus.OK);
		} catch (InvalidCredentialsException e) {
			return new ResponseEntity<>(this.parser.parseToMap("errors", e.getMessage()), HttpStatus.BAD_REQUEST);
		}

	}
}
