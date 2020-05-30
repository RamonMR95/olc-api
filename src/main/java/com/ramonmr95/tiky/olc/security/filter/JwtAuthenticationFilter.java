package com.ramonmr95.tiky.olc.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ramonmr95.tiky.olc.exceptions.InvalidCredentialsException;
import com.ramonmr95.tiky.olc.services.interfaces.ILoginService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private ILoginService loginService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (token != null && token.startsWith("Bearer")) {
			try {
				this.loginService.validate(token.replace("Bearer ", ""));
				filterChain.doFilter(request, response);
			} catch (InvalidCredentialsException e) {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			}
		}
	}

}
