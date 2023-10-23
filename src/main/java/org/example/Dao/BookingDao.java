package org.example.Dao;


import org.example.Models.Booking;

import java.util.List;

public interface BookingDao {
    String save(Booking booking);
    Booking findById(int id);
    String delete(int id);
    void getAllBookings();
    List<Booking> getBookingByUserId(int userId);
}
