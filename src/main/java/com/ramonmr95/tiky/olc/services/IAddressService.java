package com.ramonmr95.tiky.olc.services;

import java.util.List;

import com.ramonmr95.tiky.olc.entities.Address;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;

public interface IAddressService {

	public List<Address> findAll();

	public Address findOne(Long id) throws DataNotFoundException;

	public Address save(Address address) throws EntityValidationException;

	public void delete(Long id) throws DataNotFoundException;
	
	public Address findAddressByUserId(Long id);

}
