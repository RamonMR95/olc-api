package com.ramonmr95.tiky.olc.services;

import java.util.List;

import com.ramonmr95.tiky.olc.entities.Role;

public interface IRoleService {

	public List<Role> findAll();

	public Role findOne(Long id);

	public Role save(Role role);

	public void delete(Long id);

}
