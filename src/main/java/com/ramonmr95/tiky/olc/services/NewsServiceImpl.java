package com.ramonmr95.tiky.olc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonmr95.tiky.olc.entities.News;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.repositories.INewsDao;
import com.ramonmr95.tiky.olc.services.interfaces.INewsService;
import com.ramonmr95.tiky.olc.validators.EntityValidator;

@Service
public class NewsServiceImpl implements INewsService {

	@Autowired
	private INewsDao newsDao;
	
	private EntityValidator<News> validator = new EntityValidator<>();

	@Transactional(readOnly = true)
	@Override
	public List<News> getLast5News() {
		return this.newsDao.getLast5News();
	}

	@Transactional
	@Override
	public News createNews(News news) throws EntityValidationException {
		if (this.validator.isEntityValid(news)) {
			return this.newsDao.save(news);
		}
		throw new EntityValidationException(this.validator.getEntityValidationErrorsString(news));
	}

}
