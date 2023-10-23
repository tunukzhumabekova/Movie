package org.example.Service;

import org.example.Models.Movie;
import org.example.Models.Theatre;

import java.util.List;
import java.util.Map;

public interface TheatreService {
    List<Map<Movie, List<Theatre>>> getAllMoviesByTime(int hour);
}
