package com.assignment.employee.config;

import static org.mockito.Mockito.mock;

import javax.sql.DataSource;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@TestConfiguration
public class MyTestConfiguration {

	@Bean
    DataSource dataSource(){
		
		DataSource datasource=mock(DataSource.class);
		return datasource;
        //
    }
	
	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate() {
		/*
		 * JdbcTemplate jdbcTemplate= new JdbcTemplate();
		 * jdbcTemplate.setDataSource(dataSource);
		 */
		return new NamedParameterJdbcTemplate(dataSource());
		
	}
	
}
