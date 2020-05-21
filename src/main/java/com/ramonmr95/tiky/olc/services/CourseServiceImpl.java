package com.ramonmr95.tiky.olc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Course;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.repositories.ICourseDao;
import com.ramonmr95.tiky.olc.validators.EntityValidator;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private ICourseDao courseDao;

	private EntityValidator<Course> entityValidator = new EntityValidator<>();

	@Transactional
	@Override
	public List<Course> findAll() {
		return (List<Course>) this.courseDao.findAll();
	}

	@Transactional
	@Override
	public Course findOne(Long id) throws DataNotFoundException {
		if (id != null) {
			return this.courseDao.findById(id)
					.orElseThrow(() -> new DataNotFoundException("Course with id: " + id + " could not be found."));
		}
		throw new DataNotFoundException("Error course id is required");
	}

	@Transactional(readOnly = true)
	@Override
	public Course save(Course course) throws EntityValidationException {
		if (this.entityValidator.isEntityValid(course)) {
			return this.courseDao.save(course);
		}
		throw new EntityValidationException(this.entityValidator.getEntityValidationErrorsString(course));
	}

	@Transactional(readOnly = true)
	@Override
	public void delete(Long id) throws DataNotFoundException {
		this.findOne(id);
		this.courseDao.deleteById(id);
	}

}
