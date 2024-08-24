package com.project.ars.operations;


import com.project.ars.bean.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AirportDAO {
    private Connection connection;

    // Constructor to initialize database connection
    public AirportDAO() {
        try {
            // Load JDBC driver (optional if using newer JDBC versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new airport
    public void addAirport(Airport airport) {
        String sql = "INSERT INTO airports (name, city, country) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, airport.getName());
            statement.setString(2, airport.getCity());
            statement.setString(3, airport.getCountry());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get an airport by its ID
    public Airport getAirport(int id) {
        Airport airport = null;
        String sql = "SELECT * FROM airports WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                airport = new Airport();
                airport.setId(rs.getInt("id"));
                airport.setName(rs.getString("name"));
                airport.setCity(rs.getString("city"));
                airport.setCountry(rs.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airport;
    }

    // Method to update an airport
    public void updateAirport(Airport airport) {
        String sql = "UPDATE airports SET name = ?, city = ?, country = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, airport.getName());
            statement.setString(2, airport.getCity());
            statement.setString(3, airport.getCountry());
            statement.setInt(4, airport.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete an airport by its ID
    public void deleteAirport(int id) {
        String sql = "DELETE FROM airports WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all airports
    public List<Airport> getAllAirports() {
        List<Airport> airports = new ArrayList<>();
        String sql = "SELECT * FROM airports";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Airport airport = new Airport();
                airport.setId(rs.getInt("id"));
                airport.setName(rs.getString("name"));
                airport.setCity(rs.getString("city"));
                airport.setCountry(rs.getString("country"));
                airports.add(airport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airports;
    }
}
