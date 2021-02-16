package com.everis.control;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.everis.control.CarService;
import com.everis.entity.Car;

@Stateless
public class CarService {
	
	@Inject
	private CarDao carDao;

	/**
	 * Method to get a list of Car Entity available
	 */

	public List<Car> getCars() {
		List<Car> listCars = carDao.findAll();
		return listCars;
	}

	/**
	 * Method to get one Car info using its id
	 * 
	 */
	public Car getCar(String id)  {
		Car car = carDao.findOne(id);
		return car;

	}

	/**
	 * Method to create Car
	 */
	public Car createCar(final Car car) {
		carDao.addCar(car);
		return car;
	}

	/**
	 * Method to update Car using its id
	 */
	public Car updateCar(String id, Car car) {
		carDao.editCar(id, car);
		return car;
	}

	/**
	 * Method to delete Car using its id
	 *
	 */
	public Boolean deleteCar(String id) {
		try {
			carDao.deleteCar(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}