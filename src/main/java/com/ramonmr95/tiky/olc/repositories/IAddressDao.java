package com.ramonmr95.tiky.olc.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ramonmr95.tiky.olc.entities.Address;

public interface IAddressDao extends CrudRepository<Address, Long>{

}
