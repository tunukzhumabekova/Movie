package org.example.Service;

import Peaksoft.Models.Booking;
import org.example.Models.Booking;

import java.util.List;

public interface BookingService {
    String save(Booking booking);

    String save(Booking booking);

    Booking findById(int id);
    String delete(int id);
    void getAllBookings();
    List<Booking> getBookingByUserId(int userId);
}
