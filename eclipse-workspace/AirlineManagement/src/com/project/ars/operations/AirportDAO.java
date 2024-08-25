package com.project.ars.operations;

import com.project.ars.bean.Airport;
import com.project.ars.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportDAO {
    private Connection connection;

    public AirportDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Ensure connection is open before performing operations
    private void checkConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseConnection.getConnection();
        }
    }

    public void addAirport(Airport airport) {
        String sql = "INSERT INTO airports (name, city, country) VALUES (?, ?, ?)";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, airport.getName());
                statement.setString(2, airport.getCity());
                statement.setString(3, airport.getCountry());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Airport getAirport(int id) {
        Airport airport = null;
        String sql = "SELECT * FROM airports WHERE id = ?";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        airport = new Airport();
                        airport.setId(rs.getInt("id"));
                        airport.setName(rs.getString("name"));
                        airport.setCity(rs.getString("city"));
                        airport.setCountry(rs.getString("country"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airport;
    }

    public void updateAirport(Airport airport) {
        String sql = "UPDATE airports SET name = ?, city = ?, country = ? WHERE id = ?";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, airport.getName());
                statement.setString(2, airport.getCity());
                statement.setString(3, airport.getCountry());
                statement.setInt(4, airport.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAirport(int id) {
        String sql = "DELETE FROM airports WHERE id = ?";
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

    public List<Airport> getAllAirports() {
        List<Airport> airports = new ArrayList<>();
        String sql = "SELECT * FROM airports";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Airport airport = new Airport();
                    airport.setId(rs.getInt("id"));
                    airport.setName(rs.getString("name"));
                    airport.setCity(rs.getString("city"));
                    airport.setCountry(rs.getString("country"));
                    airports.add(airport);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airports;
    }
}
