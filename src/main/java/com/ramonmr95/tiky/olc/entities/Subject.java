package com.ramonmr95.tiky.olc.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.ramonmr95.tiky.olc.dtos.SubjectDto;

@Entity
@Table(name = "subjects")
public class Subject implements Serializable {

	private static final long serialVersionUID = -1941197654348468398L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "subject_name", nullable = false)
	@NotNull(message = "Name of subject can't be empty")
	private String subjectName;

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

	public SubjectDto convertToDto() {
		return new ModelMapper().map(this, SubjectDto.class);
	}

}
