package com.project.ars.manager;

import com.project.ars.bean.Booking;
import com.project.ars.operations.BookingDAO;

import java.util.List;

public class BookingManager {
    private BookingDAO bookingDAO = new BookingDAO();

    // Method to add a booking
    public void addBooking(Booking booking) {
        bookingDAO.addBooking(booking);
    }

    // Method to get a booking by its ID
    public Booking getBooking(int id) {
        return bookingDAO.getBooking(id);
    }

    // Method to update a booking
    public void updateBooking(Booking booking) {
        bookingDAO.updateBooking(booking);
    }

    // Method to delete a booking by its ID
    public void deleteBooking(int id) {
        bookingDAO.deleteBooking(id);
    }

    // Method to get all bookings
    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }
}
