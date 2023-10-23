package org.example;

import org.example.Models.Booking;
import org.example.Models.ShowTime;
import org.example.Service.*;
import org.example.Service.ServiceImpl.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner number = new Scanner(System.in);
        MoviesService moviesService = new MovieServiceImpl();
        ShowTimeService showTimeService = new ShowTimeServiceimpl();
        TheatreService theatreService = new TheatreServImpl();
        UserService userService = new UserServiceImpl();
        BookingService bookingService = new BookingServImpl();
        while (true) {
            switch (new Scanner(System.in).nextLine()) {
                case "1", "search by Movie by name" -> {
                    System.out.println("Enter title");
                    String title = scanner.next();
                    System.out.println(moviesService.searchByName(title));
                }
                case "2","sort by duration" -> {
                    String ascordesc = scanner.nextLine();
                    System.out.println(moviesService.sortByDuration(ascordesc));
                }
                case "3","get movies by theatre id and start time"-> {
                    System.out.println("Enter theatre id");
                    long theatre_id = scanner.nextLong();
                    System.out.println("Enter start_time");
                    String start_time = scanner.nextLine();
                    System.out.println(moviesService.getMoviesByTheaterIdAndStartTime(theatre_id, LocalDateTime.parse(start_time)));
                }
                case "4","get all movies by time"-> {
                    System.out.println("Enter the hour");
                    int hour = scanner.nextInt();
                    System.out.println(theatreService.getAllMoviesByTime(hour));
                }
                case "5","save" -> {
                    System.out.println(showTimeService.saveShow(new ShowTime(1L, 2L, Time.valueOf("20:00:00"), Time.valueOf("22:00:00"))));
                }
                case "6","assign" -> {
                    System.out.println(showTimeService.assign(4L, 1L, 1L));
                }
                case "7","get all shows" -> {
                    System.out.println(showTimeService.getAll());
                }
                case "8","find by id show" -> {
                    System.out.println("Enter the id");
                    long showid= scanner.nextLong();
                    System.out.println(showTimeService.findById(showid));
                }
                case "9","delete showtime by start and end time" -> {
                    Time startDate = Time.valueOf("20:00:00");
                    Time endDate = Time.valueOf("21:00:00");
                    System.out.println(showTimeService.deleteShowTimeByStartAndEndTime(startDate, endDate));
                }
                case "10","get movies group by theatre" -> {
                    System.out.println(showTimeService.getMoviesGroupByTheater());
                }
                case "11","existByEmail" -> {
                    System.out.println("Enter the email");
                    String email = scanner.next();
                    System.out.println(userService.existByEmail(email));
                }
                case "12","saveBooking" -> {
                    Time bookingTime = Time.valueOf("21:00:00");
                    System.out.println(bookingService.save(new Booking(3L,2,15,bookingTime)));
                }
                case "13","find by id" -> {
                    System.out.println(bookingService.findById(2));
                }
                case "14","delete" -> {
                    System.out.println(bookingService.delete(1));
                }
                case "15","get all bookings" -> {
                    bookingService.getAllBookings();
                }
                case "16","get booking by user id" -> {
                    System.out.println(bookingService.getBookingByUserId(1));
                }
            }
        }
    }
}
