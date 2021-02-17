package com.everis.entity;

import java.util.Date;


public class CarDto {
	
	private String id;
	private String brand;
	private String country;
	private Date createdAt;
	private Date lastUpdated;
    private Date registration;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Date getRegistration() {
		return registration;
	}
	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	public CarDto(String id, String brand, String country, Date createdAt, Date lastUpdated, Date registration) {
		super();
		this.id = id;
		this.brand = brand;
		this.country = country;
		this.createdAt = createdAt;
		this.lastUpdated = lastUpdated;
		this.registration = registration;
	}
	
    public static  CarDto mapToCardto(Car car) {
    	
		return new CarDto(car.getId(), car.getBrand(), car.getBrand(), car.getCreatedAt(), car.getLastUpdated(), car.getRegistration());
    }
	

	

}
