package com.project.ars.operations;



import java.sql.*;
import com.project.ars.bean.User;
import com.project.ars.db.DatabaseConnection;

public class UserDAO {

    public boolean registerUser(User user) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());  // You should hash the password in production
            pstmt.setString(3, user.getRole());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User loginUser(String username, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

public User validateLogin(String username, String password) {
    String query = "SELECT * FROM users WHERE username = ? AND password = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, username);
        statement.setString(2, password); // Compare hashed password
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
            return user;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
}