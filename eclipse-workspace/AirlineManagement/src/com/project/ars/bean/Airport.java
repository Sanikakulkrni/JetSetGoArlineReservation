package com.project.ars.bean;



public class Airport {
    private int id;
    private String name;
    private String city;
    private String country;
	public Airport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Airport(int id, String name, String city, String country) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.country = country;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Airport [id=" + id + ", name=" + name + ", city=" + city + ", country=" + country + "]";
	}

    
    // Constructor, Getters, Setters, and toString() method
}
