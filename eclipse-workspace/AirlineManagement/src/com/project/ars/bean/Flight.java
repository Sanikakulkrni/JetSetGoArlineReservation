package com.project.ars.bean;

public class Flight {
    private int id;
    private String flightCode;
    private String flightName;
    private String source;
    private String destination;
    private int capacity;
    private String classCode;
    private String className;
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Flight(int id, String flightCode, String flightName, String source, String destination, int capacity,
			String classCode, String className) {
		super();
		this.id = id;
		this.flightCode = flightCode;
		this.flightName = flightName;
		this.source = source;
		this.destination = destination;
		this.capacity = capacity;
		this.classCode = classCode;
		this.className = className;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFlightCode() {
		return flightCode;
	}
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String toString() {
		return "Flight [id=" + id + ", flightCode=" + flightCode + ", flightName=" + flightName + ", source=" + source
				+ ", destination=" + destination + ", capacity=" + capacity + ", classCode=" + classCode
				+ ", className=" + className + "]";
	}

    // Constructor, Getters, Setters, and toString() method
}
