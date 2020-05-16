package com.ramonmr95.tiky.olc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Role;
import com.ramonmr95.tiky.olc.repositories.IRolDao;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRolDao roleDao;

	@Transactional(readOnly = true)
	@Override
	public List<Role> findAll() {
		return (List<Role>) this.roleDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Role findOne(Long id) {
		return this.roleDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Role save(Role role) {
		return this.roleDao.save(role);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		this.roleDao.deleteById(id);
	}

}
