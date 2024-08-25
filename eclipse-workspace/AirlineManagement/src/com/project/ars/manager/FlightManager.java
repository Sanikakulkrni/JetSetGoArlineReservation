package com.project.ars.manager;

import com.project.ars.bean.Flight;
import com.project.ars.operations.FlightDAO;

import java.util.List;

public class FlightManager {
    private FlightDAO flightDAO;

    // Constructor to initialize FlightDAO
    public FlightManager() {
        this.flightDAO = new FlightDAO();
    }

    // Add a new flight
    public void addFlight(Flight flight) {
        flightDAO.addFlight(flight);
    }

    // Get a flight by its code
    public Flight getFlight(String flightCode) {
        return flightDAO.getFlight(flightCode);
    }

    // Update flight details
    public void updateFlight(Flight flight) {
        flightDAO.updateFlight(flight);
    }

    // Delete a flight
    public void deleteFlight(String flightCode) {
        flightDAO.deleteFlight(flightCode);
    }

    // Get all flights
    public List<Flight> getAllFlights() {
        return flightDAO.getAllFlights();
    }
}
