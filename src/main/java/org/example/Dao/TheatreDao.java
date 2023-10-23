package org.example.Dao;


import org.example.Models.Movie;
import org.example.Models.Theatre;

import java.util.List;
import java.util.Map;

public interface TheatreDao {
    Theatre findById(Long theatreId);
    List<Map<Movie, List<Theatre>>> getAllMoviesByTime(int hour);
}
