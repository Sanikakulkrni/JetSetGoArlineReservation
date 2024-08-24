package com.project.ars.bean;



public class Booking {
    private int id;
    private int passengerId;
    private int flightId;
    private String bookingDate;
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Booking(int id, int passengerId, int flightId, String bookingDate) {
		super();
		this.id = id;
		this.passengerId = passengerId;
		this.flightId = flightId;
		this.bookingDate = bookingDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	@Override
	public String toString() {
		return "Booking [id=" + id + ", passengerId=" + passengerId + ", flightId=" + flightId + ", bookingDate="
				+ bookingDate + "]";
	}
    
    

    // Constructor, Getters, Setters, and toString() method
}
