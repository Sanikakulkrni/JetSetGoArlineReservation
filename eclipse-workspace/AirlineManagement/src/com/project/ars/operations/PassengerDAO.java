package com.project.ars.operations;

import com.project.ars.bean.Passenger;
import com.project.ars.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAO {
    private Connection connection;

    public PassengerDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Ensure connection is open before performing operations
    private void checkConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseConnection.getConnection();
        }
    }

    public void addPassenger(Passenger passenger) {
        String query = "INSERT INTO passengers (name, age, gender, contact_number) VALUES (?, ?, ?, ?)";
        try {
            checkConnection();
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, passenger.getName());
                ps.setInt(2, passenger.getAge());
                ps.setString(3, passenger.getGender());
                ps.setString(4, passenger.getContactNumber());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Passenger> getAllPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        String query = "SELECT * FROM passengers";
        try {
            checkConnection();
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Passenger passenger = new Passenger(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("contact_number")
                    );
                    passengers.add(passenger);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }

    public Passenger getPassenger(int id) {
        Passenger passenger = null;
        String query = "SELECT * FROM passengers WHERE id = ?";
        try {
            checkConnection();
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        passenger = new Passenger(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("contact_number")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passenger;
    }

    public void updatePassenger(Passenger passenger) {
        String query = "UPDATE passengers SET name = ?, age = ?, gender = ?, contact_number = ? WHERE id = ?";
        try {
            checkConnection();
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, passenger.getName());
                ps.setInt(2, passenger.getAge());
                ps.setString(3, passenger.getGender());
                ps.setString(4, passenger.getContactNumber());
                ps.setInt(5, passenger.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePassenger(int id) {
        String query = "DELETE FROM passengers WHERE id = ?";
        try {
            checkConnection();
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
