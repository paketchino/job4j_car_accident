package ru.job4j.accident;

import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

@SpringBootApplication
public class Job4jCarAccidentApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Job4jCarAccidentApplication.class);
	}

	@Bean(name = "entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		return sessionFactory;
	}

	@Bean
	public SpringLiquibase liquibase(DataSource ds) {
		SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setChangeLog("classpath:liquibase-changeLog.xml");
		springLiquibase.setDataSource(ds);
		return springLiquibase;
	}

	public static void main(String[] args) {
		SpringApplication.run(Job4jCarAccidentApplication.class, args);
	}

}
