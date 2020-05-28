package com.ramonmr95.tiky.olc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.Subject;
import com.ramonmr95.tiky.olc.exceptions.DataNotFoundException;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.repositories.ISubjectDao;
import com.ramonmr95.tiky.olc.services.interfaces.ICourseService;
import com.ramonmr95.tiky.olc.services.interfaces.ISubjectService;
import com.ramonmr95.tiky.olc.validators.EntityValidator;

@Service
public class SubjectServiceImpl implements ISubjectService {

	@Autowired
	private ISubjectDao subjectDao;
	
	@Autowired
	private ICourseService courseService;

	private EntityValidator<Subject> entityValidator = new EntityValidator<>();

	@Transactional(readOnly = true)
	@Override
	public List<Subject> findAll() {
		return (List<Subject>) this.subjectDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Subject findOne(Long id) throws DataNotFoundException {

		if (id != null) {
			return this.subjectDao.findById(id)
					.orElseThrow(() -> new DataNotFoundException("Subject with id: " + id + " could not be found."));
		}

		throw new DataNotFoundException("Error Subject id is required");
	}

	@Transactional
	@Override
	public Subject save(Subject subject) throws EntityValidationException {
		if (this.entityValidator.isEntityValid(subject)) {
			return this.subjectDao.save(subject);
		}
		throw new EntityValidationException(this.entityValidator.getEntityValidationErrorsString(subject));
	}

	@Transactional
	@Override
	public void delete(Long id) throws DataNotFoundException {
		this.findOne(id);
		this.subjectDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Subject> findSubjectByCourseId(Long id) throws DataNotFoundException {
		this.courseService.findOne(id);
		return this.subjectDao.findSubjectByCourseId(id);
	}

}
