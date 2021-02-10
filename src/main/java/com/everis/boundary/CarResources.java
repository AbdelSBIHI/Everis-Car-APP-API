package com.everis.boundary;

import java.util.List;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

import org.apache.log4j.Logger;
import com.everis.boundary.CarResources;
import com.everis.control.CarService;
import com.everis.entity.Car;


@Path("/cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CarResources implements ICarResources {

	@Inject
    CarService carService  ;
    
    private final static Logger LOGGER = Logger.getLogger(CarResources.class);

    @GET
    @Override
    public Response getCars() {
    		LOGGER.info("Retrieving Car's List from car service: ");
    	try {
    		List<Car> cars = carService.getCars();
			LOGGER.info("Car's list retrieved");
			return Response.ok().entity(cars).build();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("Car's list not found");
			return Response.status(Status.NOT_FOUND).build();
      }
    }

    @GET
    @Override
    @Path("/{id}")
    public Response getCarById(final @PathParam("id") UUID id)
    {		

    		LOGGER.info("Getting Car by its Id: " + id);
	    try {
			return Response.ok().entity(carService.getCar(id)).build();
		} catch (Exception e) {
			LOGGER.error("Car with id : " + id + " Not Found");
			 return Response.status(Status.NOT_FOUND).build();
		}
    }

    @POST
    @Override
    public Response createCar(final Car car) {
    		LOGGER.info("Creating new Car: ");
    	try {
			LOGGER.info("new car created: "+car);
			return Response.status(Status.CREATED).entity(carService.createCar(car)).build();
		} catch (Exception e) {
			LOGGER.error("Failed to create new car");
			return Response.status(Status.BAD_REQUEST).build();
    }
    }
  
    @PUT
    @Override
    @Path("/{id}")
    public Response updateCar(final @PathParam("id") UUID id) {
    	
      LOGGER.info("Update new car!");
    	try {
			Car newCar = this.carService.getCar(id);
			this.carService.updateCar(newCar);
      LOGGER.info("Car Successfully Updated: " + newCar + "Id: " + id);
			return Response.status(Status.OK).entity(newCar).build();
		} catch (Exception e) {
      LOGGER.error("Error: Car not found!");
			return Response.status(Status.NOT_FOUND).entity("Car with id " + id + " not found").build();			
		}	  
    }

    @DELETE
    @Override
    @Path("/{id}")
    public Response deleteCar(final @PathParam("id") UUID id) {
    		LOGGER.info("Deleting car with id:"+id);
	    try {
			this.carService.deleteCar(id);
			LOGGER.info("Car with id " + id + " Deleted");
			return Response.ok().entity("Car Deleted Successfully").build();
		} catch (Exception e) {
			LOGGER.error("Failed to delete Car with id " + id);
       return Response.status(Status.NOT_FOUND).entity("Car with id " + id + " not found").build();
		}
    }

}    
