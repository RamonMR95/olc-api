package com.ramonmr95.tiky.olc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Answer;
import com.ramonmr95.tiky.olc.entities.Question;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.repositories.IAnswerDao;
import com.ramonmr95.tiky.olc.services.interfaces.IAnswerService;

@Service
public class AnswerServiceImpl implements IAnswerService {

	@Autowired
	private IAnswerDao answerDao;

	@Transactional
	@Override
	public Answer createAnswer(Answer answer) {
		return this.answerDao.save(answer);
	}

	@Transactional(readOnly = true)
	@Override
	public Answer findOne(Long id) throws DataNotFoundException {
		if (id != null) {
			return this.answerDao.findById(id)
					.orElseThrow(() -> new DataNotFoundException("Answer with id: " + id + " could not be found."));
		}
		throw new DataNotFoundException("Error answer id is required");
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Answer> findByQuestion(Question question) {
		return this.answerDao.findByQuestion(question);
	}
	
	@Transactional
	@Override
	public void delete(Long id) throws DataNotFoundException {
		Answer answer = this.findOne(id);
		this.answerDao.delete(answer);
	}

}
