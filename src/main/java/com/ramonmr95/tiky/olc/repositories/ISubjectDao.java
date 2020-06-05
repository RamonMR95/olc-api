package com.ramonmr95.tiky.olc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ramonmr95.tiky.olc.entities.Subject;
import com.ramonmr95.tiky.olc.entities.Topic;
import com.ramonmr95.tiky.olc.entities.TopicContent;

public interface ISubjectDao extends CrudRepository<Subject, Long> {

	@Query(nativeQuery = true, value = "SELECT DISTINCT(sub.subject_name) FROM exams ex INNER JOIN subjects sub ON sub.id = ex.subject_id WHERE ex.course_id = ?1")
	public List<String> findSubjectByCourseId(Long id);

	@Query(value = "SELECT t FROM Topic t INNER JOIN Subject sb ON sb.id = t.subject.id WHERE sb.subjectName LIKE ?1")
	public List<Topic> findTopicBySubjectName(String name);
	
	public Subject findBySubjectName(String name);
	
	@Query(value = "SELECT ct FROM TopicContent ct WHERE topic.id = ?1")
	public List<TopicContent> findAllTopicContentByTopicId(Long id);
	
}
