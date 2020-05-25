package com.ramonmr95.tiky.olc.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ramonmr95.tiky.olc.dtos.CourseDto;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

	private static final String dateFormat = "yyyy-MM-dd";
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Course name can't be null")
	@Column(nullable = false, name = "course_name")
	private String courseName;

	@NotNull(message = "Start year can't be null")
	@Column(nullable = false, name = "year_start")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = dateFormat)
	private Date yearStart;

	@NotNull(message = "End year name can't be null")
	@Column(nullable = false, name = "year_end")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = dateFormat)
	private Date yearEnd;

	@NotNull(message = "Schedule can't be null")
	@Column(nullable = false)
	private String schedule;

	@JsonIgnore()
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Exam> exams;

	@OneToOne
	private User mentor;

	private String photo;

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

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	public User getMentor() {
		return mentor;
	}

	public void setMentor(User mentor) {
		this.mentor = mentor;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public CourseDto convertToDto() {
		return new ModelMapper().map(this, CourseDto.class);
	}

}
