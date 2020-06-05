package com.ramonmr95.tiky.olc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ramonmr95.tiky.olc.entities.News;

public interface INewsDao extends CrudRepository<News, Long> {

	@Query(nativeQuery = true, value = "SELECT * from news ORDER BY created_at DESC LIMIT 5")
	public List<News> getLast5News();
}
