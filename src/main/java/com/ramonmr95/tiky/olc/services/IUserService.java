package com.ramonmr95.tiky.olc.services;

import java.util.List;
import java.util.Map;

import com.ramonmr95.tiky.olc.entities.User;

public interface IUserService {

	public User findOne(Long id);

	public List<User> findAll();

	public User save(User user);

	public void delete(Long id);

	public Map<Double, String> findMarksAndSubjectsByStudentIdAndYearStart(Long id, String year);
}
