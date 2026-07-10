package com.travel.bookingservice.dto;

import lombok.Data;

@Data
public class BookingResponse {
    private String bookingId;
    private String passengerName;
    private String bookingStatus;
    private Double totalPrice;
    private String message;
}
