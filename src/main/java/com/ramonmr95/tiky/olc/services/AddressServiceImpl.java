package com.ramonmr95.tiky.olc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Address;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.repositories.IAddressDao;
import com.ramonmr95.tiky.olc.services.interfaces.IAddressService;
import com.ramonmr95.tiky.olc.validators.EntityValidator;

@Service
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private IAddressDao addressDao;

	private EntityValidator<Address> entityValidator = new EntityValidator<>();

	@Transactional(readOnly = true)
	@Override
	public List<Address> findAll() {
		return (List<Address>) addressDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Address findOne(Long id) throws DataNotFoundException {
		if (id != null) {
			return this.addressDao.findById(id)
					.orElseThrow(() -> new DataNotFoundException("Address with id: " + id + " could not be found."));
		}
		throw new DataNotFoundException("Error address id is required");
	}

	@Transactional
	@Override
	public Address save(Address address) throws EntityValidationException {
		if (this.entityValidator.isEntityValid(address)) {
			return this.addressDao.save(address);
		}
		throw new EntityValidationException(this.entityValidator.getEntityValidationErrorsString(address));
	}

	@Transactional
	@Override
	public Address update(Address address, Long id) throws DataNotFoundException, EntityValidationException {
		Address updatedAddress = this.findOne(id);
		
		if (this.entityValidator.isEntityValid(address)) {
			updatedAddress.setStreet(address.getStreet());
			updatedAddress.setProvince(address.getProvince());
			updatedAddress.setCity(address.getCity());
			updatedAddress.setZip(address.getZip());
			updatedAddress.setCountry(address.getCountry());
			return this.addressDao.save(updatedAddress);
		}
		throw new EntityValidationException(this.entityValidator.getEntityValidationErrorsString(address));
	}

	@Transactional
	@Override
	public void delete(Long id) throws DataNotFoundException {
		this.findOne(id);
		this.addressDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Address findAddressByUserId(Long id) {
		return this.addressDao.findAddressByUserId(id);
	}

}
