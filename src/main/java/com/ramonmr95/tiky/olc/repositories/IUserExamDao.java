package com.ramonmr95.tiky.olc.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ramonmr95.tiky.olc.entities.UserExam;

public interface IUserExamDao extends CrudRepository<UserExam, Long>{

	@Query("SELECT e FROM UserExam e WHERE e.user.id = ?1 AND e.exam.id = ?2")
	public UserExam findExamByUserId(Long id, Long examId);
}
