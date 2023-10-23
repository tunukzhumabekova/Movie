package org.example.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfig {
    public static Connection getConnection(){
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/database2", "postgres", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
