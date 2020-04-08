package com.ramonmr95.tiky.olc.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
public class Address implements Serializable {

	private static final long serialVersionUID = -8206923172718319240L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "The street is required")
	@Column(nullable = false)
	private String street;

	@NotNull(message = "The province is required")
	@Column(nullable = false)
	private String province;

	@NotNull(message = "The city is required")
	@Column(nullable = false)
	private String city;

	@NotNull(message = "The zip is required")
	@Column(nullable = false)
	private String zip;

	@NotNull(message = "The country is required")
	@Column(nullable = false)
	private String country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
