package org.example.Models;

import java.sql.Time;

public class Booking {
    private int id;
    private Long showtime_id;
    private int users_id;
    private int number_of_tickets;
    private Time booking_time;

    public Booking() {
    }

    public Booking(Long showtime_id, int users_id, int number_of_tickets, Time booking_time) {
        this.showtime_id = showtime_id;
        this.users_id = users_id;
        this.number_of_tickets = number_of_tickets;
        this.booking_time = booking_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getShowtime_id() {
        return showtime_id;
    }

    public void setShowtime_id(Long showtime_id) {
        this.showtime_id = showtime_id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public int getNumber_of_tickets() {
        return number_of_tickets;
    }

    public void setNumber_of_tickets(int number_of_tickets) {
        this.number_of_tickets = number_of_tickets;
    }

    public Time getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(Time booking_time) {
        this.booking_time = booking_time;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", showtime_id=" + showtime_id +
                ", users_id=" + users_id +
                ", number_of_tickets=" + number_of_tickets +
                ", booking_time=" + booking_time +
                '}';
    }
}
