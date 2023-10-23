package org.example.Dao.DaoImpl;


import org.example.Config.JdbcConfig;
import org.example.Dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public Boolean existByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection connection = JdbcConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при проверке существования пользователя по email: " + e.getMessage());
        }

        return false;
    }
}
