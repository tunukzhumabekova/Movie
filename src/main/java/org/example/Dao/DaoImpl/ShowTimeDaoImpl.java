package org.example.Dao.DaoImpl;

import org.example.Config.JdbcConfig;
import org.example.Dao.ShowTimeDao;
import org.example.Models.Movie;
import org.example.Models.ShowTime;
import org.example.Models.Theatre;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowTimeDaoImpl implements ShowTimeDao {
    private org.example.Config.JdbcConfig JdbcConfig;
    private final Connection connection = JdbcConfig.getConnection();
    @Override
    public String saveShow(ShowTime showTime) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
        insert into showtime(movie_id,theatre_id,start_time,end_time)
        values (?,?,?,?)
    """);
            preparedStatement.setLong(1, showTime.getMovie_id());
            preparedStatement.setLong(2, showTime.getTheatre_id());
            preparedStatement.setTime(3, showTime.getStart_time());
            preparedStatement.setTime(4, showTime.getEnd_time());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "show saved";
    }



    @Override
    public ShowTime find(Long id) {
        ShowTime showTime = new ShowTime();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
    select * from show_time where  id = ?
    """);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                throw  new RuntimeException("not found");
            }else {
                showTime.setId(resultSet.getLong("id"));
                showTime.setMovie_id(resultSet.getLong("movie_id"));
                showTime.setTheatre_id(resultSet.getLong("theatre_id"));
                showTime.setStart_time(resultSet.getTime("start_time"));
                showTime.setEnd_time(resultSet.getTime("end_time"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return showTime;
    }
    @Override
    public void assign(ShowTime showTime) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("""
                update show_time set
                movie_id = ?,
                theatre_id = ?
                where id = ?
                       """);
                preparedStatement.setLong(1,showTime.getMovie_id());
                preparedStatement.setLong(2,showTime.getTheatre_id());
                preparedStatement.setLong(3,showTime.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

        }

    @Override
    public List<ShowTime> getAll() {
        List<ShowTime> showTimeList = new ArrayList<>();
        String sql = "select * from showtime";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                showTimeList.add(new ShowTime(resultSet.getLong("movie_id"),
                        resultSet.getLong("theatre_id"),
                        resultSet.getTime("start_time"),
                        resultSet.getTime("end_time")));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return showTimeList;
    }

    @Override
    public String deleteShowTimeByStartAndEndTime(Time startTime, Time endTime) {
        String sql = "delete from showtime where start_time = ? and end_time = ?";
                try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                    preparedStatement.setTime(1,startTime);
                    preparedStatement.setTime(2,endTime);
                    preparedStatement.executeUpdate();
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
                return "removed successfully";
    }

    @Override
    public List<Map<Theatre, List<Movie>>> getMoviesGroupByTheater() {
        List<Map<Theatre, List<Movie>>> result = new ArrayList<>();
        String sql = "SELECT t.id , t.name ,t.location, m.id , m.title,m.genre, m.duration " +
                "FROM theatre t " +
                " JOIN showtime st ON t.id = st.theatre_id " +
                " JOIN movies m ON st.movie_id = m.id";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()){
            Map<Theatre, List<Movie>> theaterMovieMap = new HashMap<>();

            while (resultSet.next()) {
                Theatre theatre = new Theatre(resultSet.getString("name"), resultSet.getString("location"));
                Movie movie = new Movie(resultSet.getString("title"), resultSet.getString("genre"), resultSet.getInt("duration"));

                theaterMovieMap.putIfAbsent(theatre, new ArrayList<>());
                theaterMovieMap.get(theatre).add(movie);
            }

            for (Map.Entry<Theatre, List<Movie>> entry : theaterMovieMap.entrySet()) {
                Map<Theatre, List<Movie>> resultMap = new HashMap<>();
                resultMap.put(entry.getKey(), entry.getValue());
                result.add(resultMap);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}

