package com.everis.entity;

import java.util.Date;

import javax.json.bind.annotation.JsonbDateFormat;


public class CarDto {

	private String id;
	private String brand;
	private String country;
	@JsonbDateFormat("yyyy-MM-dd hh:mm:ss")
	private Date createdAt;
	@JsonbDateFormat("yyyy-MM-dd hh:mm:ss")
	private Date lastUpdated;
	@JsonbDateFormat("yyyy-MM-dd hh:mm:ss")
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

	public CarDto() {

	}

	static public CarDto MapToCarDto(Car car) {
		CarDto carDto = new CarDto();
		carDto.setId(car.getId());
		carDto.setCreatedAt(car.getCreatedAt());
		carDto.setLastUpdated(car.getLastUpdated());
		carDto.setBrand(car.getBrand().getName());
		carDto.setCountry(car.getCountry().getName());
		carDto.setRegistration(car.getRegistration());
		return carDto;
	}

}
