package org.example.Service.ServiceImpl;



import org.example.Dao.DaoImpl.TheatreDaoImpl;
import org.example.Dao.TheatreDao;
import org.example.Models.Movie;
import org.example.Models.Theatre;
import org.example.Service.TheatreService;

import java.util.List;
import java.util.Map;

public class TheatreServImpl implements TheatreService {
    TheatreDao theatreDao = new TheatreDaoImpl();

    @Override
    public List<Map<Movie, List<Theatre>>> getAllMoviesByTime(int hour) {
        return theatreDao.getAllMoviesByTime(hour);
    }
}
