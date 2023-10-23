package org.example.Dao;


import org.example.Models.Movie;
import org.example.Models.ShowTime;
import org.example.Models.Theatre;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public interface ShowTimeDao {
    String saveShow(ShowTime showTime);
    ShowTime find(Long id);
    void assign(ShowTime showTime);
    List<ShowTime> getAll();
    String deleteShowTimeByStartAndEndTime(Time startTime, Time endTime);
    List<Map<Theatre, List<Movie>>> getMoviesGroupByTheater();
}
