package com.travel.search.service;

import com.travel.search.model.TravelSearchResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TravelSearchService {

    public TravelSearchResponse search(String source, String destination, LocalDateTime travelDate){

        TravelSearchResponse travelSearchResponse = new TravelSearchResponse();
       // List<String> flights = new ArrayList<>();
       // flights.set(0,"United Airlines");
        //flights.set(1,"American Airlines");
        //travelSearchResponse.setFlights(flights);

        travelSearchResponse.setFlights(Arrays.asList("United Airlines","American Airlines"));
        travelSearchResponse.setHotels(Arrays.asList("Hilton","Marriott"));

        return travelSearchResponse;
    }
}
