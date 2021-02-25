package com.everis.control;


import javax.ejb.Stateless;

import javax.inject.Inject;

import com.everis.control.CarService;
import com.everis.entity.Car;
import com.everis.entity.CarDto;
import com.everis.utils.PagesPresentation;

@Stateless
public class CarService {

	@Inject
	private PersistenceService<Car, String> persistenceService;

	/**
	 * Method to get a list of Car Entity available
	 */

	public PagesPresentation<CarDto> getCars(int page, int size, String sort, String orderBy,String filterBy) {
		
		return persistenceService.findCars(size, page, sort, orderBy, filterBy);
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

		return CarDto.MapToCarDto(persistenceService.persistEntity(car));

	}

	/**
	 * Method to update Car using its id
	 */
	public CarDto updateCar(String id, Car car) {
		car.setId(id);
		return CarDto.MapToCarDto(this.persistenceService.mergeEntity(car));
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