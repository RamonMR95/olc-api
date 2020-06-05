package com.ramonmr95.tiky.olc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramonmr95.tiky.olc.entities.News;
import com.ramonmr95.tiky.olc.exceptions.EntityValidationException;
import com.ramonmr95.tiky.olc.parsers.JsonParser;
import com.ramonmr95.tiky.olc.services.interfaces.INewsService;

@RestController
@RequestMapping("/api/news")
public class NewsController {

	@Autowired
	private INewsService newsService;

	private JsonParser parser = new JsonParser();

	@GetMapping("/latest")
	public ResponseEntity<?> getLast5News() {
		List<News> last5news = this.newsService.getLast5News();
		return new ResponseEntity<>(last5news, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createNews(@RequestBody News news) {
		try {
			News createdNews = this.newsService.createNews(news);
			return new ResponseEntity<>(createdNews, HttpStatus.OK);
		} catch (EntityValidationException e) {
			return new ResponseEntity<>(this.parser.parseJsonToMap(e.getMessage()), HttpStatus.BAD_REQUEST);
		}

	}

}
