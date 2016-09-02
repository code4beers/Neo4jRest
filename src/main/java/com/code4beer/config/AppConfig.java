package com.code4beer.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.code4beer.neo4j.NeoDao;
import com.code4beer.rest.RestControl;

@Configuration
public class AppConfig {

	@Bean
	public ApplicationProps appProps() {
		return new ApplicationProps();
	}

	@Bean
	public NeoDao neoDao(ApplicationProps appProps, JdbcTemplate jdbcTemplate) {
		return new NeoDao(appProps, jdbcTemplate);
	}

	@Bean
	public RestControl restControl(NeoDao neoDao) {
		return new RestControl(neoDao);
	}

	@Bean
	public DataSource dataSource(ApplicationProps appProps) {
		return new DriverManagerDataSource(
				"jdbc:neo4j:" + appProps.getNeoUrl() + appProps.getNeoUsername() + "," + appProps.getNeoPassword());
	}

}
