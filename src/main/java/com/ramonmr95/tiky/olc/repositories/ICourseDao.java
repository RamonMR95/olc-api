package com.ramonmr95.tiky.olc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ramonmr95.tiky.olc.entities.Course;

public interface ICourseDao extends CrudRepository<Course, Long> {

	@Query(nativeQuery = true, value = "SELECT c.id, c.course_name, c.year_start, c.year_end, c.schedule FROM courses c INNER JOIN users u ON u.course_id=c.id WHERE u.id = ?1")
	public List<Course> findCoursesByUserId(Long id);
	
	public Course findByCourseName (String name);
}
