package com.travel.provideraggregatorservice.service;

import com.travel.provideraggregatorservice.model.FlightResponseProvider;
import com.travel.provideraggregatorservice.model.HotelResponseProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService {

    public List<FlightResponseProvider> airlinesProviderService(String source, String destination, LocalDateTime travelDate){
        List<FlightResponseProvider> flightResponseProviderList = new ArrayList<>();
        FlightResponseProvider f1 = new FlightResponseProvider();
        f1.setFlightNumber("AA123");
        f1.setAirlineName("American Airline");
        f1.setDepartureTime(LocalDateTime.of(2026, 6, 1, 10, 0));
        f1.setArrivalTime(LocalDateTime.of(2026, 6, 1, 14, 0));
        f1.setPrice(180.0);
        flightResponseProviderList.add(0,f1);

        FlightResponseProvider f2 = new FlightResponseProvider();
        f2.setFlightNumber("UA345");
        f2.setAirlineName("United Airline");
        f2.setDepartureTime(LocalDateTime.of(2026, 6, 1, 10, 0));
        f2.setArrivalTime(LocalDateTime.of(2026, 6, 1, 18, 0));
        f2.setPrice(190.0);
        flightResponseProviderList.add(1,f2);
        return flightResponseProviderList;
    }

    public List<HotelResponseProvider> hotelsProviderService(String destination,LocalDateTime travelDate) {
        List<HotelResponseProvider> hotelResponseProviderList = new ArrayList<>();
        if (destination.equalsIgnoreCase("NYC")) {
            hotelResponseProviderList.add(new HotelResponseProvider("H11", "Hilton NYC", destination, 30.0, 4.5, 9));
            hotelResponseProviderList.add(new HotelResponseProvider("H12", "Hilton Times Square", destination, 200.0, 4, 7));
            hotelResponseProviderList.add(new HotelResponseProvider("H13", "Holiday Inn Manhattan", destination, 150.0, 3, 8));
        } else if (destination.equalsIgnoreCase("DFW")) {
            hotelResponseProviderList.add(new HotelResponseProvider("H14", "Hyatt Regency DFW", destination, 180.0, 5, 3));
            hotelResponseProviderList.add(new HotelResponseProvider("H15", "Marriott Airport", destination, 160.0, 4, 3));
            hotelResponseProviderList.add(new HotelResponseProvider("H16", "Budget Inn DFW", destination, 90.0, 3, 6));
        } else if (destination.equalsIgnoreCase("SFO")) {
            hotelResponseProviderList.add(new HotelResponseProvider("H17", "Fairmont San Francisco", destination, 300.0, 5, 8));
            hotelResponseProviderList.add(new HotelResponseProvider("H18", "Hilton Union Square", destination, 220.0, 4, 3));
            hotelResponseProviderList.add(new HotelResponseProvider("H19", "Ibis Budget SFO", destination, 120.0, 3, 7));
        } else {
            // DEFAULT CASE (VERY IMPORTANT)
            hotelResponseProviderList.add(new HotelResponseProvider("H20", "Generic Hotel 1", destination, 100.0, 3, 8));
            hotelResponseProviderList.add(new HotelResponseProvider("H21", "Generic Hotel 2", destination, 120.0, 4, 4));
        }
        return hotelResponseProviderList;
    }
}
