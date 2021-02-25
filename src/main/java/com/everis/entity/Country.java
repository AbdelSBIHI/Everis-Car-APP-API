package com.everis.entity;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "country")
public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8532276311149381117L;

	@Id
	@GenericGenerator(name = "UUID", strategy = "uuid2")
	@GeneratedValue(generator = "UUID")
	@Column(name = "ID")
	private String id;

	@NotNull
	@Column(name = "NAME", nullable = false)
	private String name;

	@OneToMany(mappedBy = "country")
	private Set<Car> cars = new HashSet<Car>();
	

	public Country() {
		
	}

	public Country(@NotNull String name) {
		super();
		this.name = name;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}
	
	

}
