package com.ramonmr95.tiky.olc.controllers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

import com.ramonmr95.tiky.olc.dtos.UserDto;
import com.ramonmr95.tiky.olc.entities.Address;
import com.ramonmr95.tiky.olc.entities.Course;
import com.ramonmr95.tiky.olc.entities.Role;
import com.ramonmr95.tiky.olc.entities.User;
import com.ramonmr95.tiky.olc.services.IAddressService;
import com.ramonmr95.tiky.olc.services.ICourseService;
import com.ramonmr95.tiky.olc.services.IRoleService;
import com.ramonmr95.tiky.olc.services.IUserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IUserService userServiceImpl;

	@Autowired
	private IRoleService rolServiceImpl;

	@Autowired
	private ICourseService courseServiceImpl;

	@Autowired
	private IAddressService addressServiceImpl;

	@GetMapping("/users")
	public ResponseEntity<?> list() {
		Map<String, Object> res = new ConcurrentHashMap<>();
		List<User> students = this.userServiceImpl.findAll();

		if (students != null) {
			return new ResponseEntity<>(students, HttpStatus.OK);
		}
		res.put("error", "Error there are not any users on the db");
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user")
	public ResponseEntity<?> getUser(@RequestParam Long id) {
		Map<String, Object> res = new ConcurrentHashMap<>();
		User user = this.userServiceImpl.findOne(id);

		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		res.put("error", "Error there is not any user with id: " + id);
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}

	@PostMapping(path = "/create", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
		Map<String, Object> res = new ConcurrentHashMap<>();
		User user = convertToUser(userDto);
		this.userServiceImpl.save(user);
		res.put("user", user);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PutMapping(path = "/user", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @RequestParam Long id) {
		Map<String, Object> res = new ConcurrentHashMap<>();
		User updatedUser = updateUserDataById(id, userDto);
		res.put("user", updatedUser);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@DeleteMapping("/user")
	public ResponseEntity<?> deleteUser(@RequestParam Long id) {
		Map<String, Object> res = new ConcurrentHashMap<>();
		User user = this.userServiceImpl.findOne(id);
		if (user != null) {
			this.userServiceImpl.delete(id);
			res.put("user", user);
			return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	private User convertToUser(UserDto userDto) {
		User user = userDto.convertToEntity();
		Course course = this.courseServiceImpl.findOne(userDto.getCourse().getId());
		Role role = this.rolServiceImpl.findOne(userDto.getRole().getId());
		Address address = userDto.getAddress().convertToEntity();

		user.setCourse(course);
		user.setRole(role);
		user.setAddress(address);
		return user;
	}

	private User updateUserDataById(Long id, UserDto userDto) {
		User oldUser = this.userServiceImpl.findOne(id);
		oldUser.setAddress(this.addressServiceImpl.findOne(userDto.getAddress().getId()));
		oldUser.setRole(this.rolServiceImpl.findOne(userDto.getRole().getId()));
		oldUser.setCourse(this.courseServiceImpl.findOne(userDto.getCourse().getId()));
		oldUser.setName(userDto.getName());
		oldUser.setSurName(userDto.getSurName());
		oldUser.setNickName(userDto.getNickName());
		oldUser.setEmail(userDto.getEmail());
		oldUser.setActive(userDto.isActive());
		this.userServiceImpl.save(oldUser);
		return oldUser;
	}

}
