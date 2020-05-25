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

import com.ramonmr95.tiky.olc.entities.Address;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.parsers.JsonParser;
import com.ramonmr95.tiky.olc.services.interfaces.IAddressService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	private IAddressService addressService;

	private JsonParser parser = new JsonParser();

	@GetMapping("/list")
	public ResponseEntity<?> list() {
		List<Address> addresses = this.addressService.findAll();

		if (addresses != null) {
			return new ResponseEntity<>(addresses, HttpStatus.OK);
		}
		return new ResponseEntity<>(this.parser.parseToMap("errors", "There are not any addresses on the db"),
				HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<?> getAddress(@RequestParam Long id) {
		try {
			Address address = this.addressService.findOne(id);
			return new ResponseEntity<>(address, HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/create")
	public ResponseEntity<?> createAddress(@RequestBody Address address) {
		try {
			Address newAddress = this.addressService.save(address);
			return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
		} catch (EntityValidationException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<?> updateAddress(@RequestBody Address address, @RequestParam Long id) {
		Address updatedAddress;
		try {
			updatedAddress = this.addressService.update(address, id);
			return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
		} catch (EntityValidationException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping
	public ResponseEntity<?> deleteAddress(@RequestParam Long id) {
		try {
			this.addressService.delete(id);
			return new ResponseEntity<>(this.parser.parseToMap("address", "Deleted address with id: " + id),
					HttpStatus.NO_CONTENT);
		} catch (DataNotFoundException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/address")
	public ResponseEntity<?> findUserByAddress(@RequestParam Long user_id) {
		Address address = this.addressService.findAddressByUserId(user_id);
		if (address != null) {
			return new ResponseEntity<>(address, HttpStatus.OK);
		}
		return new ResponseEntity<>(this.parser.parseToMap("errors", "Cannot find any user with id: " + user_id),
				HttpStatus.BAD_REQUEST);
	}
}
