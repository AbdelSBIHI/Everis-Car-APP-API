package com.everis.entity;

import java.io.Serializable;


import java.lang.String;
import java.util.Date;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
/**
 * Entity implementation class for Entity: Car
 *
 */
@Entity
@IdClass(CarPk.class)
@Table(name = "car")
@NamedQueries(value = {
		@NamedQuery(name = "Car.findAll", query = "select c from Car c "),
		@NamedQuery(name = "Car.findById", query = "select c from Car c where c.id = :id"),
	}
)
public class Car implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private UUID id;
	
	@Column(name = "BRAND")
    @NotNull(message = "Brand can't be null")
	private String brand;
	
	@Column(name = "REGISTRATION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registration;
	
	@Column(name = "COUNTRY")
    @NotNull(message = "Country can't be null")
	private String country;
	
	@Column(name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name = "LAST_UPDATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdated;
	private static final long serialVersionUID = 1L;

	public Car() {
		super();
	}   
	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}   
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}   
	public Date getRegistration() {
		return this.registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}   
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}   
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}   
	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
   
}

