package com.project.ars.manager;

import com.project.ars.bean.Airline;
import com.project.ars.operations.AirlineDAO;

import java.util.List;

public class AirlineManager {
    private AirlineDAO airlineDAO;

    // Constructor to initialize AirlineDAO
    public AirlineManager() {
        this.airlineDAO = new AirlineDAO();
    }

    // Method to add a new airline
    public void addAirline(Airline airline) {
        airlineDAO.addAirline(airline);
    }

    // Method to get an airline by its ID
    public Airline getAirline(int id) {
        return airlineDAO.getAirline(id);
    }

    // Method to update an airline
    public void updateAirline(Airline airline) {
        airlineDAO.updateAirline(airline);
    }

    // Method to delete an airline by its ID
    public void deleteAirline(int id) {
        airlineDAO.deleteAirline(id);
    }

    // Method to get all airlines
    public List<Airline> getAllAirlines() {
        return airlineDAO.getAllAirlines();
    }
}
