package com.ramonmr95.tiky.olc.services.interfaces;

import java.util.List;

import com.ramonmr95.tiky.olc.entities.Exam;
import com.ramonmr95.tiky.olc.entities.Question;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;

public interface IQuestionService {

	public Question createQuestion(Question question);
	
	public List<Question> findByExam(Exam exam);
	
	public void delete(Long id) throws DataNotFoundException;
	
	public Question findOne(Long id) throws DataNotFoundException;
}
