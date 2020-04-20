package com.ramonmr95.tiky.olc.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.User;
import com.ramonmr95.tiky.olc.repositories.IUserDao;

public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Transactional(readOnly = true)
	@Override
	public User findOne(Long id) {
		return this.userDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> findAll() {
		return (List<User>) this.userDao.findAll();
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Double, String> findMarksAndSubjectsByStudentIdAndYearStart(Long id, String year) {
		// TODO Auto-generated method stub
		return null;
	}

}
