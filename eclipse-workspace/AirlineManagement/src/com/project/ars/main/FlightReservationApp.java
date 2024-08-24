package com.project.ars.main;

import com.project.ars.operations.UserDAO;
import com.project.ars.bean.User;

import java.util.Scanner;

public class FlightReservationApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserDAO userDAO = new UserDAO();
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
                    addFlight();
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
                    updateFlightDetails();
                    break;
                case 9:
                    searchFlights();
                    break;
                case 10:
                    generateReports();
                    break;
                case 11:
                    addAirline();
                    break;
                case 12:
                    viewAirlines();
                    break;
                case 13:
                    manageAirports();
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

    

    // Existing main menu and functionalities go here...
    // Existing methods for each functionality
    private static void addFlight() {
        // Logic for adding a flight
    }

    private static void viewFlights() {
        // Logic for viewing flights
    }

    private static void addPassenger() {
        // Logic for adding a passenger
    }

    private static void viewPassengers() {
        // Logic for viewing passengers
    }

    private static void bookFlight() {
        // Logic for booking a flight
    }

    private static void cancelBooking() {
        // Logic for canceling a booking
    }

    private static void viewBookings() {
        // Logic for viewing bookings
    }

    private static void updateFlightDetails() {
        // Logic for updating flight details
    }

    private static void searchFlights() {
        // Logic for searching flights
    }

    private static void generateReports() {
        // Logic for generating reports
    }

    private static void addAirline() {
        // Logic for adding an airline
    }

    private static void viewAirlines() {
        // Logic for viewing airlines
    }

    private static void manageAirports() {
        // Logic for managing airports
    }

    private static void updatePassengerDetails() {
        // Logic for updating passenger details
    }

    private static void viewFlightAvailability() {
        // Logic for viewing flight availability
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
}
