package com.ramonmr95.tiky.olc.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ramonmr95.tiky.olc.entities.Address;

public interface IAddressDao extends CrudRepository<Address, Long> {

	@Query(value = "SELECT a FROM Address a WHERE a.id=?1")
	public Address findAddressByUserId(Long id);

}
