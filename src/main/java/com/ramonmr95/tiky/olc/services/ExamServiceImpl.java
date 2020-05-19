package com.ramonmr95.tiky.olc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Exam;
import com.ramonmr95.tiky.olc.repositories.IExamDao;

@Service
public class ExamServiceImpl implements IExamService {

	@Autowired
	private IExamDao examDao;

	@Transactional
	@Override
	public List<Exam> findAll() {
		return (List<Exam>) this.examDao.findAll();
	}

	@Transactional
	@Override
	public Exam findOne(Long id) {
		return this.examDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public Exam save(Exam exam) {
		return this.examDao.save(exam);
	}

	@Transactional(readOnly = true)
	@Override
	public void delete(Long id) {
		this.examDao.deleteById(id);
	}

}
