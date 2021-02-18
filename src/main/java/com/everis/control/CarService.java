package com.everis.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import com.everis.control.CarService;
import com.everis.entity.Car;
import com.everis.entity.CarDto;

@Stateless
public class CarService {

	@Inject
	private PersistenceService<Car, String> persistenceService;
	
	private Map<String, String> filterMap = new HashMap<String, String>();

	/**
	 * Method to get a list of Car Entity available
	 */

	public List<CarDto> getCars(int page, int size, String filterBy, String orderBy) {
		
		filterMap.put("brand", filterBy);
		filterMap.put("country", filterBy);
		filterMap.put("registration", filterBy);
		TypedQuery<Car> query = this.persistenceService.getEntitiesQuery(Car.class, filterMap, orderBy);
		query.setFirstResult((size * page) - size);
		query.setMaxResults(size);
		return query.getResultList().stream().map(car -> car.mapToDto()).collect(Collectors.toList());
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

		return persistenceService.persistEntity(car).mapToDto();

	}

	/**
	 * Method to update Car using its id
	 */
	public CarDto updateCar(String id, Car car) {
		car.setId(id);
		return this.persistenceService.mergeEntity(car).mapToDto();
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