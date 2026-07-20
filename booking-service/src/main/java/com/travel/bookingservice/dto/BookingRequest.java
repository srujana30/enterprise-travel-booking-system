package com.travel.bookingservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;


@Data
public class BookingRequest {

    @NotBlank(message = "Passenger name is required")
    @Schema(
            description = "Passenger full name",
            example = "John Doe",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String passengerName;

    @NotBlank(message = "Flight Number name is required")
    @Schema(
            description = "Passenger full name",
            example = "John Doe",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String flightNumber;

    @NotBlank(message = "Hotel Name name is required")
    @Schema(
            description = "Passenger full name",
            example = "John Doe",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String hotelName;
    private String source;
    private String destination;
    private LocalDate travelDate;
}
