package com.ramonmr95.tiky.olc.dtos;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.ramonmr95.tiky.olc.entities.Exam;

public class ExamDto {

	private Long id;

	private SubjectDto subject;

	private CourseDto course;

	private Date date;

	private Double mark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SubjectDto getSubject() {
		return subject;
	}

	public void setSubject(SubjectDto subject) {
		this.subject = subject;
	}

	public CourseDto getCourse() {
		return course;
	}

	public void setCourse(CourseDto course) {
		this.course = course;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getMark() {
		return mark;
	}

	public void setMark(Double mark) {
		this.mark = mark;
	}

	public Exam convertToEntity() {
		return new ModelMapper().map(this, Exam.class);
	}

}
