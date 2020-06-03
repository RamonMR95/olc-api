package com.ramonmr95.tiky.olc.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramonmr95.tiky.olc.entities.Role;
import com.ramonmr95.tiky.olc.entities.User;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.exceptions.UserAlreadyEnrolledException;
import com.ramonmr95.tiky.olc.parsers.JsonParser;
import com.ramonmr95.tiky.olc.services.interfaces.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private IUserService userServiceImpl;

	private JsonParser parser = new JsonParser();
	
	private static final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\"
			+ "x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]"
			+ "(?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@GetMapping("/list")
	public ResponseEntity<?> list() {
		List<User> users = this.userServiceImpl.findAll();

		if (users != null) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
		return new ResponseEntity<>(this.parser.parseToMap("errors", "There are not any users on the db"),
				HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<?> getUser(@RequestParam Long id) {
		try {
			User user = this.userServiceImpl.findOne(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/create", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<?> createUser(@RequestBody User user) {
		try {
			user.setPassword(passwordEncode.encode(user.getPassword()));
			User newUser = this.userServiceImpl.save(user);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} catch (EntityValidationException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User user, @RequestParam Long id) {
		try {
			User updatedUser = this.userServiceImpl.update(user, id);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} catch (EntityValidationException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping
	public ResponseEntity<?> deleteUser(@RequestParam Long id) {
		try {
			this.userServiceImpl.delete(id);
			return new ResponseEntity<>(this.parser.parseToMap("user", "Deleted user with id: " + id),
					HttpStatus.NO_CONTENT);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/role")
	public ResponseEntity<?> getRoleByUserId(@RequestParam Long user_id) {
		try {
			Role role = this.userServiceImpl.findRoleByUserId(user_id);
			return new ResponseEntity<>(role, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseToMap("errors", "Cannot find any user with id: " + user_id),
					HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/marks")
	public ResponseEntity<?> getMarksByUserIdAndCourseYearStart(@RequestParam Long id,
			@RequestParam String year_start) {
		try {
			Map<String, Double> marksMap = this.userServiceImpl.findMarksAndSubjectsByStudentIdAndYearStart(id,
					year_start);
			return new ResponseEntity<>(marksMap, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseToMap("errors", "Cannot find any user with id: " + id),
					HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/mentor")
	public ResponseEntity<?> getMentorByCourseId(@RequestParam(name = "course_id") Long courseId) {
		User user;
		try {
			user = this.userServiceImpl.findMentorByCourseId(courseId);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseToMap("errors", e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}


	@GetMapping("/email")
	public ResponseEntity<?> findUserByEmail(@RequestParam String email) {
		if (!email.matches(emailRegex)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User user = this.userServiceImpl.findByEmail(email);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/enroll")
	public ResponseEntity<?> enroll(@RequestParam(name = "user_id") Long userId,
			@RequestParam(name = "course_id") Long courseId) {
		try {
			this.userServiceImpl.enroll(userId, courseId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		} catch (UserAlreadyEnrolledException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/photo")
	public ResponseEntity<?> updatePhoto(@RequestParam(name = "user_id") Long userId, @RequestParam String url) {
		try {
			User user = this.userServiceImpl.updatePhoto(userId, url);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		} catch (EntityValidationException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

}
