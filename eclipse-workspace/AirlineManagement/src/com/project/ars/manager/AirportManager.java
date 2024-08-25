package com.project.ars.manager;

import com.project.ars.bean.Airport;
import com.project.ars.operations.AirportDAO;

import java.util.List;

public class AirportManager {
    private AirportDAO airportDAO = new AirportDAO();

    // Method to add a new airport
    public void addAirport(Airport airport) {
        airportDAO.addAirport(airport);
    }

    // Method to get an airport by its ID
    public Airport getAirport(int id) {
        return airportDAO.getAirport(id);
    }

    // Method to update an airport
    public void updateAirport(Airport airport) {
        airportDAO.updateAirport(airport);
    }

    // Method to delete an airport by its ID
    public void deleteAirport(int id) {
        airportDAO.deleteAirport(id);
    }

    // Method to get all airports
    public List<Airport> getAllAirports() {
        return airportDAO.getAllAirports();
    }
}
