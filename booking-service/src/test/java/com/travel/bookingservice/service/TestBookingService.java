package com.travel.bookingservice.service;

import com.travel.bookingservice.dto.BookingRequest;
import com.travel.bookingservice.dto.BookingResponse;
import com.travel.bookingservice.dto.CancelBookingResponse;
import com.travel.bookingservice.exception.BookingNotFoundException;
import com.travel.bookingservice.model.Booking;
import com.travel.bookingservice.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestBookingService {
    @InjectMocks
    BookingService bookingService;

    @Mock
    BookingRepository bookingRepository;

    @Test
    public void testCreateBookingService() {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setPassengerName("Acer");
        bookingRequest.setFlightNumber("IND-YUI");
        bookingRequest.setHotelName("Marriott");
        bookingRequest.setSource("DFW");
        bookingRequest.setDestination("AUS");
        bookingRequest.setTravelDate(LocalDate.now());

        when(bookingRepository.save(any(Booking.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        BookingResponse bookingResponse = bookingService.createBooking(bookingRequest);

        assertNotNull(bookingResponse);
        assertNotNull(bookingResponse.getBookingId());
        assertEquals("Acer", bookingResponse.getPassengerName());
        assertEquals(450.00, bookingResponse.getTotalPrice());
        assertEquals("Booking created successfully.", bookingResponse.getMessage());
        assertEquals("CONFIRMED", bookingResponse.getBookingStatus());
        // Verify save() called exactly once
        verify(bookingRepository, times(1)).save(any(Booking.class));
        // Capture the Booking object passed to repository
        ArgumentCaptor<Booking> bookingCaptor = ArgumentCaptor.forClass(Booking.class);
        verify(bookingRepository).save(bookingCaptor.capture());
        Booking savedBooking = bookingCaptor.getValue();

        assertEquals("Acer", savedBooking.getPassengerName());
        assertEquals("IND-YUI", savedBooking.getFlightNumber());
        assertEquals("Marriott", savedBooking.getHotelName());
        assertEquals("DFW", savedBooking.getSource());
        assertEquals("AUS", savedBooking.getDestination());
        assertEquals("CONFIRMED", savedBooking.getBookingStatus());
        assertEquals(450.00, savedBooking.getTotalPrice());


    }

    @Test
    public void testGetBooking() {

        String bookingId = "BK-11100000";

        Booking booking = new Booking();
        booking.setBookingId(bookingId);
        booking.setPassengerName("Riya");
        booking.setFlightNumber("FON-000");
        booking.setSource("DAL");
        booking.setDestination("LAS");
        when(bookingRepository.findById("BK-11100000")).thenReturn(Optional.of(booking));

        Booking bookingResponse = bookingService.getBooking(bookingId);
        assertNotNull(bookingResponse);
        assertEquals("BK-11100000", bookingResponse.getBookingId());
        assertEquals("DAL", bookingResponse.getSource());

    }

    @Test
    public void testBookingNotFound() {
        when(bookingRepository.findById(anyString())).thenReturn(Optional.empty());
        BookingNotFoundException exception = assertThrows(BookingNotFoundException.class, () -> bookingService.getBooking(""));
        assertEquals("BookingId  not found", exception.getMessage());
    }

    @Test
    public void testBookingNotFoundToCancel() {
        when(bookingRepository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(BookingNotFoundException.class, () -> bookingService.cancelBooking(""));
    }

    @Test
    public void testAlreadyCancelledBooking() {
        String bookingId = "BK-11100000";

        Booking booking = new Booking();
        booking.setBookingId(bookingId);
        booking.setPassengerName("Riya");
        booking.setFlightNumber("FON-000");
        booking.setSource("DAL");
        booking.setDestination("LAS");
        booking.setBookingStatus("CANCELLED");
        when(bookingRepository.findById("BK-11100000")).thenReturn(Optional.of(booking));

        CancelBookingResponse response = bookingService.cancelBooking(bookingId);

        assertNotNull(response);
        assertEquals("CANCELLED", response.getBookingStatus());
        assertEquals("Booking is already cancelled", response.getMessage());


    }

    @Test
    public void testCancelBooking() {
        String bookingId = "BK-11100000";

        Booking booking = new Booking();
        booking.setBookingId(bookingId);
        booking.setPassengerName("Riya");
        booking.setFlightNumber("FON-000");
        booking.setSource("DAL");
        booking.setDestination("LAS");
        booking.setBookingStatus("CONFIRMED");
        when(bookingRepository.findById("BK-11100000")).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        CancelBookingResponse response = bookingService.cancelBooking(bookingId);

        assertNotNull(response);
        assertEquals("CANCELLED", response.getBookingStatus());
        assertEquals("Booking cancelled successfully", response.getMessage());

        verify(bookingRepository, times(1))
                .save(any(Booking.class));

        ArgumentCaptor<Booking> bookingCaptor =
                ArgumentCaptor.forClass(Booking.class);

        verify(bookingRepository).save(bookingCaptor.capture());

        Booking savedBooking = bookingCaptor.getValue();

        // Verify booking status updated
        assertEquals("CANCELLED", savedBooking.getBookingStatus());

        assertEquals("BK-11100000", savedBooking.getBookingId());
        assertEquals("Riya", savedBooking.getPassengerName());
        assertEquals("FON-000", savedBooking.getFlightNumber());
        assertNull(savedBooking.getHotelName());
    }


}
