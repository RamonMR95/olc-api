package com.ramonmr95.tiky.olc.services;

import java.util.List;

import com.ramonmr95.tiky.olc.entities.Address;

public interface IAddressService {

	public List<Address> findAll();

	public Address findOne(Long id);

	public Address save(Address address);

	public void delete(Long id);
}
