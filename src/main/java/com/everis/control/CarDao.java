package com.everis.control;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.everis.entity.Car;

/**
 * @author AbdelSBIHI 
 * class that interact with data base
 */
@Stateless
public class CarDao {

	@PersistenceContext(unitName = "car-unit")
	private EntityManager em;

	/**
	 * getting all cars
	 * 
	 * @return List of Car
	 */
	public List<Car> findAll() {

		List<Car> listCars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
		return listCars;
	}

	/**
	 * find car by ID
	 * 
	 * @param id id of the car to be fetch
	 * @return Car return the fetched Car
	 */
	public Car findOne(String id) {

		Car car = em.createNamedQuery("Car.findById", Car.class).setParameter("id", id).getSingleResult();
		return car;

	}

	/**
	 * create a new car
	 * 
	 * @param car car to be added
	 * @return Car return the created Car
	 */
	public Car addCar(final Car car) {

		em.persist(car);
		return car;
	}

	/**
	 * update existing car
	 * 
	 * @param id  id of car to be updated
	 * @param car car to be assigned
	 * @return Car updated car
	 */
	public Car editCar(String id, Car car) {

		em.merge(car);
		return car;
	}

	/**
	 * delete a car with it's ID
	 * 
	 * @param id the id of car to be deleted
	 * @return boolean true if deleted false if not
	 */
	public Boolean deleteCar(String id) {
		try {

			final Car car = findOne(id);
			em.remove(car);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
