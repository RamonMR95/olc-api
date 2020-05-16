package com.ramonmr95.tiky.olc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Address;
import com.ramonmr95.tiky.olc.repositories.IAddressDao;

@Service
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private IAddressDao addressDao;

	@Transactional(readOnly = true)
	@Override
	public List<Address> findAll() {
		return (List<Address>) addressDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Address findOne(Long id) {
		return this.addressDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Address save(Address address) {
		return this.addressDao.save(address);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		this.addressDao.deleteById(id);
	}

}
