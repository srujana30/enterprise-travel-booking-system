package com.travel.bookingservice.service;

import com.travel.bookingservice.exception.BookingNotFoundException;
import com.travel.bookingservice.repository.BookingRepository;
import com.travel.bookingservice.dto.BookingRequest;
import com.travel.bookingservice.dto.BookingResponse;
import com.travel.bookingservice.dto.CancelBookingResponse;
import com.travel.bookingservice.model.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {

    private static final Logger logger =
              LoggerFactory.getLogger(BookingService.class);


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
        logger.info("Booking created successfully - "+booking.getBookingId());
        return response;
    }

    public Booking getBooking(String bookingId) {
        Optional<Booking> bookingResponse = bookingRepository.findById(bookingId);
        if(bookingResponse.isEmpty()){
            logger.warn("BookingId - " + bookingId +" not found");
            throw new BookingNotFoundException("BookingId " + bookingId +" not found");
        }
        return  bookingResponse.get();

    }

    public CancelBookingResponse cancelBooking(String bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isEmpty()) {
            logger.warn("BookingId - " + bookingId +" not found");
            throw new BookingNotFoundException("BookingId " + bookingId +" not found");
        }

        if ("CANCELLED".equals(booking.get().getBookingStatus())) {
            logger.info("Booking-"+bookingId+" is already CANCELLED");
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
        logger.info("Updating Booking-"+bookingId+" status as CANCELLED");

        booking.get().setBookingStatus("CANCELLED");


        // Save updated booking
        bookingRepository.save(booking.get());


        CancelBookingResponse response =
                new CancelBookingResponse();

        response.setBookingId(booking.get().getBookingId());
        response.setBookingStatus(
                booking.get().getBookingStatus()
        );
        response.setMessage(
                "Booking cancelled successfully"
        );


        return response;
    }
}

