package com.travel.provideraggregatorservice.model;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightResponseProvider {
    @JsonTypeId
    private String flightNumber;
    private String airlineName;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Double price;
}
