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

@SpringBootApplication
public class Job4jCarAccidentApplication extends SpringBootServletInitializer {

	@Bean(destroyMethod = "close")
	public SessionFactory sf() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure().build();
		return new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public static void main(String[] args) {
		SpringApplication.run(Job4jCarAccidentApplication.class, args);
	}

}
