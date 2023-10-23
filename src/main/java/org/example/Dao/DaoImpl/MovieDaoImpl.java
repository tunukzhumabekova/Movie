package org.example.Dao.DaoImpl;


import org.example.Config.JdbcConfig;
import org.example.Dao.MovieDao;
import org.example.Models.Movie;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoImpl implements MovieDao {
    private final Connection connection = JdbcConfig.getConnection();
    @Override
    public void createTable(String table, List<String> columns) {
        StringBuilder stringBuilder =
                new StringBuilder(String.format("create table %s (",table));
        try {
            Statement statement = connection.createStatement();

        for (int i = 0; i < columns.size(); i++) {
            stringBuilder.append(columns.get(i));
            if (i<columns.size()-1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(")");
        statement.executeUpdate(stringBuilder.toString());
        statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveMovies(Movie movie) {
        try (
            PreparedStatement preparedStatement = connection.prepareStatement("""
              insert into movies(title,genre,duration)values (?,?,?)
              """)){
            preparedStatement.setString(1,movie.getTitle());
            preparedStatement.setString(2,movie.getGenre());
            preparedStatement.setInt(3,movie.getDuration());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie findById(long id) {
        Movie movie = new Movie();
      PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    """
            select * from movies where id = ?
    """);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                throw new RuntimeException("not found");
            }else {
                movie.setId(resultSet.getLong("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setDuration(resultSet.getInt("duration"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movie;
    }

    @Override
    public List<Movie> searchByName(String title) {
        List<Movie> movies = new ArrayList<>();
        String sql = "select * from movies where title = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                movies.add(new Movie(resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getInt("duration")));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return movies;
    }

    @Override
    public List<Movie> sortByDuration(String ascOrDesc) {
       List<Movie>movies = new ArrayList<>();
       String sql = "select * from movies order by duration"+(ascOrDesc.equalsIgnoreCase("asc") ? "ASC" : "DESC");
       try(PreparedStatement preparedStatement = connection.prepareStatement(sql);
           ResultSet resultSet = preparedStatement.executeQuery()){
           movies.add(new Movie(resultSet.getString("title"),
                   resultSet.getString("genre"),
                   resultSet.getInt("duration")));
       }catch (SQLException e){
           throw new RuntimeException(e);
       }
        return movies;
    }

    @Override
    public List<Movie> getMoviesByTheaterIdAndStartTime(Long theaterId, LocalDateTime startTime) {
        List<Movie>movies = new ArrayList<>();
        String sql = "select * from movies where theatre_id = ? and start_time = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet =preparedStatement.executeQuery()){
            movies.add(new Movie(resultSet.getString("title"),
                    resultSet.getString("genre"),
                    resultSet.getInt("duration")));
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return movies;
    }
}
