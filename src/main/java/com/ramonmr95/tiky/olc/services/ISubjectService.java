package com.ramonmr95.tiky.olc.services;

import java.util.List;

import com.ramonmr95.tiky.olc.entities.Subject;

public interface ISubjectService {

	public List<Subject> findAll();

	public Subject findOne(Long id);

	public Subject save(Subject subject);

	public void delete(Long id);
}
