package com.code4beer.rest;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code4beer.neo4j.NeoDao;

@RestController
public class RestControl {

	private static final Logger log = LoggerFactory.getLogger(RestControl.class);

	private NeoDao dao;

	public RestControl(NeoDao neoDao) {
		log.info("Building RestControl Class");
		this.dao = neoDao;
	}

	@RequestMapping("/neo")
	public String neo(@RequestParam(value = "name", defaultValue = "4j") String name) {
		return "neo" + name;
	}

	@RequestMapping("/movie")
	public Content movie(@RequestParam(value = "name", defaultValue = "Cloud Atlas") String name) {
		Object result = dao.movieDirector(name);
		return new Content(result);
	}

	@RequestMapping("/movieDirector")
	public Content movieMaker(@RequestParam(value = "name") String name) {
		ArrayList<String> result = new ArrayList<String>();
		result = dao.movieDirectorMovies(name);
		return new Content(result);
	}

}
