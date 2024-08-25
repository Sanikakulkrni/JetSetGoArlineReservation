package com.project.ars.main;

import com.project.ars.bean.*;
import com.project.ars.manager.*;
import com.project.ars.operations.UserDAO;

import java.util.List;
import java.util.Scanner;

public class FlightReservationApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserDAO userDAO = new UserDAO();
    private static final FlightManager flightManager = new FlightManager();
    private static final BookingManager bookingManager = new BookingManager();
    private static final PassengerManager passengerManager = new PassengerManager();
    private static final AirportManager airportManager = new AirportManager();
    private static final AirlineManager airlineManager = new AirlineManager();
    private static User loggedInUser = null; // Track the logged-in user

    public static void main(String[] args) {
        while (loggedInUser == null) {
            displayAuthMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        // If logged in, display the main menu
        mainMenu();
    }

    private static void displayAuthMenu() {
        System.out.println("Flight Reservation System - Authentication");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next(); // In production, you should hash the password
        String role = "passenger"; // Default role; can be modified

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        if (userDAO.registerUser(user)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed.");
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        loggedInUser = userDAO.validateLogin(username, password);
        if (loggedInUser != null) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }

    private static void mainMenu() {
        while (true) {
            displayMenu();  // Display main menu options
            int choice = getUserChoice();  // Get user's choice
            switch (choice) {
                case 1:
                    checkAdminAccess(() -> addFlight());
                    break;
                case 2:
                    viewFlights();
                    break;
                case 3:
                    addPassenger();
                    break;
                case 4:
                    viewPassengers();
                    break;
                case 5:
                    bookFlight();
                    break;
                case 6:
                    cancelBooking();
                    break;
                case 7:
                    viewBookings();
                    break;
                case 8:
                    checkAdminAccess(() -> updateFlightDetails());
                    break;
                case 9:
                    searchFlights();
                    break;
                case 10:
                    checkAdminAccess(() -> generateReports());
                    break;
                case 11:
                    checkAdminAccess(() -> addAirline());
                    break;
                case 12:
                    viewAirlines();
                    break;
                case 13:
                    checkAdminAccess(() -> manageAirports());
                    break;
                case 14:
                    updatePassengerDetails();
                    break;
                case 15:
                    viewFlightAvailability();
                    break;
                case 16:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void checkAdminAccess(Runnable action) {
        if ("admin".equalsIgnoreCase(loggedInUser.getRole())) {
            action.run();
        } else {
            System.out.println("Access denied. Admins only.");
        }
    }

    private static void displayMenu() {
        System.out.println("Flight Reservation System");
        System.out.println("1. Add Flight");
        System.out.println("2. View Flights");
        System.out.println("3. Add Passenger");
        System.out.println("4. View Passengers");
        System.out.println("5. Book Flight");
        System.out.println("6. Cancel Booking");
        System.out.println("7. View Bookings");
        System.out.println("8. Update Flight Details");
        System.out.println("9. Search Flights");
        System.out.println("10. Generate Reports");
        System.out.println("11. Add Airline");
        System.out.println("12. View Airlines");
        System.out.println("13. Manage Airports");
        System.out.println("14. Update Passenger Details");
        System.out.println("15. View Flight Availability");
        System.out.println("16. Exit");
    }

    private static int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    // Methods for each functionality
    private static void addFlight() {
        System.out.println("Enter flight details:");
        // Collect flight details from user
        Flight flight = new Flight();
        // Set flight details from user input
        flightManager.addFlight(flight);
        System.out.println("Flight added successfully.");
    }

    private static void viewFlights() {
        List<Flight> flights = flightManager.getAllFlights();
        // Display flights
    }

    private static void addPassenger() {
        System.out.println("Enter passenger details:");
        // Collect passenger details from user
        Passenger passenger = new Passenger();
        // Set passenger details from user input
        passengerManager.addPassenger(passenger);
        System.out.println("Passenger added successfully.");
    }

    private static void viewPassengers() {
        List<Passenger> passengers = passengerManager.getAllPassengers();
        // Display passengers
    }

    private static void bookFlight() {
        System.out.println("Enter booking details:");
        // Collect booking details from user
        Booking booking = new Booking();
        // Set booking details from user input
        bookingManager.addBooking(booking);
        System.out.println("Flight booked successfully.");
    }

    private static void cancelBooking() {
        System.out.print("Enter booking ID to cancel: ");
        int bookingId = scanner.nextInt();
        bookingManager.deleteBooking(bookingId);
        System.out.println("Booking canceled successfully.");
    }

    private static void viewBookings() {
        List<Booking> bookings = bookingManager.getAllBookings();
        // Display bookings
    }

    private static void updateFlightDetails() {
        System.out.println("Enter updated flight details:");
        // Collect updated flight details from user
        Flight flight = new Flight();
        // Set flight details from user input
        flightManager.updateFlight(flight);
        System.out.println("Flight updated successfully.");
    }

    private static void searchFlights() {
        System.out.print("Enter flight code or other criteria: ");
        String criteria = scanner.next();
        Flight flight = flightManager.getFlight(criteria);
        // Display flight details
    }

    private static void generateReports() {
        // Logic for generating reports
    }

    private static void addAirline() {
        System.out.println("Enter airline details:");
        // Collect airline details from user
        Airline airline = new Airline();
        // Set airline details from user input
        airlineManager.addAirline(airline);
        System.out.println("Airline added successfully.");
    }

    private static void viewAirlines() {
        List<Airline> airlines = airlineManager.getAllAirlines();
        // Display airlines
    }

    private static void manageAirports() {
        System.out.println("Enter airport details:");
        // Collect airport details from user
        Airport airport = new Airport();
        // Set airport details from user input
        airportManager.addAirport(airport);
        System.out.println("Airport added successfully.");
    }

    private static void updatePassengerDetails() {
        System.out.println("Enter updated passenger details:");
        // Collect updated passenger details from user
        Passenger passenger = new Passenger();
        // Set passenger details from user input
        // Call appropriate method from PassengerManager
    }

    private static void viewFlightAvailability() {
        // Logic for viewing flight availability
    }
}
