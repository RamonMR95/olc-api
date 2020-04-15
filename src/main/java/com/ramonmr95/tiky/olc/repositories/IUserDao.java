package com.ramonmr95.tiky.olc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ramonmr95.tiky.olc.entities.User;

@Repository
public interface IUserDao extends CrudRepository<User, Long> {

	@Query(nativeQuery = true, value = "SELECT e.mark, sb.subject_name " + "FROM exams e "
			+ "INNER JOIN courses co ON co.id = e.course_id " + "INNER JOIN users us ON us.course_id = co.id "
			+ "INNER JOIN subjects sb ON sb.id = e.subject_id " + "WHERE us.id = ?1 AND co.year_start = ?2")
	public List<Object[]> findMarkAndSubjectsByStudentIdAndYearStart(Long id, String year);
	
}
