package com.everis.entity;

import java.io.Serializable;

import java.lang.String;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

/**
 * Entity implementation class for Entity: Car
 *
 */
@Entity
@Table(name = "car")
@NamedQueries(value = { @NamedQuery(name = "Car.findAll", query = "select c from Car c "),
		@NamedQuery(name = "Car.findById", query = "select c from Car c where c.id = :id"), })
@XmlRootElement
public class Car implements Serializable {

	@Id
	@GenericGenerator(name = "UUID", strategy = "uuid2")
	@GeneratedValue(generator = "UUID")
	@Column(name = "ID")
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id", nullable = false)
	private Brand brand;

	@Column(name = "REGISTRATION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registration;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;

	@Column(name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "LAST_UPDATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdated;
	private static final long serialVersionUID = 1L;

	public Car() {

	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Date getRegistration() {
		return this.registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
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
	
	static public Car MapToCar(CarDto carDto) {
		Car car= new Car();
		car.setId(car.getId());
		car.setCreatedAt(car.getCreatedAt());
		car.setLastUpdated(car.getLastUpdated());
		car.setBrand(new Brand(carDto.getBrand()));
		car.setCountry(new Country(carDto.getCountry()));
		car.setRegistration(car.getRegistration());
		return car;
	}

}
