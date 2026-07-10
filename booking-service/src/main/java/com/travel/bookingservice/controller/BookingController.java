package com.travel.bookingservice.controller;

import com.travel.bookingservice.dto.BookingRequest;
import com.travel.bookingservice.dto.BookingResponse;
import com.travel.bookingservice.dto.CancelBookingResponse;
import com.travel.bookingservice.model.Booking;
import com.travel.bookingservice.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public String health() {
        return "Booking Service Running";
    }

    @PostMapping("/createBooking")
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request) {
       BookingResponse response = service.createBooking(request);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getBooking/{bookingId}")
    public ResponseEntity<Booking> getBooking(@PathVariable String bookingId) {
        Booking response = service.getBooking(bookingId);
        if(response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{bookingId}/cancel")
    public ResponseEntity<CancelBookingResponse> cancelBooking(@PathVariable String bookingId) {
        CancelBookingResponse response = service.cancelBooking(bookingId);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

}
