package com.everis.boundary;


import java.util.List;



import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
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
    public Response getCarById(final @PathParam("id") String id)
    {		

    		LOGGER.info("Getting Car by its Id: " + id);
	    try {
			return Response.ok().entity(carService.getCar(id)).build();
		} catch (Exception e) {
			LOGGER.error("Car with id : " + id + " Not Found");
			 return Response.status(Status.NOT_FOUND).entity("Car with id " + id + " not found").build();
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
			Client client = ClientBuilder.newBuilder().build();
	        WebTarget target =client.target("http://localhost:8080/cars");
	        Response response = target.request()
	                                .post(Entity.entity(car,
	                                        MediaType.APPLICATION_JSON), Response.class);
			return response;
    }
    }
  
    @PUT
    @Override
    @Path("/{id}")
    public Response updateCar(final @PathParam("id") String id , final Car car) {
    	
    		car.setId(id); 	
    		LOGGER.info("Validating Car's info: " + car);
	    try {
    		carService.updateCar(id, car);
    		LOGGER.info("Car Successfully Updated: " + car + "Id: " + id);
    		return Response.ok().entity(car).build();
	    } catch (Exception e) {

			 LOGGER.error("Failed to update car with id " + id);
	 	    Client client = ClientBuilder.newBuilder().build();
	        WebTarget target =client.target("http://localhost:8080/cars");
	        Response response = target.request()
	                                .post(Entity.entity(car,
	                                        MediaType.APPLICATION_JSON), Response.class);
	        return response;
	    }
	
    }

    @DELETE
    @Override
    @Path("/{id}")
    public Response deleteCar(final @PathParam("id") String id) {
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
