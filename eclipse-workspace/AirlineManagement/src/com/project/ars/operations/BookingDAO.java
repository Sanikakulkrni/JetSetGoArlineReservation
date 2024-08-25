package com.project.ars.operations;

import com.project.ars.bean.Booking;
import com.project.ars.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private Connection connection;

    public BookingDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Ensure connection is open before performing operations
    private void checkConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseConnection.getConnection();
        }
    }

    public void addBooking(Booking booking) {
        String sql = "INSERT INTO bookings (passengerId, flightId, bookingDate) VALUES (?, ?, ?)";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, booking.getPassengerId());
                statement.setInt(2, booking.getFlightId());
                statement.setString(3, booking.getBookingDate());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Booking getBooking(int id) {
        Booking booking = null;
        String sql = "SELECT * FROM bookings WHERE id = ?";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        booking = new Booking();
                        booking.setId(rs.getInt("id"));
                        booking.setPassengerId(rs.getInt("passengerId"));
                        booking.setFlightId(rs.getInt("flightId"));
                        booking.setBookingDate(rs.getString("bookingDate"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    public void updateBooking(Booking booking) {
        String sql = "UPDATE bookings SET passengerId = ?, flightId = ?, bookingDate = ? WHERE id = ?";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, booking.getPassengerId());
                statement.setInt(2, booking.getFlightId());
                statement.setString(3, booking.getBookingDate());
                statement.setInt(4, booking.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBooking(int id) {
        String sql = "DELETE FROM bookings WHERE id = ?";
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

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";
        try {
            checkConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Booking booking = new Booking();
                    booking.setId(rs.getInt("id"));
                    booking.setPassengerId(rs.getInt("passengerId"));
                    booking.setFlightId(rs.getInt("flightId"));
                    booking.setBookingDate(rs.getString("bookingDate"));
                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
