package com.ramonmr95.tiky.olc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "exams")
public class Exam implements Serializable {

	private static final long serialVersionUID = 3706876187182768150L;
	private static final String dateFormat = "yyyy-MM-dd";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "The name is required")
	@Column(nullable = false)
	private String name;

	@JsonIgnore
	@OneToOne
	private Subject subject;

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	@NotNull(message = "The course is required")
	private Course course;

	@Temporal(TemporalType.DATE)
	@NotNull(message = "The exam date is required")
	@Column(nullable = false)
	@JsonFormat(pattern = dateFormat)
	private Date date;

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

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
