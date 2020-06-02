package com.ramonmr95.tiky.olc.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Course;
import com.ramonmr95.tiky.olc.entities.Role;
import com.ramonmr95.tiky.olc.entities.User;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.exceptions.UserAlreadyEnrolledException;
import com.ramonmr95.tiky.olc.repositories.IUserDao;
import com.ramonmr95.tiky.olc.services.interfaces.ICourseService;
import com.ramonmr95.tiky.olc.services.interfaces.IRoleService;
import com.ramonmr95.tiky.olc.services.interfaces.IUserService;
import com.ramonmr95.tiky.olc.validators.EntityValidator;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IRoleService roleServiceImpl;

	@Autowired
	private ICourseService courseService;
	
	private EntityValidator<User> entityValidator = new EntityValidator<>();

	@Autowired
	private IUserDao userDao;

	@Transactional(readOnly = true)
	@Override
	public User findOne(Long id) throws DataNotFoundException {
		if (id != null) {
			return this.userDao.findById(id)
					.orElseThrow(() -> new DataNotFoundException("User with id: " + id + " could not be found."));
		}
		throw new DataNotFoundException("Error user id is required");
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> findAll() {
		return (List<User>) this.userDao.findAll();
	}

	@Transactional
	@Override
	public User update(User user, Long id) throws DataNotFoundException, EntityValidationException {
		User newUser = this.findOne(id);

		if (this.entityValidator.isEntityValid(user)) {
			Role role = this.roleServiceImpl.findOne(user.getRole().getId());
			newUser.setRole(role);
			newUser.setName(user.getName());
			newUser.setSurName(user.getSurName());
			newUser.setPassword(user.getPassword());
			newUser.setAbout(user.getAbout());
			newUser.setPhoto(user.getPhoto());
			return this.userDao.save(newUser);
		}
		throw new EntityValidationException(this.entityValidator.getEntityValidationErrorsString(user));
	}

	@Transactional
	@Override
	public User save(User user) throws EntityValidationException, DataNotFoundException {
		Role role = this.roleServiceImpl.findOne(user.getRole().getId());
		user.setRole(role);

		if (this.entityValidator.isEntityValid(user)) {
			return this.userDao.save(user);
		}
		throw new EntityValidationException(this.entityValidator.getEntityValidationErrorsString(user));
	}

	@Transactional
	@Override
	public void delete(Long id) throws DataNotFoundException {
		this.findOne(id);
		this.userDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, Double> findMarksAndSubjectsByStudentIdAndYearStart(Long id, String year)
			throws DataNotFoundException {
		this.findOne(id);
		Map<String, Double> marksMap = new HashMap<>();
		List<Object[]> marksList = this.userDao.findMarkAndSubjectsByStudentIdAndYearStart(id, year);
		marksList.forEach(mark -> marksMap.put((String) mark[1], (Double) mark[0]));
		return marksMap;
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> findAllUsersPerCourse(Long course_id) {
		return this.userDao.findAllUsersPerCourse(course_id);
	}

	@Transactional(readOnly = true)
	@Override
	public Role findRoleByUserId(Long id) throws DataNotFoundException {
		this.findOne(id);
		return this.userDao.findRoleByUserId(id);
	}

	@Transactional(readOnly = true)
	@Override
	public User findMentorByCourseId(Long courseId) throws DataNotFoundException {
		this.courseService.findOne(courseId);
		User user = this.userDao.findMentorByCourseId(courseId);
		if (user == null) {
			throw new DataNotFoundException("Course with id '" + courseId + "' doesn't have any mentor");
		}
		return user;
	}

	@Transactional(readOnly = true)
	@Override
	public User findByEmailAndPassword(String email, String password) {
		return this.userDao.findByEmailAndPassword(email, password);
	}

	@Transactional(readOnly = true)
	@Override
	public User findByEmail(String email) {
		return this.userDao.findByEmail(email);
	}

	@Transactional(readOnly = true)
	@Override
	public User findMentorById(Long id) throws DataNotFoundException {
		User user =  this.userDao.findMentorById(id);
		if (user != null) {
			return user;
		}
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public Map<Long, String> findMentorsByCourse() throws DataNotFoundException {
		List<Course> courses = this.courseService.findAll();
		
		Map<Long, String> data = new HashMap<>();

		for (Course course : courses) {
			User usr = this.findMentorById(course.getId());
			if (usr != null)  {
				data.put(course.getId(), usr.getName());
			} else {
				data.put(course.getId(), "");
			}
		}
		return data;
	}

	@Transactional
	@Override
	public void enroll(Long userId, Long courseId) throws DataNotFoundException, UserAlreadyEnrolledException {
		User user = this.findOne(userId);
		if (user.getCourse().getId() != courseId) {
			Course course = this.courseService.findOne(courseId);
			user.setCourse(course);
		} else {
			throw new UserAlreadyEnrolledException("User with id: " + userId + " is already enrolled to course with id: " + courseId);
		}

	}

}
