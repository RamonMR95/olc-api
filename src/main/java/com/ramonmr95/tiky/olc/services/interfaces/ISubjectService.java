package com.ramonmr95.tiky.olc.services.interfaces;

import java.util.List;

import com.ramonmr95.tiky.olc.entities.Subject;
import com.ramonmr95.tiky.olc.entities.Topic;
import com.ramonmr95.tiky.olc.entities.TopicContent;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;

public interface ISubjectService {

	public List<Subject> findAll();

	public Subject findOne(Long id) throws DataNotFoundException;

	public Subject save(Subject subject) throws EntityValidationException;
	
	public Subject update(Subject subject, Long id) throws DataNotFoundException, EntityValidationException;

	public void delete(Long id) throws DataNotFoundException;

	public List<String> findSubjectByCourseId(Long id) throws DataNotFoundException;
	
	public List<Topic> findTopicBySubjectName(String name) throws DataNotFoundException;
	
	public List<TopicContent> findTopicContentByTopicId(Long name);

	List<Topic> findAllTopics();
	
	public Subject findByName(String name) throws DataNotFoundException;

}
