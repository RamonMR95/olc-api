package com.ramonmr95.tiky.olc.services;

import java.util.List;

import com.ramonmr95.tiky.olc.entities.Exam;

public interface IExamService {

	public List<Exam> findAll();

	public Exam findOne(Long id);

	public Exam save(Exam exam);

	public void delete(Long id);
	
}
