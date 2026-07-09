package com.travel.search.service;

import com.travel.search.client.ProviderClient;
import com.travel.search.model.FlightResponseProvider;
import com.travel.search.model.HotelResponseProvider;
import com.travel.search.model.TravelSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TravelSearchService {
    
    @Autowired
    ProviderClient providerClient;

    public TravelSearchResponse search(String source, String destination, LocalDateTime travelDate){
        TravelSearchResponse travelSearchResponse = new TravelSearchResponse();
       // travelSearchResponse.setFlights(Arrays.asList("United Airlines","American Airlines"));
       // travelSearchResponse.setHotels(Arrays.asList("Hilton","Marriott"));

        List<FlightResponseProvider> flightResponse = new ArrayList<>();
        List<HotelResponseProvider> hotelResponse = new ArrayList<>();

        String message = "Search completed successfully";

        try {
            flightResponse = providerClient.getFlightsFromProvider(source, destination, travelDate);
        }catch(Exception e){
            message = "Flight provider unavailable";
        }
        try {
            hotelResponse = providerClient.getHotelsFromProvider(destination, travelDate);
        }catch (Exception e){
            if(!message.equals("Search completed successfully")){
                message =
                        "Flight and Hotel providers unavailable";
            }else {
                message = "Hotel provider unavailable";
            }
        }
        travelSearchResponse.setFlights(flightResponse);
        travelSearchResponse.setHotels(hotelResponse);
        travelSearchResponse.setMessage(message);

        return travelSearchResponse;
    }
}
