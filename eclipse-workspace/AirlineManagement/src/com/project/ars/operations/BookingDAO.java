package com.project.ars.operations;



import com.project.ars.bean.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookingDAO {
    private Connection connection;

    // Constructor to initialize database connection
    public BookingDAO() {
        try {
            // Load JDBC driver (optional if using newer JDBC versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new booking
    public void addBooking(Booking booking) {
        String sql = "INSERT INTO bookings (passengerId, flightId, bookingDate) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, booking.getPassengerId());
            statement.setInt(2, booking.getFlightId());
            statement.setString(3, booking.getBookingDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a booking by its ID
    public Booking getBooking(int id) {
        Booking booking = null;
        String sql = "SELECT * FROM bookings WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setPassengerId(rs.getInt("passengerId"));
                booking.setFlightId(rs.getInt("flightId"));
                booking.setBookingDate(rs.getString("bookingDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    // Method to update a booking
    public void updateBooking(Booking booking) {
        String sql = "UPDATE bookings SET passengerId = ?, flightId = ?, bookingDate = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, booking.getPassengerId());
            statement.setInt(2, booking.getFlightId());
            statement.setString(3, booking.getBookingDate());
            statement.setInt(4, booking.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a booking by its ID
    public void deleteBooking(int id) {
        String sql = "DELETE FROM bookings WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all bookings
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setPassengerId(rs.getInt("passengerId"));
                booking.setFlightId(rs.getInt("flightId"));
                booking.setBookingDate(rs.getString("bookingDate"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
