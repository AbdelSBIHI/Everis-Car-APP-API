package com.everis.control;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

import org.apache.log4j.Logger;

import com.everis.entity.Car;

@Singleton
public class CarDeletionManager {
	
	 private final static Logger LOGGER = Logger.getLogger(CarDeletionManager.class);

	    
	    @EJB
	    private CarService service;
	    
	    @Schedule(second = "0", minute = "1", hour = "0")
	    public final void purgeOldEntries() {
		
		LOGGER.info("Deleting Cars ready for permanent removal...");
		List<Car> forDeletion = service.getReadyForDeletion();
		
		for(Car car : forDeletion) {
		    service.deleteCar(car.getId());
		    LOGGER.info("Car chosen for deleting: " + car);
		}
		
		LOGGER.info(" Deleting Process Completed");
	    }

}
