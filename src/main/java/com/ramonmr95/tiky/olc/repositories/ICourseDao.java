package com.ramonmr95.tiky.olc.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ramonmr95.tiky.olc.entities.Course;

public interface ICourseDao extends CrudRepository<Course, Long> {

}
