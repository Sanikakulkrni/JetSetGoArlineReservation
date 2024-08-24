package com.project.ars.operations;

import com.project.ars.db.*;

import com.project.ars.bean.Flight;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FlightDAO {
    private Connection connection;

    // Constructor to initialize database connection
    public FlightDAO() {
        try {
            // Load JDBC driver (optional if using newer JDBC versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new flight
    public void addFlight(Flight flight) {
        String sql = "INSERT INTO flights (flightCode, flightName, source, destination, capacity, classCode, className) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, flight.getFlightCode());
            statement.setString(2, flight.getFlightName());
            statement.setString(3, flight.getSource());
            statement.setString(4, flight.getDestination());
            statement.setInt(5, flight.getCapacity());
            statement.setString(6, flight.getClassCode());
            statement.setString(7, flight.getClassName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a flight by its code
    public Flight getFlight(String flightCode) {
        Flight flight = null;
        String sql = "SELECT * FROM flights WHERE flightCode = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, flightCode);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                flight = new Flight();
                flight.setId(rs.getInt("id"));
                flight.setFlightCode(rs.getString("flightCode"));
                flight.setFlightName(rs.getString("flightName"));
                flight.setSource(rs.getString("source"));
                flight.setDestination(rs.getString("destination"));
                flight.setCapacity(rs.getInt("capacity"));
                flight.setClassCode(rs.getString("classCode"));
                flight.setClassName(rs.getString("className"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    // Method to update flight details
    public void updateFlight(Flight flight) {
        String sql = "UPDATE flights SET flightName = ?, source = ?, destination = ?, capacity = ?, classCode = ?, className = ? WHERE flightCode = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, flight.getFlightName());
            statement.setString(2, flight.getSource());
            statement.setString(3, flight.getDestination());
            statement.setInt(4, flight.getCapacity());
            statement.setString(5, flight.getClassCode());
            statement.setString(6, flight.getClassName());
            statement.setString(7, flight.getFlightCode());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a flight
    public void deleteFlight(String flightCode) {
        String sql = "DELETE FROM flights WHERE flightCode = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, flightCode);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all flights
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getInt("id"));
                flight.setFlightCode(rs.getString("flightCode"));
                flight.setFlightName(rs.getString("flightName"));
                flight.setSource(rs.getString("source"));
                flight.setDestination(rs.getString("destination"));
                flight.setCapacity(rs.getInt("capacity"));
                flight.setClassCode(rs.getString("classCode"));
                flight.setClassName(rs.getString("className"));
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
}
