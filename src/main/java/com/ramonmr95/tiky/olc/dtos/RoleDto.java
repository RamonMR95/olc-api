package com.ramonmr95.tiky.olc.dtos;

import org.modelmapper.ModelMapper;

import com.ramonmr95.tiky.olc.entities.Role;

public class RoleDto {

	private Long id;

	private String name;

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

	public Role convertToEntity() {
		return new ModelMapper().map(this, Role.class);
	}

}
