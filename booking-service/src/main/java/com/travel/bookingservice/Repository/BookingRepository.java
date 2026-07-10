package com.travel.bookingservice.Repository;

import com.travel.bookingservice.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BookingRepository  {

    final Map<String, Booking> bookingStore = new HashMap<String, Booking>();

    public void save(Booking booking) {
        bookingStore.put(booking.getBookingId(), booking);
    }

    public Booking findById(String bookingId) {
        return bookingStore.get(bookingId);
    }

}


