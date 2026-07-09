package com.travel.search.model;

import lombok.Data;

import java.util.List;

@Data
public class TravelSearchResponse {

    private List<FlightResponseProvider> flights;
    private List<HotelResponseProvider> hotels;
    private String message;

}
