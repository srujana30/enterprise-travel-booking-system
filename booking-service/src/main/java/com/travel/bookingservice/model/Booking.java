package com.travel.bookingservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    private String bookingId;
    @Column(name = "passenger_name")
    private String passengerName;
    @Column(name = "flight_name")
    private String flightNumber;
    @Column(name = "hotel_name")
    private String hotelName;
    @Column(name = "source")
    private String source;
    @Column(name = "destination")
    private String destination;
    @Column(name = "travel_date")
    private LocalDate travelDate;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "booking_status")
    private String bookingStatus;
    @Column(name = "booking_time")
    private LocalDateTime bookingTime;
}
