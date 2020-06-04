package com.ramonmr95.tiky.olc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramonmr95.tiky.olc.entities.UserExam;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.exceptions.ExamAlreadyDoneByStudentException;
import com.ramonmr95.tiky.olc.parsers.JsonParser;
import com.ramonmr95.tiky.olc.services.interfaces.IUserExamService;

@RestController
@RequestMapping("/api/user-exam")
public class UserExamController {

	@Autowired
	private IUserExamService userExamService;

	private JsonParser parser = new JsonParser();

	@PostMapping
	public ResponseEntity<?> submitExam(@RequestBody UserExam userExam) {
		try {
			UserExam newUserExam = this.userExamService.submit(userExam);
			return new ResponseEntity<>(newUserExam, HttpStatus.OK);
		} catch (EntityValidationException | ExamAlreadyDoneByStudentException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

}
