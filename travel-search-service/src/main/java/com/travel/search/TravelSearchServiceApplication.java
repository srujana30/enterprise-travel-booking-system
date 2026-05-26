package com.travel.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TravelSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelSearchServiceApplication.class, args);
	}

    @RestController
    @RequestMapping("/api/travel")
    public static class TravelController {

        @GetMapping("/health")
        public String health() {
            return "Travel Search Service Running";
        }
    }
}
