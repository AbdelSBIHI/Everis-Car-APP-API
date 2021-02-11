package com.everis.control;


import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import com.everis.control.CarService;
import com.everis.entity.Car;

@Stateless
public class CarService {
  
    @PersistenceContext(unitName = "car-unit")
    private EntityManager em;
    private final static Logger LOGGER = Logger.getLogger(CarService.class);

    /**
     * Method to get a list of Car Entity available
     */
    public List<Car> getCars()
    {
      LOGGER.info("Getting all Cars' List: ");
      List<Car> listCars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
      LOGGER.info("Car's List completed: " + listCars);
      return listCars;
    }
    /**
     * Method to get one Car info using its id                             
     */
    public Car getCar(UUID id)
    {
    	LOGGER.info("Getting Car by it Id... ");
    	Car car = em.createNamedQuery("Car.findById", Car.class).setParameter("id", id).getSingleResult();
    	LOGGER.info("Car selected: " + car);
    	return car;

    }

    /**
     * Method to create Car 
     */
    public Car createCar(final Car car)
    {
    	LOGGER.info("Creating Car... ");
    	em.persist(car);
    	LOGGER.info("Created Car: " + car);
    	return car;
    }
    /**
     * Method to update Car using its id
     */
    public Car updateCar(Car car)
    {
    	LOGGER.info("Updating Car...");
    	em.merge(car);
    	LOGGER.info("Car updated " + car);
    	return car;
    }
   
    /**
     * Method to delete Car using its id
     *
     */
    public void deleteCar(UUID id)
    {
    	LOGGER.info("Deleting Car... ");
    	final Car car = getCar(id);
    	LOGGER.info("Car's Id chosen for delete: " + id);
    	em.remove(car);
    	LOGGER.info("Deleted Car: " + car);	
    }

}