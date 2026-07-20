package com.travel.bookingservice.controller;

import com.travel.bookingservice.dto.BookingRequest;
import com.travel.bookingservice.dto.BookingResponse;
import com.travel.bookingservice.dto.CancelBookingResponse;
import com.travel.bookingservice.exception.BookingNotFoundException;
import com.travel.bookingservice.model.Booking;
import com.travel.bookingservice.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
@Tag(name = "Booking API", description = "APIs for creating, retrieving and cancelling travel bookings")
public class BookingController {
    private static final Logger logger =
            LoggerFactory.getLogger(BookingService.class);
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @Operation
    @GetMapping("/health")
    public String health() {
        return "Booking Service Running";
    }

    @PostMapping("/addBooking")
    @Operation(
            summary = "Create Booking",
            description = "Creates a new travel booking for a passenger."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Booking created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid booking request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<BookingResponse> createBooking(@Valid  @RequestBody BookingRequest request) {
        logger.info("Creating booking request received");
        BookingResponse response = service.createBooking(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{bookingId}")
    @Operation(
            summary = "Get Booking",
            description = "Retrieves a travel booking for a passenger."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Booking retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Invalid booking id"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Booking> getBooking(@Parameter(
            description = "Unique booking ID",
            example = "BK-10013456"
    ) @PathVariable String bookingId) {
        Booking response = service.getBooking(bookingId);
//        if(response == null){
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{bookingId}/cancel")
    @Operation(
            summary = "Cancel Booking",
            description = "Cancels a travel booking for a passenger."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Booking cancelled successfully"),
            @ApiResponse(responseCode = "404", description = "Invalid booking id"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CancelBookingResponse> cancelBooking( @Parameter(
            description = "Unique booking ID",
            example = "BK-1000001"
    )@PathVariable String bookingId) {
        CancelBookingResponse response = service.cancelBooking(bookingId);
        if (response == null) {
            logger.warn("BookingId - " + bookingId + " not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

}
