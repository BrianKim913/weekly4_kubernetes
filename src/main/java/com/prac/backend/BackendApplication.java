package com.prac.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@RestController
public class BackendApplication {
	private static final Logger logger = LoggerFactory.getLogger(BackendApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

		// Allow all origins
		config.addAllowedOrigin("*");

		// Allow all headers
		config.addAllowedHeader("*");

		// Allow all methods (GET, POST, PUT, DELETE, etc.)
		config.addAllowedMethod("*");

		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}

	@GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
	public String helloWorld() {
		logger.info("Request received");
		return "<h1>Test-Prep-Ai.com</h1><h2>당신의 시험을 AI가 책임집니다!</h2>";
	}

	@GetMapping("/api/loadtest")
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
