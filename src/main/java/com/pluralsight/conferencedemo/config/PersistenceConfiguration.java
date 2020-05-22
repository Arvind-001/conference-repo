package com.pluralsight.conferencedemo.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * This example shows how to override configuration. 
 * 
 * This configuration class can be used to customize and create changes or
 * configuration in our persistence tier.
 * 
 * Any methods that we define in here can return bean definitions and that will get 
 * stored in the Spring context.
 * 
 * Below example shows how to override the data source being provided by the
 * Spring Data JPA starter.
 * 
 */
@Configuration
public class PersistenceConfiguration {

// 	@Bean
// 	public DataSource getDataSource() {
		
// 		DataSourceBuilder builder = DataSourceBuilder.create();
// 		builder.url("jdbc:postgresql://localhost:5432/postgres");
// 		builder.username("postgres");
// 		builder.password("postgres");
// 		System.out.println("This is customized datasource class created ...");
// 		return builder.build();
		
// 	}
}
