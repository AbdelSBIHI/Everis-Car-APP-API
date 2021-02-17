package com.everis.control;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.everis.control.CarService;
import com.everis.entity.Car;

@Stateless
public class CarService {

	@Inject
	private PersistenceService<Car, String> persistenceService;

	/**
	 * Method to get a list of Car Entity available
	 */

	public List<Car> getCars() {

		return this.persistenceService.getEntitiesWithNamedQuery("Car.findAll", Car.class);
	}

	/**
	 * Method to get one Car info using its id
	 * 
	 */
	public Car getCar(String id) {

		Car car = this.persistenceService.getEntityByID(Car.class, id);
		return car;

	}

	/**
	 * Method to create Car
	 */
	public Car createCar(final Car car) {

		return this.persistenceService.persistEntity(car);

	}

	/**
	 * Method to update Car using its id
	 */
	public Car updateCar(String id, Car car) {

		return this.persistenceService.mergeEntity(car);
	}

	/**
	 * Method to delete Car using its id
	 *
	 */
	public Boolean deleteCar(String id) {

		Car car = this.getCar(id);
		return this.persistenceService.deleteEntity(car);

	}

}