package com.ramonmr95.tiky.olc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Answer;
import com.ramonmr95.tiky.olc.entities.Exam;
import com.ramonmr95.tiky.olc.entities.Question;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.repositories.IExamDao;
import com.ramonmr95.tiky.olc.services.interfaces.ICourseService;
import com.ramonmr95.tiky.olc.services.interfaces.IExamService;
import com.ramonmr95.tiky.olc.services.interfaces.IUserService;
import com.ramonmr95.tiky.olc.validators.EntityValidator;

@Service
public class ExamServiceImpl implements IExamService {

	@Autowired
	private IExamDao examDao;

	@Autowired
	private IUserService userService;

	@Autowired
	private ICourseService courseService;

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
	public Exam update(Exam exam, Long id) throws EntityValidationException, DataNotFoundException {
		Exam updatedExam = this.findOne(id);

		if (this.entityValidator.isEntityValid(exam)) {
			updatedExam.setDate(exam.getDate());
//			updatedExam.setMark(exam.getMark());
			return this.examDao.save(updatedExam);
		}
		throw new EntityValidationException(this.entityValidator.getEntityValidationErrorsString(exam));
	}

	@Transactional
	@Override
	public void delete(Long id) throws DataNotFoundException {
		this.findOne(id);
		this.examDao.deleteById(id);
	}

	@Transactional
	@Override
	public List<Exam> findAllExamByCourseIdAndSubjectId(Long course_id, Long subject_id) {
		return this.examDao.findAllExamByCourseIdAndSubjectId(course_id, subject_id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Answer> findAllAnswersGivenQuestionId(Long id) {
		return this.examDao.findAllAnswersGivenQuestionId(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Question> findAllQuestionByExamId(Long id) throws DataNotFoundException {
		this.findOne(id);
		return this.examDao.findAllQuestionByExamId(id);
	}

	@Override
	public List<Exam> findAllExamsNotDoneByUserIdAndCourseId(Long user_id, Long course_id)
			throws DataNotFoundException {
		this.userService.findOne(user_id);
		this.courseService.findOne(course_id);
		return this.examDao.findAllExamsNotDoneByUserIdAndCourseId(user_id, course_id);

	}

}
