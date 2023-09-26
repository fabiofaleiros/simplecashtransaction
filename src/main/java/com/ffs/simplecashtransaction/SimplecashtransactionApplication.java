package com.ffs.simplecashtransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SimplecashtransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplecashtransactionApplication.class, args);
	}

}
