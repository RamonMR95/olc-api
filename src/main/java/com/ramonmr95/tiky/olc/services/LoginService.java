package com.ramonmr95.tiky.olc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ramonmr95.tiky.olc.entities.User;
import com.ramonmr95.tiky.olc.exceptions.InvalidCredentialsException;
import com.ramonmr95.tiky.olc.services.interfaces.ILoginService;
import com.ramonmr95.tiky.olc.services.interfaces.IUserService;

@Service
public class LoginService implements ILoginService {

	@Autowired
	private IUserService userService;

	@Autowired
	private Environment env;

	@Override
	public String login(String email, String password) throws InvalidCredentialsException {
		User user = this.userService.findByEmailAndPassword(email, password);

		if (user != null) {
			return generateToken(user);
		}
		throw new InvalidCredentialsException("Invalid credentials");
	}

	private String generateToken(User user) {
		Calendar expiration = Calendar.getInstance();
		expiration.add(Calendar.HOUR_OF_DAY, 2);
		Date exp = expiration.getTime();
		Algorithm algorithm = Algorithm.HMAC256(env.getProperty("jwt.token.secret"));
		return JWT.create().withIssuer("olc").withSubject(user.getEmail()).withClaim("role", user.getRole().getName())
				.withIssuedAt(new Date()).withExpiresAt(exp).sign(algorithm);

	}

	@Override
	public void validate(String token) throws InvalidCredentialsException {
		try {
			Algorithm algorithm = Algorithm.HMAC256(env.getProperty("jwt.token.secret"));
			JWTVerifier verifier = JWT.require(algorithm).build();
			verifier.verify(token);
		} catch (JWTVerificationException e) {
			throw new InvalidCredentialsException("Cannot validate token");
		}
	}

}
