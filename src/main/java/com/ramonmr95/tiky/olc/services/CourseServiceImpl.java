package com.ramonmr95.tiky.olc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Course;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.repositories.ICourseDao;
import com.ramonmr95.tiky.olc.services.interfaces.ICourseService;
import com.ramonmr95.tiky.olc.services.interfaces.IUserService;
import com.ramonmr95.tiky.olc.validators.EntityValidator;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private ICourseDao courseDao;

	@Autowired
	private IUserService userService;

	private EntityValidator<Course> entityValidator = new EntityValidator<>();

	@Transactional(readOnly = true)
	@Override
	public List<Course> findAll() {
		return (List<Course>) this.courseDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Course findOne(Long id) throws DataNotFoundException {
		if (id != null) {
			return this.courseDao.findById(id)
					.orElseThrow(() -> new DataNotFoundException("Course with id: " + id + " could not be found."));
		}
		throw new DataNotFoundException("Error course id is required");
	}

	@Transactional
	@Override
	public Course save(Course course) throws EntityValidationException {
		if (this.entityValidator.isEntityValid(course)) {
			return this.courseDao.save(course);
		}
		throw new EntityValidationException(this.entityValidator.getEntityValidationErrorsString(course));
	}

	@Transactional
	@Override
	public Course update(Course course, Long id) throws DataNotFoundException, EntityValidationException {
		Course updatedCourse = this.findOne(id);
		
		if (this.entityValidator.isEntityValid(course)) {
			updatedCourse.setCourseName(course.getCourseName());
			updatedCourse.setYearStart(course.getYearStart());
			updatedCourse.setYearEnd(course.getYearEnd());
			updatedCourse.setSchedule(course.getSchedule());
			return this.courseDao.save(updatedCourse);
		}
		throw new EntityValidationException(this.entityValidator.getEntityValidationErrorsString(course));
	}

	@Transactional(readOnly = true)
	@Override
	public void delete(Long id) throws DataNotFoundException {
		this.findOne(id);
		this.courseDao.deleteById(id);
	}

	@Override
	public List<Course> findCoursesByUserId(Long id) throws DataNotFoundException {
		this.userService.findOne(id);
		return this.courseDao.findCoursesByUserId(id);
	}

}
