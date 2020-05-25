package com.ramonmr95.tiky.olc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ramonmr95.tiky.olc.dtos.UserDto;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 2781452882301619960L;
	private static final String dateFormat = "yyyy-MM-dd";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", nullable = true)
	private Address address;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id", nullable = true)
	private Course course;

	@NotNull(message = "The name is required")
	@Column(nullable = false)
	private String name;

	@NotNull(message = "The surname is required")
	@Column(name = "sur_name", nullable = false)
	private String surName;

	@NotNull(message = "The nickname is required")
	@Column(name = "nick_name", nullable = false, unique = true)
	private String nickName;

	@NotNull(message = "The email is required")
	@Email(message = "Invalid email format")
	@Column(nullable = false, unique = true)
	private String email;

	@NotNull(message = "The password is required")
	@Column(nullable = false)
	private String password;

	@NotNull(message = "The active state is required")
	@Column(nullable = false)
	private boolean active;

	private String photo;

	@NotNull(message = "The birth date is required")
	@Column(nullable = false, name = "birth_date")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = dateFormat)
	private Date birthDate;

	private String about;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public UserDto convertToDto() {
		return new ModelMapper().map(this, UserDto.class);
	}

}
