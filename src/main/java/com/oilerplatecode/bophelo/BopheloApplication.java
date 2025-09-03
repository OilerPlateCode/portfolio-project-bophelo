package com.oilerplatecode.bophelo;

import com.oilerplatecode.bophelo.run.RunRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.oilerplatecode.bophelo.run.Location;
import com.oilerplatecode.bophelo.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class BopheloApplication {

	static final Logger log = LoggerFactory.getLogger(BopheloApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BopheloApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(RunRepository runRepository) {
		return args -> {
			Run run = new Run(1, "Test Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5000, Location.INDOOR);
			log.info("Running " + run);
			log.info("something changed");
			runRepository.create(run);
		};
	}

}
