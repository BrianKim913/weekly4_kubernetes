package com.prac.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@RestController
public class BackendApplication {
	private static final Logger logger = LoggerFactory.getLogger(BackendApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	private EventRepository eventRepository;
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
		String requestId = UUID.randomUUID().toString().substring(0, 8);
		logger.info("Load test request received [ID: {}] through /api/loadtest path", requestId);

		long timestamp = System.currentTimeMillis();
		long iterations = 800000 + (timestamp % 400000); // Vary between 800K-1.2M iterations

		long startTime = System.nanoTime();
		double result = 0;

		for (int i = 0; i < iterations; i++) {
			result += (long) (Math.sqrt(i) * Math.sin(i * 0.01));
		}
		long duration = (System.nanoTime() - startTime) / 1000000;
		logger.info("Load test [ID: {}] completed in {}ms - Result: {:.2f}, Iterations: {}",
				requestId, duration, result, iterations);
		return String.format("로드테스트 [%s] - 시간: %d ms, 반복: %d, 결과: %.2f",
				requestId, duration, iterations, result);
	}

	@PostMapping("/api/submit")
	public Event submit(@RequestBody Event event) {
		logger.info("Submitting event: {}", event.getTitle());
		return eventRepository.save(event);
	}

	@GetMapping("/api/retrieve")
	public List<Event> retrieve() {
		logger.info("Retrieving all events");
		return eventRepository.findAll();
	}
}
