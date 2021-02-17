package com.everis.control;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.everis.control.CarService;
import com.everis.entity.Car;
import com.everis.entity.CarDto;

@Stateless
public class CarService {

	@Inject
	private PersistenceService<Car, String> persistenceService;

	/**
	 * Method to get a list of Car Entity available
	 */

	public List<CarDto> getCars() {
		List<CarDto> cars=this.persistenceService.getEntitiesWithNamedQuery("Car.findAll", Car.class).stream().
				map(car -> CarDto.mapToCardto(car)).collect(Collectors.toList());
		return cars;
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
	public CarDto createCar(final Car car) {

		return CarDto.mapToCardto(persistenceService.persistEntity(car));

	}

	/**
	 * Method to update Car using its id
	 */
	public CarDto updateCar(String id, Car car) {
		car.setId(id);
		return CarDto.mapToCardto(this.persistenceService.mergeEntity(car));
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