package com.ramonmr95.tiky.olc.services.interfaces;

import java.util.List;

import com.ramonmr95.tiky.olc.entities.Answer;
import com.ramonmr95.tiky.olc.entities.Question;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;

public interface IAnswerService {

	public Answer createAnswer(Answer answer);
	
	public Answer findOne(Long id) throws DataNotFoundException;
	
	public List<Answer> findByQuestion(Question question);
	
	public void delete(Long id) throws DataNotFoundException;
}
