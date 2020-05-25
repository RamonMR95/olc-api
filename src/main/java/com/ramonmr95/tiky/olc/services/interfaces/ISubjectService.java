package com.ramonmr95.tiky.olc.services.interfaces;

import java.util.List;

import com.ramonmr95.tiky.olc.entities.Subject;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;

public interface ISubjectService {

	public List<Subject> findAll();

	public Subject findOne(Long id) throws DataNotFoundException;

	public Subject save(Subject subject) throws EntityValidationException;

	public void delete(Long id) throws DataNotFoundException;

	public List<Subject> findSubjectByCourseId(Long id);

}
