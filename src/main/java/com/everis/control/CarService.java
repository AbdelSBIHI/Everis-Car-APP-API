package com.everis.control;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.everis.control.CarService;
import com.everis.entity.Car;


public class CarService {

    @PersistenceContext(unitName = "car-unit")
    private EntityManager em;

    /**
     * Method to get a list of Car Entity available
     */
    public List<Car> getCars()
    {	
		List<Car> listCars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
		return listCars;
    }

    /**
     * Method to get one Car info using its id                             
     */
    public Car getCar(int id)
    { 	
    	Car car = em.createNamedQuery("Car.findById", Car.class).setParameter("id", id).getSingleResult();
    	if (car != null) { 					
    	    return car;

    	} else { 	    
    	    return new Car();
    	}
    }

    /**
     * Method to create Car 
     */
    public Car createCar(final Car car)
    { 	
    	em.persist(car);
    	return car;
    }
    /**
     * Method to update Car using its id
     */
    public Car updateCar(Car car)
    {
    	em.merge(car);
    	return car;
    }
   
    /**
     * Method to delete Car using its id
     *
     */
    public void deleteCar(int id)
    {
    	final Car car = getCar(id);
    	em.remove(car);	
    }

}