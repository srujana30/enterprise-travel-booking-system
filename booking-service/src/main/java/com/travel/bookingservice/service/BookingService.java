package com.travel.bookingservice.service;

import com.travel.bookingservice.Repository.BookingRepository;
import com.travel.bookingservice.dto.BookingRequest;
import com.travel.bookingservice.dto.BookingResponse;
import com.travel.bookingservice.dto.CancelBookingResponse;
import com.travel.bookingservice.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public BookingResponse createBooking(BookingRequest request) {

        Booking booking = new Booking();
        booking.setBookingId("BK-" + UUID.randomUUID().toString().substring(0, 8));
        booking.setPassengerName(request.getPassengerName());
        booking.setFlightNumber(request.getFlightNumber());
        booking.setHotelName(request.getHotelName());
        booking.setSource(request.getSource());
        booking.setDestination(request.getDestination());
        booking.setTravelDate(request.getTravelDate());
        booking.setTotalPrice(450.00);
        booking.setBookingStatus("CONFIRMED");
        booking.setBookingTime(LocalDateTime.now());

        BookingResponse response = new BookingResponse();
        response.setPassengerName(booking.getPassengerName());
        response.setTotalPrice(booking.getTotalPrice());
        response.setBookingStatus(booking.getBookingStatus());
        response.setBookingId(booking.getBookingId());
        response.setMessage("Booking created successfully.");

        bookingRepository.save(booking);
        return response;
    }

    public Booking getBooking(String bookingId) {
        Booking boookingResponse = bookingRepository.findById(bookingId);
        return boookingResponse;
    }

    public CancelBookingResponse cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null) {
            return null;
        }

        if ("CANCELLED".equals(booking.getBookingStatus())) {

            CancelBookingResponse response =
                    new CancelBookingResponse();

            response.setBookingId(bookingId);
            response.setBookingStatus("CANCELLED");
            response.setMessage(
                    "Booking is already cancelled"
            );

            return response;
        }


        // Update status
        booking.setBookingStatus("CANCELLED");


        // Save updated booking
        bookingRepository.save(booking);


        CancelBookingResponse response =
                new CancelBookingResponse();

        response.setBookingId(booking.getBookingId());
        response.setBookingStatus(
                booking.getBookingStatus()
        );
        response.setMessage(
                "Booking cancelled successfully"
        );


        return response;
    }
}

