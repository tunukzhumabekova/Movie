package org.example.Dao.DaoImpl;


import org.example.Config.JdbcConfig;
import org.example.Dao.BookingDao;
import org.example.Models.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements BookingDao {
    private final Connection connection = JdbcConfig.getConnection();
    @Override
    public String save(Booking booking) {
        String sql = "insert into booking(showtime_id, user_id, number_of_tickets, booking_time)values (?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,booking.getShowtime_id());
            preparedStatement.setInt(2,booking.getUsers_id());
            preparedStatement.setInt(3,booking.getNumber_of_tickets());
            preparedStatement.setTime(4,booking.getBooking_time());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return "saved!";
    }



    @Override
    public Booking findById(int id) {
        Booking booking = new Booking();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(
                    """
            select * from booking where id = ?
    """);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                throw new RuntimeException("not found");
            }else {
                booking.setId(resultSet.getInt("id"));
                booking.setShowtime_id(resultSet.getLong("showtime_id"));
                booking.setUsers_id(resultSet.getInt("user_id"));
                booking.setNumber_of_tickets(resultSet.getInt("number_of_tickets"));
                booking.setBooking_time(resultSet.getTime("booking_time"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return booking;
    }

    @Override
    public String delete(int id) {
        String sql = "delete from booking where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return "removed successfully";
    }

    @Override
    public void getAllBookings() {
     List <Booking> bookings = new ArrayList<>();
     String sql = "select * from booking";
     try(Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql)){
         while (resultSet.next()){
             bookings.add(new Booking(resultSet.getLong("showtime_id"),
                     resultSet.getInt("user_id"),
                     resultSet.getInt("number_of_tickets"),
                     resultSet.getTime("booking_time")));
         }
     }catch (SQLException e){
         throw new RuntimeException(e);
     }
    }

    @Override
    public List<Booking> getBookingByUserId(int userId) {
        List<Booking> bookingList = new ArrayList<>();

        String sql = "SELECT * FROM booking WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    bookingList.add(new Booking(
                            resultSet.getLong("showtime_id"),
                            resultSet.getInt("user_id"),
                            resultSet.getInt("number_of_tickets"),
                            resultSet.getTime("booking_time")));
                }
            }
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
        return bookingList;
    }
}
