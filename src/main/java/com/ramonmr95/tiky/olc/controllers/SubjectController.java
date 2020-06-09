package com.ramonmr95.tiky.olc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramonmr95.tiky.olc.entities.Subject;
import com.ramonmr95.tiky.olc.entities.Topic;
import com.ramonmr95.tiky.olc.entities.TopicContent;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.parsers.JsonParser;
import com.ramonmr95.tiky.olc.services.interfaces.ISubjectService;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

	@Autowired
	private ISubjectService subjectService;

	private JsonParser parser = new JsonParser();

	@GetMapping("/list")
	public ResponseEntity<?> list() {
		List<Subject> sbj = this.subjectService.findAll();

		if (sbj != null) {
			return new ResponseEntity<>(sbj, HttpStatus.OK);
		}
		return new ResponseEntity<>(this.parser.parseToMap("errors", "There are not any subjects on the db"),
				HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<?> getSubject(@RequestParam Long id) {
		try {
			Subject sbj = this.subjectService.findOne(id);
			return new ResponseEntity<>(sbj, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/name")
	public ResponseEntity<?> getSubjectName(@RequestParam String name) {
		try {
			Subject sbj = this.subjectService.findByName(name);
			return new ResponseEntity<>(sbj, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/create")
	public ResponseEntity<?> createSubject(@RequestBody Subject sbj) {
		try {
			Subject newSubject = this.subjectService.save(sbj);
			return new ResponseEntity<>(newSubject, HttpStatus.CREATED);
		} catch (EntityValidationException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<?> updateSubject(@RequestBody Subject sbj, @RequestParam Long id) {
		Subject updateSubject;
		try {
			updateSubject = this.subjectService.update(sbj, id);
			return new ResponseEntity<>(updateSubject, HttpStatus.OK);
		} catch (EntityValidationException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping
	public ResponseEntity<?> deleteSubject(@RequestParam Long id) {
		try {
			this.subjectService.delete(id);
			return new ResponseEntity<>(this.parser.parseToMap("exam", "Deleted subject with id: " + id),
					HttpStatus.NO_CONTENT);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@Transactional(readOnly = true)
	@GetMapping("/topic")
	public ResponseEntity<?> listTopicsBySubjectName(@RequestParam(name = "name") String name) {
		try {
			List<Topic> listTheme = this.subjectService.findTopicBySubjectName(name);
			return new ResponseEntity<>(listTheme, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

	@Transactional(readOnly = true)
	@GetMapping("/topicContent")
	public ResponseEntity<?> listTopicsContentByTopicId(@RequestParam(name = "topic_id") Long id) {
		List<TopicContent> listTheme = this.subjectService.findTopicContentByTopicId(id);
		return new ResponseEntity<>(listTheme, HttpStatus.OK);
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/topic/list")
	public ResponseEntity<?> listAllTopics() {
		List<Topic> listTheme = this.subjectService.findAllTopics();
		return new ResponseEntity<>(listTheme, HttpStatus.OK);
	}

}
