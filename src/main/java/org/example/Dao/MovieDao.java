package org.example.Dao;


import org.example.Models.Movie;

import java.time.LocalDateTime;
import java.util.List;
public interface MovieDao {
    void createTable(String table, List<String> columns);

    void saveMovies(Movie movie);

    Movie findById(long id);
    List<Movie> searchByName(String title);
    List<Movie> sortByDuration(String ascOrDesc);
    List<Movie> getMoviesByTheaterIdAndStartTime(Long theaterId, LocalDateTime startTime);
}
