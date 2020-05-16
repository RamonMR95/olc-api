package com.ramonmr95.tiky.olc.services;

import java.util.List;

import com.ramonmr95.tiky.olc.entities.Course;

public interface ICourseService {

	public List<Course> findAll();

	public Course findOne(Long id);

	public Course save(Course course);

	public void delete(Long id);
}
