package com.project.ars.operations;

import com.project.ars.bean.Airline;
import com.project.ars.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirlineDAO {
    private Connection connection;

    public AirlineDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Ensure connection is open before performing operations
    private void checkConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseConnection.getConnection();
        }
    }

    public void addAirline(Airline airline) {
        String sql = "INSERT INTO airlines (name, country) VALUES (?, ?)";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, airline.getName());
                statement.setString(2, airline.getCountry());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Airline getAirline(int id) {
        Airline airline = null;
        String sql = "SELECT * FROM airlines WHERE id = ?";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        airline = new Airline();
                        airline.setId(rs.getInt("id"));
                        airline.setName(rs.getString("name"));
                        airline.setCountry(rs.getString("country"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airline;
    }

    public void updateAirline(Airline airline) {
        String sql = "UPDATE airlines SET name = ?, country = ? WHERE id = ?";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, airline.getName());
                statement.setString(2, airline.getCountry());
                statement.setInt(3, airline.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAirline(int id) {
        String sql = "DELETE FROM airlines WHERE id = ?";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Airline> getAllAirlines() {
        List<Airline> airlines = new ArrayList<>();
        String sql = "SELECT * FROM airlines";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Airline airline = new Airline();
                    airline.setId(rs.getInt("id"));
                    airline.setName(rs.getString("name"));
                    airline.setCountry(rs.getString("country"));
                    airlines.add(airline);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlines;
    }
}
