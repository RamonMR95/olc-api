package com.ramonmr95.tiky.olc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ramonmr95.tiky.olc.entities.Subject;

public interface ISubjectDao extends CrudRepository<Subject, Long> {

	@Query(nativeQuery = true, value = "SELECT DISTINCT(sub.subject_name) FROM exams ex INNER JOIN subjects sub ON sub.id = ex.subject_id WHERE ex.course_id = ?1")
	public List<String> findSubjectByCourseId(Long id);

	
}
