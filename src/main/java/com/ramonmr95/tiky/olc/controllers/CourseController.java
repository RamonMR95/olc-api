package com.ramonmr95.tiky.olc.controllers;

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

import com.ramonmr95.tiky.olc.entities.Course;
import com.ramonmr95.tiky.olc.entities.Subject;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.parsers.JsonParser;
import com.ramonmr95.tiky.olc.services.interfaces.ICourseService;
import com.ramonmr95.tiky.olc.services.interfaces.ISubjectService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	private ICourseService courseService;

	@Autowired
	private ISubjectService subjectService;

	private JsonParser parser = new JsonParser();

	@GetMapping("/list")
	public ResponseEntity<?> list() {
		List<Course> courses = this.courseService.findAll();

		if (courses != null) {
			return new ResponseEntity<>(courses, HttpStatus.OK);
		}
		return new ResponseEntity<>(this.parser.parseToMap("errors", "There are not any courses on the db"),
				HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<?> getCourse(@RequestParam Long id) {
		Course course;
		try {
			course = this.courseService.findOne(id);
			return new ResponseEntity<>(course, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/course")
	public ResponseEntity<?> getCourseByUserId(@RequestParam Long user_id) {
		List<Course> courses;
		try {
			courses = this.courseService.findCoursesByUserId(user_id);
			return new ResponseEntity<>(courses, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<?> createCourse(@RequestBody Course course) {
		try {
			Course newCourse = this.courseService.save(course);
			return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
		} catch (EntityValidationException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<?> updateCourse(@RequestBody Course course, @RequestParam Long id) {
		try {
			Course updatedCourse = this.courseService.update(course, id);
			return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		} catch (EntityValidationException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping
	public ResponseEntity<?> deleteCourse(@RequestParam Long id) {
		try {
			this.courseService.delete(id);
			return new ResponseEntity<>(this.parser.parseToMap("course", "Deleted course with id: " + id),
					HttpStatus.NO_CONTENT);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/subjects")
	public ResponseEntity<?> getSubjectsByCourseId(@RequestParam(value = "course_id") Long id) {
		List<Subject> subjects;
		try {
			subjects = this.subjectService.findSubjectByCourseId(id);
			if (!subjects.isEmpty()) {
				return new ResponseEntity<>(subjects, HttpStatus.OK);
			}
			return new ResponseEntity<>(
					this.parser.parseToMap("errors", "The course with id: " + id + " does not have any subject."),
					HttpStatus.NOT_FOUND);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(
					this.parser.parseToMap("errors", "The course with id: " + id + " does not exist."),
					HttpStatus.NOT_FOUND);
		}

	}

}
