package com.ramonmr95.tiky.olc.services.interfaces;

import java.util.List;
import java.util.Map;

import com.ramonmr95.tiky.olc.entities.Role;
import com.ramonmr95.tiky.olc.entities.User;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.exceptions.UserAlreadyEnrolledException;

public interface IUserService {

	public User findOne(Long id) throws DataNotFoundException;

	public List<User> findAll();

	public User save(User user) throws EntityValidationException, DataNotFoundException;

	public User update(User user, Long id) throws EntityValidationException, DataNotFoundException;

	public void delete(Long id) throws DataNotFoundException;

	public Map<String, Double> findMarksAndSubjectsByStudentIdAndYearStart(Long id, String year)
			throws DataNotFoundException;

	public List<User> findAllUsersPerCourse(Long course_id);

	public User findByEmailAndPassword(String email, String password);
	
	public User findByEmail(String email);

	public Role findRoleByUserId(Long id) throws DataNotFoundException;

	public User findMentorByCourseId(Long courseId) throws DataNotFoundException;
	
	public User findMentorById(Long id) throws DataNotFoundException;
	
	public Map<Long, String> findMentorsByCourse() throws DataNotFoundException;
	
	public void enroll(Long userId, Long courseId) throws DataNotFoundException, UserAlreadyEnrolledException;
	
	public User updatePhoto(Long userId, String url) throws DataNotFoundException, EntityValidationException;


}
