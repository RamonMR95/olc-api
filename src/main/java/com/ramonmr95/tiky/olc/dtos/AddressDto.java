package com.ramonmr95.tiky.olc.dtos;

import org.modelmapper.ModelMapper;

import com.ramonmr95.tiky.olc.entities.Address;

public class AddressDto {

	private Long id;

	private String street;

	private String city;

	private String province;

	private String zip;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
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

	public Address convertToEntity() {
		return new ModelMapper().map(this, Address.class);
	}

}
