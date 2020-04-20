package com.ramonmr95.tiky.olc.services;

import java.util.HashMap;
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

	@Transactional
	@Override
	public User save(User user) {
		return this.userDao.save(user);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		this.userDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Map<Double, String> findMarksAndSubjectsByStudentIdAndYearStart(Long id, String year) {
		Map<Double, String> marksMap = new HashMap<Double, String>();
		List<Object[]> marksList = this.userDao.findMarkAndSubjectsByStudentIdAndYearStart(id, year);
		marksList.forEach(mark -> marksMap.put((Double) mark[0], (String) mark[1]));
		return marksMap;
	}

}
