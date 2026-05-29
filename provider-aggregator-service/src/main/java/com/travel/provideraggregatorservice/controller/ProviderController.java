package com.travel.provideraggregatorservice.controller;

import com.travel.provideraggregatorservice.model.FlightResponseProvider;
import com.travel.provideraggregatorservice.model.HotelResponseProvider;
import com.travel.provideraggregatorservice.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ProviderController {

   @Autowired
    ProviderService providerService;

    @GetMapping("/airline/provider/flights")
    public List<FlightResponseProvider> airlinesProvider(@RequestParam String source, @RequestParam String destination, @RequestParam LocalDateTime travelDate){
        return providerService.airlinesProviderService(source,destination,travelDate);
    }

    @GetMapping("airline/provider/hotels")
    public List<HotelResponseProvider> hotelsProvider(@RequestParam String destination, @RequestParam LocalDateTime travelDate){
        return providerService.hotelsProviderService(destination,travelDate);
    }

}
