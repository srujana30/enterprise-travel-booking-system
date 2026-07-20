package com.travel.bookingservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(info = @Info (title = "Enterprise Travel Booking System API",
                                 version = "v1",
                                 description = "REST APIs for searching flights and hotels, creating bookings, retrieving booking details, and cancelling bookings. Built using Spring Boot Microservices, Spring Data JPA, MySQL, and RESTful APIs",
                                 contact = @Contact(name = "SG",
                                                    email = "sg.r@gmail.com")))
public class OpenApiConfig {
}
