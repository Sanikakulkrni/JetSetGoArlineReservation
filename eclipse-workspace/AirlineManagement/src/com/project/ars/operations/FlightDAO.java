package com.project.ars.operations;

import com.project.ars.bean.Flight;
import com.project.ars.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {
    private Connection connection;

    public FlightDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Ensure connection is open before performing operations
    private void checkConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseConnection.getConnection();
        }
    }

    public void addFlight(Flight flight) {
        String sql = "INSERT INTO flights (flight_code, flight_name, source, destination, capacity, class_code, class_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, flight.getFlightCode());
                statement.setString(2, flight.getFlightName());
                statement.setString(3, flight.getSource());
                statement.setString(4, flight.getDestination());
                statement.setInt(5, flight.getCapacity());
                statement.setString(6, flight.getClassCode());
                statement.setString(7, flight.getClassName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Flight getFlight(String flightCode) {
        Flight flight = null;
        String sql = "SELECT * FROM flights WHERE flight_code = ?";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, flightCode);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    flight = new Flight();
                    flight.setId(rs.getInt("id"));
                    flight.setFlightCode(rs.getString("flight_code"));
                    flight.setFlightName(rs.getString("flight_name"));
                    flight.setSource(rs.getString("source"));
                    flight.setDestination(rs.getString("destination"));
                    flight.setCapacity(rs.getInt("capacity"));
                    flight.setClassCode(rs.getString("class_code"));
                    flight.setClassName(rs.getString("class_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    public void updateFlight(Flight flight) {
        String sql = "UPDATE flights SET flight_name = ?, source = ?, destination = ?, capacity = ?, class_code = ?, class_name = ? WHERE flight_code = ?";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, flight.getFlightName());
                statement.setString(2, flight.getSource());
                statement.setString(3, flight.getDestination());
                statement.setInt(4, flight.getCapacity());
                statement.setString(5, flight.getClassCode());
                statement.setString(6, flight.getClassName());
                statement.setString(7, flight.getFlightCode());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFlight(String flightCode) {
        String sql = "DELETE FROM flights WHERE flight_code = ?";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, flightCode);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Flight flight = new Flight();
                    flight.setId(rs.getInt("id"));
                    flight.setFlightCode(rs.getString("flight_code"));
                    flight.setFlightName(rs.getString("flight_name"));
                    flight.setSource(rs.getString("source"));
                    flight.setDestination(rs.getString("destination"));
                    flight.setCapacity(rs.getInt("capacity"));
                    flight.setClassCode(rs.getString("class_code"));
                    flight.setClassName(rs.getString("class_name"));
                    flights.add(flight);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
}
