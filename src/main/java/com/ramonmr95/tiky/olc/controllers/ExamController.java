package com.ramonmr95.tiky.olc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramonmr95.tiky.olc.dtos.ExamQuestionAnswerDto;
import com.ramonmr95.tiky.olc.entities.Answer;
import com.ramonmr95.tiky.olc.entities.Exam;
import com.ramonmr95.tiky.olc.entities.Question;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.parsers.JsonParser;
import com.ramonmr95.tiky.olc.services.interfaces.IExamService;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

	@Autowired
	private IExamService examService;

	private JsonParser parser = new JsonParser();

	@GetMapping("/list")
	public ResponseEntity<?> list() {
		List<Exam> exams = this.examService.findAll();

		if (exams != null) {
			return new ResponseEntity<>(exams, HttpStatus.OK);
		}
		return new ResponseEntity<>(this.parser.parseToMap("errors", "There are not any exams on the db"),
				HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<?> getExam(@RequestParam Long id) {
		try {
			Exam exam = this.examService.findOne(id);
			return new ResponseEntity<>(exam, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/create")
	public ResponseEntity<?> createExam(@RequestBody Exam exam) {
		try {
			Exam newExam = this.examService.save(exam);
			return new ResponseEntity<>(newExam, HttpStatus.CREATED);
		} catch (EntityValidationException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<?> updateExam(@RequestBody Exam exam, @RequestParam Long id) {
		Exam updatedExam;
		try {
			updatedExam = this.examService.update(exam, id);
			return new ResponseEntity<>(updatedExam, HttpStatus.OK);
		} catch (EntityValidationException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping
	public ResponseEntity<?> deleteExam(@RequestParam Long id) {
		try {
			this.examService.delete(id);
			return new ResponseEntity<>(this.parser.parseToMap("exam", "Deleted exam with id: " + id),
					HttpStatus.NO_CONTENT);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/exam")
	public ResponseEntity<?> getExamsByCourseIdAndSubjectId(@RequestParam Long course_id,
			@RequestParam Long subject_id) {
		List<Exam> exams = this.examService.findAllExamByCourseIdAndSubjectId(course_id, subject_id);
		return new ResponseEntity<>(exams, HttpStatus.OK);
	}

	@GetMapping("/questions/answers")
	public ResponseEntity<?> getAllQuestionsAndAnswersByExamId(@RequestParam(name = "exam_id") Long examId) {
		try {
			List<ExamQuestionAnswerDto> exam = new ArrayList<>();
			List<Question> questions = this.examService.findAllQuestionByExamId(examId);
			for (Question question : questions) {
				List<Answer> answers = this.examService.findAllAnswersGivenQuestionId(question.getId());
				exam.add(new ExamQuestionAnswerDto(question.convertToDto(), answers));
			}
			return new ResponseEntity<>(exam, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/not-done")
	public ResponseEntity<?> listAllExamsNotDoneByUserIdAndCourseId(@RequestParam(name = "user_id") Long userId,
			@RequestParam(name = "course_id") Long courseId) {
		try {
			List<Exam> exams = this.examService.findAllExamsNotDoneByUserIdAndCourseId(userId, courseId);
			
			return new ResponseEntity<>(exams, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}	
	}

}
