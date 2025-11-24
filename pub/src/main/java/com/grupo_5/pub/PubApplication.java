package com.grupo_5.pub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.grupo_5.pub.model")
@EnableJpaRepositories(basePackages = "com.grupo_5.pub.repository")
public class PubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PubApplication.class, args);
	}
}
