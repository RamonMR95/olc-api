package com.ramonmr95.tiky.olc.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.dtos.UserDto;
import com.ramonmr95.tiky.olc.entities.Course;
import com.ramonmr95.tiky.olc.entities.Role;
import com.ramonmr95.tiky.olc.entities.User;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
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
	public User update(UserDto userDto, Long id) throws DataNotFoundException, EntityValidationException {
		User user = this.findOne(id);
		Role role = this.roleServiceImpl.findOne(userDto.getRole().getId());
		user.setRole(role);
		user.setName(userDto.getName());
		user.setSurName(userDto.getSurName());
		user.setNickName(userDto.getNickName());
		user.setEmail(userDto.getEmail());
		user.setActive(userDto.isActive());

		if (this.entityValidator.isEntityValid(user)) {
			return this.userDao.save(user);
		}
		throw new EntityValidationException(this.entityValidator.getEntityValidationErrorsString(user));
	}

	@Transactional
	@Override
	public User save(UserDto userDto) throws EntityValidationException, DataNotFoundException {
		User user = userDto.convertToEntity();
		Role role = this.roleServiceImpl.findOne(userDto.getRole().getId());
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
	public User findByNickName(String nickName) {
		return this.userDao.findByNickName(nickName);
	}

	@Transactional(readOnly = true)
	@Override
	public User findByEmail(String email) {
		return this.userDao.findByEmail(email);
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
		Course course = this.courseService.findOne(courseId);
		User mentor = course.getMentor();
		if (mentor != null) {
			return this.findOne(mentor.getId());
		}
		return null;
	}

}
