package com.project.ars.main;




import com.project.ars.operations.PassengerDAO;
import com.project.ars.operations.FlightDAO;
import com.project.ars.operations.BookingDAO;
import com.project.ars.operations.AirlineDAO;
import com.project.ars.operations.AirportDAO;
import com.project.ars.operations.ReportDAO;

import java.util.Scanner;

public class FlightReservationApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PassengerDAO passengerDAO = new PassengerDAO();
    private static final FlightDAO flightDAO = new FlightDAO();
    private static final BookingDAO bookingDAO = new BookingDAO();
    private static final AirlineDAO airlineDAO = new AirlineDAO();
    private static final AirportDAO airportDAO = new AirportDAO();
    private static final ReportDAO reportDAO = new ReportDAO();

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
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

    // Implement methods for each functionality (e.g., addFlight, viewFlights, etc.)
}
