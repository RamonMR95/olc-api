package com.ramonmr95.tiky.olc.exceptions;

public class InvalidCredentialsException extends Exception {

	private static final long serialVersionUID = -918418491333361304L;

	public InvalidCredentialsException() {

	}

	public InvalidCredentialsException(String message) {
		super(message);
	}
}
