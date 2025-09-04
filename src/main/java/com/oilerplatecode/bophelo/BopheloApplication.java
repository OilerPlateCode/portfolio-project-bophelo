package com.oilerplatecode.bophelo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class BopheloApplication {

	static final Logger log = LoggerFactory.getLogger(BopheloApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BopheloApplication.class, args);
	}

}
