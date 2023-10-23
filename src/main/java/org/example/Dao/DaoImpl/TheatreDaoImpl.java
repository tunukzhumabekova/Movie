package org.example.Dao.DaoImpl;

import org.example.Config.JdbcConfig;
import org.example.Dao.TheatreDao;
import org.example.Models.Movie;
import org.example.Models.Theatre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreDaoImpl implements TheatreDao {
    private final Connection connection = JdbcConfig.getConnection();
    @Override
    public Theatre findById(Long theatreId) {
        Theatre theatre = new Theatre();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("""
    select * from theatre where id =?
    """);
            preparedStatement.setLong(1,theatreId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                throw new RuntimeException("not fpund");
            }
            else{
                theatre.setId(resultSet.getLong("id"));
                theatre.setName(resultSet.getString("name"));
                theatre.setLocation(resultSet.getString("location"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return theatre;
    }

    @Override
    public List<Map<Movie, List<Theatre>>> getAllMoviesByTime(int hour) {
        List<Map<Movie, List<Theatre>>> result = new ArrayList<>();

        String sql = "SELECT m.*, t.* FROM movies m JOIN theaters t ON m.theater_id = t.id WHERE m.start_time = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, hour);
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<Movie, List<Theatre>> movieTheatreMap = new HashMap<>();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getLong("m.id"));
                movie.setTitle(resultSet.getString("m.title"));
                movie.setGenre(resultSet.getString("m.genre"));
                movie.setDuration(resultSet.getInt("duration"));

                Theatre theatre = new Theatre();
                theatre.setId(resultSet.getLong("t.id"));
                theatre.setName(resultSet.getString("t.name"));
                theatre.setLocation(resultSet.getString("t.location"));

                movieTheatreMap.put(movie, movieTheatreMap.getOrDefault(movie, new ArrayList<>()));
                movieTheatreMap.get(movie).add(theatre);
            }

            for (Map.Entry<Movie, List<Theatre>> entry : movieTheatreMap.entrySet()) {
                Map<Movie, List<Theatre>> resultMap = new HashMap<>();
                resultMap.put(entry.getKey(), entry.getValue());
                result.add(resultMap);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
