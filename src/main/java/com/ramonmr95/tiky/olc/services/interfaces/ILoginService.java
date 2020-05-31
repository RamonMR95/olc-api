package com.ramonmr95.tiky.olc.services.interfaces;

import java.util.Map;

import com.ramonmr95.tiky.olc.exceptions.InvalidCredentialsException;

public interface ILoginService {

	public Map<String, String> login(String email, String password) throws InvalidCredentialsException;

	public void validate(String token) throws InvalidCredentialsException;
}
