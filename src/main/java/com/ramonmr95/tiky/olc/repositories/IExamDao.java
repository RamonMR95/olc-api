package com.ramonmr95.tiky.olc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ramonmr95.tiky.olc.entities.Answer;
import com.ramonmr95.tiky.olc.entities.Exam;
import com.ramonmr95.tiky.olc.entities.Question;

public interface IExamDao extends CrudRepository<Exam, Long> {

	@Query("SELECT e FROM Exam e WHERE course_id = ?1 AND subject_id = ?2")
	public List<Exam> findAllExamByCourseIdAndSubjectId(Long course_id, Long subject_id);
	
	@Query("SELECT a FROM Answer a WHERE a.question.id = ?1")
	public List<Answer> findAllAnswersGivenQuestionId(Long id);
	
	@Query("SELECT q FROM Question q WHERE q.exam.id = ?1")
	public List<Question> findAllQuestionByExamId(Long id);
	
	

}
