package com.ramonmr95.tiky.olc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ramonmr95.tiky.olc.entities.Answer;
import com.ramonmr95.tiky.olc.entities.Question;

public interface IAnswerDao extends CrudRepository<Answer, Long>{

	List<Answer> findByQuestion(Question question);
	
}
