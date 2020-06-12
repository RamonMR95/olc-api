package com.ramonmr95.tiky.olc.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.security.jwt.JwtUtils;
import com.ramonmr95.tiky.olc.security.service.UserDetailsImpl;
import com.ramonmr95.tiky.olc.services.interfaces.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Transactional
	@Override
	public Map<String, String> login(String username, String password) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		Map<String, String> jwtData = new HashMap<>();
		jwtData.put("id", String.valueOf(this.userServiceImpl.findByEmail(username).getId()));
		jwtData.put("token", jwt);
		jwtData.put("role", roles.get(0));
		jwtData.put("email", username);
		jwtData.put("iat", this.jwtUtils.getIssuedAtFromJwtToken(jwt));
		jwtData.put("exp", this.jwtUtils.getExpirationFromJwtToken(jwt));
		return jwtData;
	}

}
