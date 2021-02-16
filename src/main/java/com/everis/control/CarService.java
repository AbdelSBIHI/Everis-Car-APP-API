package com.everis.control;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.everis.control.CarService;
import com.everis.entity.Car;

/**
 * @author AbdelSBIHI
 *  
 */
@Stateless
public class CarService {

	@Inject
	private CarDao carDao;

	/**
	 * get a list of Car available
	 * 
	 * @return list of cars
	 */

	public List<Car> getCars() {
		List<Car> listCars = carDao.findAll();
		return listCars;
	}

	/**
	 * get one Car info using its id
	 * 
	 * @param id id of the car
	 * @return a Car if the car exists, null if not
	 */
	public Car getCar(String id) {
		Car car = carDao.findOne(id);
		return car;

	}

	/**
	 * create Car
	 * 
	 * @param car object contains information of the car the we want to create
	 * @return Car: the saved car
	 */
	public Car createCar(final Car car) {
		carDao.addCar(car);
		return car;
	}

	/**
	 * update Car using its id
	 * 
	 * @param car contains the new info of an existing car
	 * @return Car :a car with new informations
	 */
	public Car updateCar(String id, Car car) {
		carDao.editCar(id, car);
		return car;
	}

	/**
	 * delete Car using its id
	 * 
	 * @param id: the id of the card we want to delete
	 * @return true if the car deleted, false if not
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