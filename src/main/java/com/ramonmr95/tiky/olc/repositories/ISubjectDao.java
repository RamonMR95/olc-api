package com.ramonmr95.tiky.olc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ramonmr95.tiky.olc.entities.Subject;

public interface ISubjectDao extends CrudRepository<Subject, Long> {

	@Query(nativeQuery = true, value = "SELECT sub.subject_name FROM courses co "
			+ "INNER JOIN exams ex ON ex.course_id= co.id "
			+ "INNER JOIN subjects sub ON sub.id=ex.subject_id WHERE co.id = ?1;")
	public List<Subject> findSubjectByCourseId(Long id);

}
