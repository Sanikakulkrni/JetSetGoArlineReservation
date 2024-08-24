package com.project.ars.operations;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.project.ars.bean.Airline;
import com.project.ars.db.DatabaseConnection;

public class AirlineDAO {

    // Create Airline
    public boolean addAirline(Airline airline) {
        boolean isAdded = false;
        String query = "INSERT INTO airlines (name, country) VALUES (?, ?)";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setString(1, airline.getName());
            ps.setString(2, airline.getCountry());
            isAdded = ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return isAdded;
    }

    // Retrieve All Airlines
    public List<Airline> getAllAirlines() {
        List<Airline> airlines = new ArrayList<>();
        String query = "SELECT * FROM airlines";
        
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
             
            while (rs.next()) {
                Airline airline = new Airline();
                airline.setId(rs.getInt("id"));
                airline.setName(rs.getString("name"));
                airline.setCountry(rs.getString("country"));
                airlines.add(airline);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return airlines;
    }

    // Update Airline
    public boolean updateAirline(Airline airline) {
        boolean isUpdated = false;
        String query = "UPDATE airlines SET name = ?, country = ? WHERE id = ?";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setString(1, airline.getName());
            ps.setString(2, airline.getCountry());
            ps.setInt(3, airline.getId());
            isUpdated = ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return isUpdated;
    }

    // Delete Airline
    public boolean deleteAirline(int id) {
        boolean isDeleted = false;
        String query = "DELETE FROM airlines WHERE id = ?";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setInt(1, id);
            isDeleted = ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return isDeleted;
    }

    // Retrieve Airline by ID
    public Airline getAirlineById(int id) {
        Airline airline = null;
        String query = "SELECT * FROM airlines WHERE id = ?";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                	airline = new Airline();
                    airline.setId(rs.getInt("id"));
                    airline.setName(rs.getString("name"));
                    airline.setCountry(rs.getString("country"));
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return airline;
    }
}             
