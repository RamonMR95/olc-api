package com.ramonmr95.tiky.olc.services;

import java.util.List;

import com.ramonmr95.tiky.olc.entities.Exam;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;

public interface IExamService {

	public List<Exam> findAll();

	public Exam findOne(Long id) throws DataNotFoundException;

	public Exam save(Exam exam) throws EntityValidationException;

	public void delete(Long id) throws DataNotFoundException;

	public List<Exam> findAllExamByCourseIdAndSubjectId(Long course_id, Long subject_id);

}
