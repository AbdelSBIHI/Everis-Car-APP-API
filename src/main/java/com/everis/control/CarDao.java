package com.everis.control;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.everis.entity.Car;

@Stateless
public class CarDao {

	@PersistenceContext(unitName = "car-unit")
	private EntityManager em;

	public List<Car> findAll() {

		List<Car> listCars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
		return listCars;
	}

	public Car findOne(String id) {

		Car car = em.createNamedQuery("Car.findById", Car.class).setParameter("id", id).getSingleResult();
		return car;

	}

	public Car addCar(final Car car) {

		em.persist(car);
		return car;
	}

	public Car editCar(String id, Car car) {

		em.merge(car);
		return car;
	}

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
