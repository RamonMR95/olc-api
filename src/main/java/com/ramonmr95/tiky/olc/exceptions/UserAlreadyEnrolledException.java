package com.ramonmr95.tiky.olc.exceptions;

public class UserAlreadyEnrolledException extends Exception {
	
	private static final long serialVersionUID = -6761876205688707803L;

	public UserAlreadyEnrolledException() {
		
	}
	
	public UserAlreadyEnrolledException(String msg) {
		super(msg);
	}

}
