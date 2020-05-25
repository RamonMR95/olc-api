package com.ramonmr95.tiky.olc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramonmr95.tiky.olc.dtos.UserDto;
import com.ramonmr95.tiky.olc.entities.Role;
import com.ramonmr95.tiky.olc.entities.User;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.parsers.JsonParser;
import com.ramonmr95.tiky.olc.services.interfaces.IUserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private IUserService userServiceImpl;

	private JsonParser parser = new JsonParser();

	@GetMapping("/list")
	public ResponseEntity<?> list() {
		List<User> users = this.userServiceImpl.findAll();

		if (users != null) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
		return new ResponseEntity<>(this.parser.parseToMap("errors", "There are not any users on the db"),
				HttpStatus.NOT_FOUND);
	}

	@GetMapping("/")
	public ResponseEntity<?> getUser(@RequestParam Long id) {
		try {
			User user = this.userServiceImpl.findOne(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/create", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
		try {
			User user = this.userServiceImpl.save(userDto);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (EntityValidationException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "/", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @RequestParam Long id) {
		User updatedUser;
		try {
			updatedUser = this.userServiceImpl.update(userDto, id);
		} catch (EntityValidationException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);

	}

	@DeleteMapping("/")
	public ResponseEntity<?> deleteUser(@RequestParam Long id) {
		try {
			this.userServiceImpl.delete(id);
			return new ResponseEntity<>(this.parser.parseToMap("user", "Deleted user with id: " + id),
					HttpStatus.NO_CONTENT);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("role")
	public ResponseEntity<?> getRoleByUserId(@RequestParam Long user_id) {
		try {
			Role role = this.userServiceImpl.findRoleByUserId(user_id);
			return new ResponseEntity<>(role, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseToMap("errors", "Cannot find any user with id: " + user_id),
					HttpStatus.BAD_REQUEST);
		}

	}

}
