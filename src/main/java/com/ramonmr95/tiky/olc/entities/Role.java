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

import com.ramonmr95.tiky.olc.dtos.RoleDto;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

	private static final long serialVersionUID = 350668629164871720L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "The name is required")
	@Column(nullable = false, unique = true)
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

	public RoleDto convertToDto() {
		return new ModelMapper().map(this, RoleDto.class);
	}

}
