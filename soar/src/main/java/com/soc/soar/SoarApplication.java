package com.soc.soar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SoarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoarApplication.class, args);
	}

}
