package com.ramonmr95.tiky.olc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Subject;
import com.ramonmr95.tiky.olc.repositories.ISubjectDao;

@Service
public class SubjectServiceImpl implements ISubjectService {

	@Autowired
	private ISubjectDao subjectDao;

	@Transactional
	@Override
	public List<Subject> findAll() {
		return (List<Subject>) this.subjectDao.findAll();
	}

	@Transactional
	@Override
	public Subject findOne(Long id) {
		return this.subjectDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public Subject save(Subject subject) {
		return this.subjectDao.save(subject);
	}

	@Transactional(readOnly = true)
	@Override
	public void delete(Long id) {
		this.subjectDao.deleteById(id);
	}

}
