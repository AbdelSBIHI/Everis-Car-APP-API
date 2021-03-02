package com.everis.control;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.everis.control.CarService;
import com.everis.entity.Car;
import com.everis.entity.CarDto;
import com.everis.utils.PagesPresentation;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.REQUIRED;

@Stateless
public class CarService {

	@Inject
	private PersistenceService<Car, String> persistenceService;

	/**
	 * Method to get a list of Car Entity available
	 */

	public PagesPresentation<CarDto> getCars(int page, int size, String sort, String orderBy, String filterBy) {

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
	 * Method to soft delete Car using its id
	 *
	 */

	@Transactional(REQUIRED)
	public Boolean softDeleteCar(String id) {

		Car car = this.getCar(id);
		if (car != null) {
			car.setToBeDeleted(true);
			this.persistenceService.mergeEntity(car);
			return true;
		} else
			return false;

	}

	/**
	 * Method to delete Car using its id
	 *
	 */
	public Boolean deleteCar(String id) {

		Car car = this.getCar(id);
		return this.persistenceService.deleteEntity(car);

	}

	/**
	 * Method to get cars that must be completely removed from application
	 * 
	 * @return [listDeletedCars] List of Cars ready to be removed
	 */
	public List<Car> getReadyForDeletion() {

		List<Car> listDeletedCars = persistenceService.getReadyForDeletion();
		return listDeletedCars;

	}

}