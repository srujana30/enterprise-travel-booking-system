package com.travel.bookingservice.dto;

import lombok.Data;

@Data
public class CancelBookingResponse {
    private String bookingId;
    private String bookingStatus;
    private String message;
}
