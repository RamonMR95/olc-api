package com.ramonmr95.tiky.olc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramonmr95.tiky.olc.entities.UserExam;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.exceptions.ExamAlreadyDoneByStudentException;
import com.ramonmr95.tiky.olc.parsers.JsonParser;
import com.ramonmr95.tiky.olc.services.EmailService;
import com.ramonmr95.tiky.olc.services.interfaces.IUserExamService;

@RestController
@RequestMapping("/api/user-exam")
public class UserExamController {

	@Autowired
	private IUserExamService userExamService;

	@Autowired
	private EmailService emailService;

	private JsonParser parser = new JsonParser();

	@PostMapping
	public ResponseEntity<?> submitExam(@RequestBody UserExam userExam) {
		try {
			UserExam newUserExam = this.userExamService.submit(userExam);

			this.emailService.sendEmail(newUserExam.getUser().getEmail(),
					String.format("This is your Mark in exam %s", newUserExam.getExam().getName()),
					String.format("Hello %s, %n%nThe mark in exam %s is %.2f. %n%nGreetings.",
							newUserExam.getUser().getName(), newUserExam.getExam().getName(), newUserExam.getMark()));

			return new ResponseEntity<>(newUserExam, HttpStatus.OK);
		} catch (EntityValidationException | ExamAlreadyDoneByStudentException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/exam")
	public ResponseEntity<?> findExamByUserId(@RequestParam(name = "user_id") Long userId,
			@RequestParam(name = "exam_id") Long examId) {
		try {
			UserExam userExam = this.userExamService.findExamByUserId(userId, examId);
			if (userExam != null) {
				return new ResponseEntity<>(userExam, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

}
