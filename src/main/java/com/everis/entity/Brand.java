package com.everis.entity;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "brand")
@NamedQueries(value = { @NamedQuery(name = "Brand.findByName", query = "select b from Brand b where b.name = :name")})
public class Brand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2029507249882641695L;

	@Id
	@GenericGenerator(name = "UUID", strategy = "uuid2")
	@GeneratedValue(generator = "UUID")
	@Column(name = "ID")
	private String id;

	@NotNull
	@Column(name = "NAME", nullable = false)
	private String name;

	@OneToMany(mappedBy = "brand")
	private Set<Car> cars = new HashSet<Car>();

	
	

	public Brand() {
	
	}

	public Brand(@NotNull String name) {
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
