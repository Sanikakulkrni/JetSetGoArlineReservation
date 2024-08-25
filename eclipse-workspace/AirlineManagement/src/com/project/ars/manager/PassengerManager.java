package com.project.ars.manager;

import com.project.ars.bean.Passenger;
import com.project.ars.operations.PassengerDAO;

import java.util.List;

public class PassengerManager {
    private final PassengerDAO passengerDAO;

    public PassengerManager() {
        this.passengerDAO = new PassengerDAO();
    }

    public void addPassenger(Passenger passenger) {
        passengerDAO.addPassenger(passenger);
    }

    public List<Passenger> getAllPassengers() {
        return passengerDAO.getAllPassengers();
    }

    // Add other methods for passenger operations if needed, e.g., updatePassenger(), deletePassenger()
}
