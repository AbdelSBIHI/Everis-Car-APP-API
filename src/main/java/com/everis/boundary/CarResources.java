package com.everis.boundary;

import java.util.List;

import javax.ejb.EJB;
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
public class CarResources {

    
    CarService carService = new CarService();

    @GET
    public Response getCars() {
    		
    	try {
    		List<Car> cars = carService.getCars();
			return Response.ok().entity(cars).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		}   	
    }

    @GET
    @Path("/{id}")
    public Response getCarById(final @PathParam("id") int id)
    {		
	    try {
			return Response.ok().entity(carService.getCar(id)).build();
		} catch (Exception e) {
			 return Response.status(Status.NOT_FOUND).build();
		}
    }
    @POST
    public Response createCar(final Car car) {
    	try {
			return Response.status(Status.CREATED).entity(carService.createCar(car)).build();
		} catch (Exception e) {
		    return Response.status(Status.BAD_REQUEST).build();
		}
	}    

    @PUT
    @Path("/{id}")
    public Response updateCar(final @PathParam("id") int id) {
    	
    	try {
			Car newCar = this.carService.getCar(id);
			this.carService.updateCar(newCar);
			return Response.status(Status.OK).entity(newCar).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).build();			
		}	  
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCar(final @PathParam("id") int id) {
	    try {
			this.carService.deleteCar(id);
			return Response.ok().entity("Car Deleted Successfully").build();
		} catch (Exception e) {
		    return Response.status(Status.NOT_FOUND).entity("Car with id " + id + " not found").build();
		}
    }
}
    
