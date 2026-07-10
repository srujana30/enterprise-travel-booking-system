package com.travel.bookingservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;


@Data
public class BookingRequest {

    @NotBlank(message = "Passenger name is required")
    private String passengerName;

    @NotBlank(message = "Flight Number name is required")
    private String flightNumber;

    @NotBlank(message = "Hotel Name name is required")
    private String hotelName;
    private String source;
    private String destination;
    private LocalDate travelDate;
}
