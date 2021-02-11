package com.everis.boundary;

import java.util.List;

import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

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
    

    @GET
    @Override
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
    @Override
    @Path("/{id}")
    public Response getCarById(final @PathParam("id") UUID id)
    {		

	    try {
			return Response.ok().entity(carService.getCar(id)).build();
		} catch (Exception e) {
			 return Response.status(Status.NOT_FOUND).build();
		}
    }

    @POST
    @Override
    public Response createCar(final Car car) {
    	try {
			return Response.status(Status.CREATED).entity(carService.createCar(car)).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).build();
    }
    }
  
    @PUT
    @Override
    @Path("/{id}")
    public Response updateCar(final @PathParam("id") UUID id) {
    	
    	try {
			Car newCar = this.carService.getCar(id);
			this.carService.updateCar(newCar);
			return Response.status(Status.OK).entity(newCar).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).entity("Car with id " + id + " not found").build();			
		}	  
    }

    @DELETE
    @Override
    @Path("/{id}")
    public Response deleteCar(final @PathParam("id") UUID id) {
	    try {
			this.carService.deleteCar(id);
			return Response.ok().entity("Car Deleted Successfully").build();
		} catch (Exception e) {
       return Response.status(Status.NOT_FOUND).entity("Car with id " + id + " not found").build();
		}
    }

}    
