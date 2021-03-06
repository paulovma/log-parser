package com.quake.arena.logparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableReactiveMongoRepositories
@SpringBootApplication
public class LogParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogParserApplication.class, args);
	}

}
