package com.ramonmr95.tiky.olc.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramonmr95.tiky.olc.entities.Login;
import com.ramonmr95.tiky.olc.services.interfaces.ILoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private ILoginService loginService;

	@PostMapping
	public ResponseEntity<?> login(@RequestBody Login login) {
		Map<String, String> jwtData = this.loginService.login(login.getUsername(), login.getPassword());
		return new ResponseEntity<>(jwtData, HttpStatus.OK);
	}

}
