package com.ramonmr95.tiky.olc.services.interfaces;

import java.util.List;

import com.ramonmr95.tiky.olc.entities.Exam;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;

public interface IExamService {

	public List<Exam> findAll();

	public Exam findOne(Long id) throws DataNotFoundException;

	public Exam save(Exam exam) throws EntityValidationException;
	
	public Exam update(Exam exam, Long id) throws EntityValidationException, DataNotFoundException;

	public void delete(Long id) throws DataNotFoundException;

	public List<Exam> findAllExamByCourseIdAndSubjectId(Long course_id, Long subject_id);

}
