package org.example.Service.ServiceImpl;



import org.example.Dao.BookingDao;
import org.example.Dao.DaoImpl.BookingDaoImpl;
import org.example.Models.Booking;
import org.example.Service.BookingService;

import java.util.List;

public class BookingServImpl implements BookingService {
    BookingDao bookingDao = new BookingDaoImpl();

    @Override
    public String save(Booking booking) {
        return null;
    }

    @Override
    public Booking findById(int id) {
        return bookingDao.findById(id);
    }

    @Override
    public String delete(int id) {
        return bookingDao.delete(id);
    }

    @Override
    public void getAllBookings() {
    bookingDao.getAllBookings();
    }

    @Override
    public List<Booking> getBookingByUserId(int userId) {
        return bookingDao.getBookingByUserId(userId);
    }
}
