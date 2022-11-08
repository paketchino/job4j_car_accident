package ru.job4j.accident;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class Job4jCarAccidentApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Job4jCarAccidentApplication.class);
	}

	@Bean
	public SpringLiquibase liquibase(DataSource ds) {
		SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setChangeLog("db/changelog/liquibase-changeLog.xml");
		springLiquibase.setDataSource(ds);
		return springLiquibase;
	}

	public static void main(String[] args) {
		SpringApplication.run(Job4jCarAccidentApplication.class, args);
	}

}
