package com.ramonmr95.tiky.olc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Exam;
import com.ramonmr95.tiky.olc.entities.User;
import com.ramonmr95.tiky.olc.entities.UserExam;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.exceptions.ExamAlreadyDoneByStudentException;
import com.ramonmr95.tiky.olc.repositories.IUserExamDao;
import com.ramonmr95.tiky.olc.services.interfaces.IExamService;
import com.ramonmr95.tiky.olc.services.interfaces.IUserExamService;
import com.ramonmr95.tiky.olc.services.interfaces.IUserService;
import com.ramonmr95.tiky.olc.validators.EntityValidator;

@Service
public class UserExamServiceImpl implements IUserExamService {

	@Autowired
	private IUserExamDao userExamDao;

	@Autowired
	private IExamService examService;

	@Autowired
	private IUserService userService;

	private EntityValidator<UserExam> validator = new EntityValidator<>();


	@Transactional
	@Override
	public UserExam submit(UserExam userExam)
			throws EntityValidationException, DataNotFoundException, ExamAlreadyDoneByStudentException {
		if (userExam.getExam() == null)
			throw new EntityValidationException("Exam is required");

		if (userExam.getUser() == null) {
			throw new EntityValidationException("User is required");
		}

		if (this.findExamByUserId(userExam.getUser().getId(), userExam.getExam().getId()) != null) {
			throw new ExamAlreadyDoneByStudentException("Exam named: " + userExam.getExam().getName()
					+ " was done already done by student with id : " + userExam.getUser().getId());
		}

		User user = this.userService.findOne(userExam.getUser().getId());
		Exam exam = this.examService.findOne(userExam.getExam().getId());
		userExam.setExam(exam);
		userExam.setUser(user);

		if (this.validator.isEntityValid(userExam)) {
			return this.userExamDao.save(userExam);
		}
		throw new EntityValidationException(this.validator.getEntityValidationErrorsString(userExam));
	}

	@Transactional(readOnly = true)
	@Override
	public UserExam findExamByUserId(Long userId, Long examId) throws DataNotFoundException {
		this.userService.findOne(userId);
		this.examService.findOne(examId);
		return this.userExamDao.findExamByUserId(userId, examId);
	}
}
