package com.travel.search.controller;

import com.travel.search.model.TravelSearchResponse;
import com.travel.search.service.TravelSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/travel")
public  class TravelController {

    private final TravelSearchService service;

    public TravelController(TravelSearchService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public String health() {
        return "Travel Search Service Running";
    }

    @GetMapping("/search")
    public TravelSearchResponse search(@RequestParam String source, @RequestParam String destination, @RequestParam LocalDateTime travelDate){
        return service.search(source,destination,travelDate);
    }
}

