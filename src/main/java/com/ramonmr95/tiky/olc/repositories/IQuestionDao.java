package com.ramonmr95.tiky.olc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ramonmr95.tiky.olc.entities.Exam;
import com.ramonmr95.tiky.olc.entities.Question;

public interface IQuestionDao extends CrudRepository<Question, Long>{

	public List<Question> findByExam(Exam exam);
	
}
