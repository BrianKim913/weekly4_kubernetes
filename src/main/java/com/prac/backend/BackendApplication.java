package com.prac.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@GetMapping("/")
	public String helloKubernates() {
		System.out.print("request");
		return "Hello, kubernates prac";
	}

	@GetMapping("/loadtest")
	public String loadTest() {
		System.out.print("request");
		long result =0;
		for (int i = 0; i < 1000000; i++) {
			result += (long) Math.sqrt(i);
		}
		return "로드테스트"+ result ;
	}
}
