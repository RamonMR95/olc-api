package com.ramonmr95.tiky.olc.services.interfaces;

import java.util.List;

import com.ramonmr95.tiky.olc.entities.News;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;

public interface INewsService {

	public List<News> getLast5News();
	
	public News createNews(News news) throws EntityValidationException;
}
