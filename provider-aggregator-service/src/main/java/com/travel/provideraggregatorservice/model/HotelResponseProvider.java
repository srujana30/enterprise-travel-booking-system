package com.travel.provideraggregatorservice.model;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponseProvider {

    @JsonTypeId
    private String hotelID;
    private String hotelName;
    private String city;
    private double pricePerNight;
    private double rating;
    private int availableRooms;
}
