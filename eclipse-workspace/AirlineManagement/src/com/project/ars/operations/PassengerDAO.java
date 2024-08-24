package com.project.ars.operations;



import com.project.ars.bean.Passenger;
import com.project.ars.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAO {
    private Connection connection;

    public PassengerDAO() {
        connection = new DatabaseConnection().getConnection();
    }

    public void addPassenger(Passenger passenger) {
        String query = "INSERT INTO passengers (name, age, gender, contact_number) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, passenger.getName());
            ps.setInt(2, passenger.getAge());
            ps.setString(3, passenger.getGender());
            ps.setString(4, passenger.getContactNumber());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Passenger> getAllPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        String query = "SELECT * FROM passengers";
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }

    // Implement other methods like updatePassenger(), deletePassenger() if needed
}
