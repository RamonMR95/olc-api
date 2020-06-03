package com.ramonmr95.tiky.olc.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.ramonmr95.tiky.olc.dtos.QuestionDto;

@Entity
@Table(name = "questions")
public class Question implements Serializable {

	private static final long serialVersionUID = -8988646629202041303L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Exam exam;

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

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
	public QuestionDto convertToDto() {
		return new ModelMapper().map(this, QuestionDto.class);
	}

}
