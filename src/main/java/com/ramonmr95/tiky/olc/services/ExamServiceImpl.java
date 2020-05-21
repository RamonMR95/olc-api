package com.ramonmr95.tiky.olc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Exam;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.repositories.IExamDao;
import com.ramonmr95.tiky.olc.validators.EntityValidator;

@Service
public class ExamServiceImpl implements IExamService {

	@Autowired
	private IExamDao examDao;

	private EntityValidator<Exam> entityValidator = new EntityValidator<>();

	@Transactional(readOnly = true)
	@Override
	public List<Exam> findAll() {
		return (List<Exam>) examDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Exam findOne(Long id) throws DataNotFoundException {
		if (id != null) {
			return this.examDao.findById(id)
					.orElseThrow(() -> new DataNotFoundException("Exam with id: " + id + " could not be found."));
		}
		throw new DataNotFoundException("Error Exam id is required");
	}

	@Transactional
	@Override
	public Exam save(Exam exam) throws EntityValidationException {
		if (this.entityValidator.isEntityValid(exam)) {
			return this.examDao.save(exam);
		}
		throw new EntityValidationException(this.entityValidator.getEntityValidationErrorsString(exam));
	}

	@Transactional
	@Override
	public void delete(Long id) throws DataNotFoundException {
		this.findOne(id);
		this.examDao.deleteById(id);
	}

	@Override
	public List<Exam> findAllExamByCourseIdAndSubjectId(Long course_id, Long subject_id) {
		return this.examDao.findAllExamByCourseIdAndSubjectId(course_id, subject_id);
	}

}