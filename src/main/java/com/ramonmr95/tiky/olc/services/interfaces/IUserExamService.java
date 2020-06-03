package com.ramonmr95.tiky.olc.services.interfaces;

import com.ramonmr95.tiky.olc.entities.UserExam;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;

public interface IUserExamService {
	
	public UserExam submit(UserExam userExam) throws EntityValidationException, DataNotFoundException;
}
