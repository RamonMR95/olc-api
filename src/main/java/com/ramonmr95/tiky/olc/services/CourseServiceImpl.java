package com.ramonmr95.tiky.olc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Course;
import com.ramonmr95.tiky.olc.repositories.ICourseDao;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private ICourseDao courseDao;

	@Transactional
	@Override
	public List<Course> findAll() {
		return (List<Course>) this.courseDao.findAll();
	}

	@Transactional
	@Override
	public Course findOne(Long id) {
		return this.courseDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public Course save(Course course) {
		return this.courseDao.save(course);
	}

	@Transactional(readOnly = true)
	@Override
	public void delete(Long id) {
		this.courseDao.deleteById(id);
	}

}
