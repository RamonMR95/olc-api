package com.ramonmr95.tiky.olc.exceptions;

public class ExamAlreadyDoneByStudentException extends Exception {

	private static final long serialVersionUID = -2198160832558883558L;

	public ExamAlreadyDoneByStudentException() {

	}

	public ExamAlreadyDoneByStudentException(String message) {
		super(message);
	}

}
