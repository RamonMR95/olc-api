package com.ramonmr95.tiky.olc.services.interfaces;

import java.util.List;

import com.ramonmr95.tiky.olc.entities.Course;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;

public interface ICourseService {

	public List<Course> findAll();

	public Course findOne(Long id) throws DataNotFoundException;

	public Course save(Course course) throws EntityValidationException;
	
	public Course update(Course course, Long id) throws DataNotFoundException, EntityValidationException;

	public void delete(Long id) throws DataNotFoundException;
	
	public List<Course> findCoursesByUserId(Long id) throws DataNotFoundException;
}
