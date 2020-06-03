package com.ramonmr95.tiky.olc.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramonmr95.tiky.olc.entities.Login;
import com.ramonmr95.tiky.olc.security.jwt.JwtUtils;
import com.ramonmr95.tiky.olc.security.service.UserDetailsImpl;
import com.ramonmr95.tiky.olc.services.UserServiceImpl;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired 
	private UserServiceImpl userServiceImpl;

	@PostMapping
	public ResponseEntity<?> login(@RequestBody Login login) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		Map<String, String> jwtData = new HashMap<>();
		jwtData.put("id", String.valueOf(this.userServiceImpl.findByEmail(login.getUsername()).getId()));
		jwtData.put("token", jwt);
		jwtData.put("role", roles.get(0));
		jwtData.put("email", login.getUsername());
		jwtData.put("iat", this.jwtUtils.getIssuedAtFromJwtToken(jwt));
		jwtData.put("exp", this.jwtUtils.getExpirationFromJwtToken(jwt));
		return new ResponseEntity<>(jwtData, HttpStatus.OK);
	}

}
