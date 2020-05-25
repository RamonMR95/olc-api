package com.ramonmr95.tiky.olc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Role;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.repositories.IRoleDao;
import com.ramonmr95.tiky.olc.services.interfaces.IRoleService;
import com.ramonmr95.tiky.olc.validators.EntityValidator;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;

	private EntityValidator<Role> entityValidator = new EntityValidator<>();

	@Transactional(readOnly = true)
	@Override
	public List<Role> findAll() {
		return (List<Role>) this.roleDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Role findOne(Long id) throws DataNotFoundException {
		if (id != null) {
			return this.roleDao.findById(id)
					.orElseThrow(() -> new DataNotFoundException("Role with id: " + id + " could not be found."));
		}
		throw new DataNotFoundException("Error role id is required");
	}

	@Transactional
	@Override
	public Role save(Role role) throws EntityValidationException {
		if (this.entityValidator.isEntityValid(role)) {
			return this.roleDao.save(role);
		}
		throw new EntityValidationException(this.entityValidator.getEntityValidationErrorsString(role));
	}

	@Transactional
	@Override
	public void delete(Long id) throws DataNotFoundException {
		this.findOne(id);
		this.roleDao.deleteById(id);
	}

}
