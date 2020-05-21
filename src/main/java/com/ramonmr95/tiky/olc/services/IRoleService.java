package com.ramonmr95.tiky.olc.services;

import java.util.List;

import com.ramonmr95.tiky.olc.entities.Role;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;

public interface IRoleService {

	public List<Role> findAll();

	public Role findOne(Long id) throws DataNotFoundException;

	public Role save(Role role) throws EntityValidationException;

	public void delete(Long id) throws DataNotFoundException;

}
