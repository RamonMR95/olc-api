package com.ramonmr95.tiky.olc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Exam;
import com.ramonmr95.tiky.olc.entities.Question;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.repositories.IQuestionDao;
import com.ramonmr95.tiky.olc.services.interfaces.IQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService {
	
	@Autowired
	private IQuestionDao questionDao;

	@Transactional
	@Override
	public Question createQuestion(Question question) {
		return this.questionDao.save(question);
	}
	
	@Transactional(readOnly = true)
	public List<Question> findByExam(Exam exam) {
		return this.questionDao.findByExam(exam);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Question findOne(Long id) throws DataNotFoundException {
		if (id != null) {
			return this.questionDao.findById(id)
					.orElseThrow(() -> new DataNotFoundException("Question with id: " + id + " could not be found."));
		}
		throw new DataNotFoundException("Error question id is required");
	}
	
	@Transactional
	@Override
	public void delete(Long id) throws DataNotFoundException {
		Question question = this.findOne(id);
		this.questionDao.delete(question);
	}

}
