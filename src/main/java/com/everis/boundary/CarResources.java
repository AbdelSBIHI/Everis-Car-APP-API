package com.everis.boundary;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

import org.apache.log4j.Logger;
import com.everis.boundary.CarResources;
import com.everis.control.CarService;
import com.everis.entity.Car;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;



@Path("cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@OpenAPIDefinition(info = @Info(title = "Everis-Car-App_API", version = "0.0", description = "Car's CRUD Functionality"))
public class CarResources {

    CarService carService = new CarService() ;
    
    private final static Logger LOGGER = Logger.getLogger(CarResources.class);

    @GET
    @Operation(description = "Get a list of cars")
    @ApiResponse(responseCode = "200", description = "Returns List of Cars Available")
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
    @Path("/{id}")
    @Operation(description = "Pick a Car by its Id", responses = {
    	    @ApiResponse(responseCode = "200", description = "Returns a Car by its id"),
    	    @ApiResponse(responseCode = "404", description = "Car with given id doesn't exists") })
    @Parameter(description = "Refference of Car's Id selected by the user", required = true)
    public Response getCarById(final @PathParam("id") int id)
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
    @Operation(description = "Create new car", responses = {
    	    @ApiResponse(responseCode = "200", description = "Car has been successfully created"),
    	    @ApiResponse(responseCode = "400", description = "creation of a new car has been failed") })
    @Parameter(description = "Refference of object car to be created", required = true)
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
    @Path("/{id}")
    @Operation(description = "Update new car", responses = {
    	    @ApiResponse(responseCode = "200", description = "Car has been successfully updated"),
    	    @ApiResponse(responseCode = "400", description = "Car cannot be udpated"),
    	    @ApiResponse(responseCode = "404", description = "Car with given id doesn't exists") } )
        @Parameter(description = "Refference of Car's id to be updated", required = true)
        @Parameter(description = "Refference of Object Car to be updated", required = true)
    public Response updateCar(final @PathParam("id") int id) {
    	
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
    @Path("/{id}")
    @Operation(description = "Delete existing car", responses = {
    	    @ApiResponse(responseCode = "200", description = "Car has been successfully deleted"),
    	    @ApiResponse(responseCode = "404", description = "Car with given id doesn't exists") } )
    @Parameter(description = "Refference of Car's id selected by the user", required = true)
    public Response deleteCar(final @PathParam("id") int id) {
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
