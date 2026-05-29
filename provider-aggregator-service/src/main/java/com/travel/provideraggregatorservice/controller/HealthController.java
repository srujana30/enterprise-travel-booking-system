package com.travel.provideraggregatorservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/provideraggregator")
public class HealthController {

    @GetMapping("/health")
    public String healthCheck(){
        return "ProviderAggregatorService Running";
    }
}
