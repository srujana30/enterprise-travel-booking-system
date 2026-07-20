package com.travel.bookingservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.travel.bookingservice.dto.BookingRequest;
import com.travel.bookingservice.dto.BookingResponse;
import com.travel.bookingservice.dto.CancelBookingResponse;
import com.travel.bookingservice.exception.BookingNotFoundException;
import com.travel.bookingservice.exception.GlobalExceptionHandler;
import com.travel.bookingservice.model.Booking;
import com.travel.bookingservice.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BookingController.class)
public class TestBookingController {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    BookingService bookingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddBooking200() throws Exception {
        BookingRequest request = new BookingRequest();
        request.setPassengerName("John Doe");
        request.setFlightNumber("IND-101");
        request.setHotelName("Marriott");

        BookingResponse response = new BookingResponse();
        response.setBookingStatus("CONFIRMED");
        response.setBookingId("BK-0000000");
        response.setPassengerName("John Doe");
        response.setMessage("Booking created successfully.");

        when(bookingService.createBooking(request)).thenReturn(response);

        mockMvc.perform(post("/api/booking/addBooking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookingId").value("BK-0000000"))
                .andExpect(jsonPath("$.passengerName").value("John Doe"))
                .andExpect(jsonPath("$.bookingStatus").value("CONFIRMED"))
                .andExpect(jsonPath("$.message").value("Booking created successfully."));
    }

    @Test
    public void testAddBooking400() throws Exception {
        BookingRequest request = new BookingRequest();
        mockMvc.perform(post("/api/booking/addBooking").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).andExpect(status().isBadRequest());
        verify(bookingService, never()).createBooking(any());
    }
    @Test
    public void testAddBooking500() throws Exception {
        BookingRequest request = new BookingRequest();
        request.setPassengerName("John Doe");
        request.setFlightNumber("AI101");
        request.setHotelName("Marriott");
        request.setSource("Dallas");
        request.setDestination("New York");

        when(bookingService.createBooking(request))
                .thenThrow(new RuntimeException("Something went wrong"));

        mockMvc.perform(post("/api/booking/addBooking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                        .andExpect(status().isInternalServerError());

        // Verify service called once
        verify(bookingService, times(1))
                .createBooking(any(BookingRequest.class));
    }

    @Test
    public void testGetBooking200() throws Exception {
        Booking response = new Booking();
        response.setBookingId("BK-0000000");
        response.setBookingStatus("CONFIRMED");
        response.setSource("DFW");
        response.setDestination("LAS");
        when(bookingService.getBooking("BK-0000000")).thenReturn(response);

        mockMvc.perform(get("/api/booking/BK-0000000")).andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value("BK-0000000"))
                .andExpect(jsonPath("$.bookingStatus").value("CONFIRMED"));
    }

    @Test
    public void testGetBooking404() throws Exception {
        when(bookingService.getBooking("BK-0000000")).thenThrow(new BookingNotFoundException("Booking not found"));
        mockMvc.perform(get("/api/booking/BK-0000000")).andExpect(status().isNotFound());
    }

    @Test
    public void testCancelBooking200() throws Exception {
        CancelBookingResponse response = new CancelBookingResponse();
        response.setBookingId("BK-0000000");
        response.setBookingStatus("CANCELLED");
        when(bookingService.cancelBooking("BK-0000000")).thenReturn(response);
        mockMvc.perform(patch("/api/booking/BK-0000000/cancel")).andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value("BK-0000000"))
                .andExpect(jsonPath("$.bookingStatus").value("CANCELLED"));
    }

    @Test
    public void testAlreadyCancelBooking200() throws Exception {
        CancelBookingResponse response = new CancelBookingResponse();
        response.setBookingId("BK-0000000");
        response.setBookingStatus("CANCELLED");
        response.setMessage("Booking is already cancelled");
        when(bookingService.cancelBooking("BK-0000000")).thenReturn(response);
        mockMvc.perform(patch("/api/booking/BK-0000000/cancel")).andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value("BK-0000000"))
                .andExpect(jsonPath("$.bookingStatus").value("CANCELLED"))
                .andExpect(jsonPath("$.message").value("Booking is already cancelled"));


    }


    @Test
    public void testCancelBooking404() throws Exception {
        when(bookingService.cancelBooking("BK-0000000")).thenThrow(new BookingNotFoundException("Booking not found"));
        mockMvc.perform(patch("/api/booking/BK-0000000/cancel")).andExpect(status().isNotFound());
    }


}
