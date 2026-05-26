package com.travel.search.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/travel")
public  class TravelController {

    @GetMapping("/health")
    public String health() {
        return "Travel Search Service Running";
    }
}

