package com.code4beer.neo4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.code4beer.config.ApplicationProps;

public class NeoDao {

	private static final Logger log = LoggerFactory.getLogger(NeoDao.class);

	private JdbcTemplate template;

	private ApplicationProps appProps;

	public NeoDao(ApplicationProps appProps, JdbcTemplate jdbcTemplate) {
		log.info("Building NeoDao");
		this.appProps = appProps;
		this.template = jdbcTemplate;
	}

	public Object movieDirector(String name) {
		List<Map<String, Object>> results = template.queryForList(appProps.getSqlQueryMovie(), name);
		Object returnValue = null;
		ArrayList<String> returnValueList = new ArrayList<>();
		if (results.size() > 1) {
			for (Map<String, Object> mapped : results) {
				for (Object res : mapped.values()) {
					returnValueList.add((String) res);
				}
			}
			returnValue = returnValueList;
		} else {
			for (Map<String, Object> mapped : results) {
				for (Object res : mapped.values()) {
					returnValue = (String) res;
				}
			}
		}
		return returnValue;
	}

	public ArrayList<String> movieDirectorMovies(String name) {
		List<String> names = new ArrayList<String>(Arrays.asList(name.split("\\*")));
		List<Map<String, Object>> results = null;
		ArrayList<String> returnValue = new ArrayList<String>();
		for (String dir : names) {
			results = template.queryForList(appProps.getSqlQueryDirectorMovies(), dir);
			for (Map<String, Object> mapped : results) {
				for (Object res : mapped.values()) {
					returnValue.add((String) res);
				}
			}
		}
		return returnValue;
	}
}
