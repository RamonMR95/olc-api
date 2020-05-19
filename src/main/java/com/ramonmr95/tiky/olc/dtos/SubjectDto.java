package com.ramonmr95.tiky.olc.dtos;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.ramonmr95.tiky.olc.entities.Subject;

public class SubjectDto {

	private Long id;

	private String subjectName;

	private List<ExamDto> exam;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public List<ExamDto> getExam() {
		return exam;
	}

	public void setExam(List<ExamDto> exam) {
		this.exam = exam;
	}

	public Subject convertToEntity() {
		return new ModelMapper().map(this, Subject.class);
	}

}
