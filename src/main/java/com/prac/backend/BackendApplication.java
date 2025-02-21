package com.prac.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@RestController
public class BackendApplication {
	private static final Logger logger = LoggerFactory.getLogger(BackendApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@GetMapping("/")
	public String helloKubernates() {
		logger.info("Request received");
		return "Hello, kubernates prac";
	}

	@GetMapping("/loadtest")
	public String loadTest() {
		logger.info("Load test Request received");
		long result =0;
		for (int i = 0; i < 1000000; i++) {
			result += (long) Math.sqrt(i);
		}
		logger.info("Load test result :" + result);
		return "로드테스트"+ result ;
	}
}
