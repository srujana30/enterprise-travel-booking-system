package com.travel.bookingservice.repository;

import com.travel.bookingservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public interface BookingRepository extends JpaRepository<Booking,String> {



}


