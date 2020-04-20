package com.ramonmr95.tiky.olc.controllers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramonmr95.tiky.olc.entities.User;
import com.ramonmr95.tiky.olc.services.IUserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IUserService studentService;

	@GetMapping("/users")
	public ResponseEntity<?> list() {
		Map<String, Object> res = new ConcurrentHashMap<String, Object>();
		List<User> students = this.studentService.findAll();

		if (students != null) {
			return new ResponseEntity<>(students, HttpStatus.OK);
		}

		res.put("error", "Error there are not any user on the db");
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user")
	public ResponseEntity<?> student(@RequestParam Long id) {
		Map<String, Object> res = new ConcurrentHashMap<String, Object>();
		User student = this.studentService.findOne(id);

		if (student != null) {
			return new ResponseEntity<>(student, HttpStatus.OK);
		}

		res.put("error", "Error there is not any user with id: " + id);
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}

}
