package com.ramonmr95.tiky.olc.dtos;

import java.io.Serializable;

import org.modelmapper.ModelMapper;

import com.ramonmr95.tiky.olc.entities.Question;

public class QuestionDto implements Serializable {

	private static final long serialVersionUID = -2004467237542605004L;

	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Question convertToEntity() {
		return new ModelMapper().map(this, Question.class);
	}

}
