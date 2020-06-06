package com.ramonmr95.tiky.olc.services.interfaces;

import com.ramonmr95.tiky.olc.entities.UserExam;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.exceptions.ExamAlreadyDoneByStudentException;
import com.ramonmr95.tiky.olc.exceptions.TeacherExamSubmitException;

public interface IUserExamService {

	public UserExam submit(UserExam userExam) throws EntityValidationException, DataNotFoundException, ExamAlreadyDoneByStudentException, TeacherExamSubmitException;

	public UserExam findExamByUserId(Long id, Long examId) throws DataNotFoundException;

}
