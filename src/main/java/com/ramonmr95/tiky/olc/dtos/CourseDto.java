package com.ramonmr95.tiky.olc.dtos;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.ramonmr95.tiky.olc.entities.Course;
import com.ramonmr95.tiky.olc.entities.Exam;

public class CourseDto {

	private Long id;

	private String courseName;

	private Date yearStart;

	private Date yearEnd;

	private String schedule;

	private List<ExamDto> exams;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Date getYearStart() {
		return yearStart;
	}

	public void setYearStart(Date yearStart) {
		this.yearStart = yearStart;
	}

	public Date getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(Date yearEnd) {
		this.yearEnd = yearEnd;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public List<ExamDto> getExams() {
		return exams;
	}

	public void setExams(List<ExamDto> exams) {
		this.exams = exams;
	}

	public Course convertToEntity() {
		return new ModelMapper().map(this, Course.class);
	}

}
