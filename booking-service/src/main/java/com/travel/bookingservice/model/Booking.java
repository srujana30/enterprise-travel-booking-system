package com.travel.bookingservice.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Booking {
    private String bookingId;
    private String passengerName;
    private String flightNumber;
    private String hotelName;
    private String source;
    private String destination;
    private LocalDate travelDate;
    private Double totalPrice;
    private String bookingStatus;
    private LocalDateTime bookingTime;
}
