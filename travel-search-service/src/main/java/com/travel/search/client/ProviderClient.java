package com.travel.search.client;

import com.travel.search.model.FlightResponseProvider;
import com.travel.search.model.HotelResponseProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ProviderClient {

    private final RestTemplate restTemplate;

    public ProviderClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${provider.service.base-url}")
    private String providerBaseUrl;

    public List<FlightResponseProvider> getFlightsFromProvider(String source, String destination, LocalDateTime travelDate){

        String url = providerBaseUrl+
                "/airline/provider/flights?source="+source+
                "&destination="+destination+
                "&travelDate="+travelDate;
        ResponseEntity<List<FlightResponseProvider>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<FlightResponseProvider>>() {
                });
         return response.getBody();
    }

    public List<HotelResponseProvider> getHotelsFromProvider(String destination, LocalDateTime travelDate){
        String url = providerBaseUrl+
                "/airline/provider/hotels?destination="+destination+
                "&travelDate="+travelDate;
        ResponseEntity<List<HotelResponseProvider>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<HotelResponseProvider>>() {
        });
        return response.getBody();
    }
}
