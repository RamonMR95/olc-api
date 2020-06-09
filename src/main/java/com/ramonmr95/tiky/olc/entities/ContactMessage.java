package com.ramonmr95.tiky.olc.entities;

import java.io.Serializable;

public class ContactMessage implements Serializable {

	private static final long serialVersionUID = -1595672012284907193L;

	private String subject;
	private String body;
	private String name;
	private String email;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
