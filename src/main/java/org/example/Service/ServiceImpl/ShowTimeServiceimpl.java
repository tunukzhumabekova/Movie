package org.example.Service.ServiceImpl;


import org.example.Dao.DaoImpl.MovieDaoImpl;
import org.example.Dao.DaoImpl.ShowTimeDaoImpl;
import org.example.Dao.DaoImpl.TheatreDaoImpl;
import org.example.Dao.MovieDao;
import org.example.Dao.ShowTimeDao;
import org.example.Dao.TheatreDao;
import org.example.Models.Movie;
import org.example.Models.ShowTime;
import org.example.Models.Theatre;
import org.example.Service.ShowTimeService;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class ShowTimeServiceimpl implements ShowTimeService {
    ShowTimeDao showTimeDao = new ShowTimeDaoImpl();
    MovieDao movieDao = new MovieDaoImpl();
    TheatreDao theatreDao = new TheatreDaoImpl();
    @Override
    public String saveShow(ShowTime showTime) {
        return showTimeDao.saveShow(showTime);

    }

    @Override
    public ShowTime findById(Long id) {
        return showTimeDao.find(id);
    }

    @Override
    public String assign(Long showTime_id, Long movie_id, Long theatre_id) {
        ShowTime showTime = findById(showTime_id);
        Movie movie = movieDao.findById(movie_id);
        Theatre theatre = theatreDao.findById(theatre_id);
        showTime.setMovie_id(movie.getId());
        showTime.setTheatre_id(theatre.getId());
        showTimeDao.assign(showTime);
        return "success";
    }

    @Override
    public List<ShowTime> getAll() {
        return showTimeDao.getAll();
    }

    @Override
    public String deleteShowTimeByStartAndEndTime(Time startTime, Time endTime) {
        return showTimeDao.deleteShowTimeByStartAndEndTime(startTime,endTime);
    }

    @Override
    public List<Map<Theatre, List<Movie>>> getMoviesGroupByTheater() {
        return showTimeDao.getMoviesGroupByTheater();
    }
}
