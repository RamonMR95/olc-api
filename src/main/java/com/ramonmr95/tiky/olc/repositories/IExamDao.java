package com.ramonmr95.tiky.olc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ramonmr95.tiky.olc.entities.Exam;

public interface IExamDao extends CrudRepository<Exam, Long> {

	@Query(nativeQuery = true, value = "Select * FROM exams WHERE course_id = ?1 AND subject_id = ?2")
	public List<Exam> findAllExamByCourseIdAndSubjectId(Long course_id, Long subject_id);

}
