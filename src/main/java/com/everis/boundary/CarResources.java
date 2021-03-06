package com.everis.boundary;


import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

import org.apache.log4j.Logger;
import com.everis.boundary.CarResources;
import com.everis.control.CarService;
import com.everis.entity.Car;
import com.everis.entity.CarDto;
import com.everis.jms.CarMessagePublisher;
import com.everis.utils.PagesPresentation;

@Path("/cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CarResources implements ICarResources {

	@Inject
	CarService carService;
	@Inject
	CarMessagePublisher carMessagePublisher;

	private final static Logger LOGGER = Logger.getLogger(CarResources.class);

	@GET
	@Override
	public Response getCars(@DefaultValue("1") @QueryParam(value = "page") int page,
			@DefaultValue("5") @QueryParam(value = "size") int size,
			@DefaultValue("") @QueryParam(value = "filterBy") String filterBy,
			@QueryParam(value = "orderBy") String orderBy,
			@QueryParam("sort") @DefaultValue("asc") String sort) {
		LOGGER.info("Retrieving Car's List from car service: ");
		try {
			PagesPresentation<CarDto> cars =carService.getCars(page, size, sort, orderBy, filterBy);
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
	public Response getCarById(final @PathParam("id") String id) {

		LOGGER.info("Getting Car by its Id: " + id);
		
			Car car=this.carService.getCar(id);
			CarDto cardto = CarDto.MapToCarDto(car);
			return Response.ok().entity(cardto).build();
		
	}

	@POST
	@Override
	public Response createCar(final CarDto cardto) {

		LOGGER.info("Creating new Car: ");
		try {
			LOGGER.info("new car created: " + cardto);
			return Response.status(Status.CREATED).entity(carService.createCar(cardto)).build();
		} catch (PersistenceException e) {
			LOGGER.error("Failed to create new car");
			throw e;
		}
	}

	@PUT
	@Override
	@Path("/{id}")
	public Response updateCar(final @PathParam("id") String id, final CarDto cardto) {

		
		LOGGER.info("Validating Car's info: " + cardto);
		try {
			
			carMessagePublisher.carEditionJms(Car.MapToCar(cardto));
			LOGGER.info("Car Successfully Updated: " + cardto + "Id: " + id);
			return Response.ok().entity(cardto).build();
		} catch (PersistenceException e) {
			LOGGER.info(" cannot update the car ");
			throw e;
		}

	}

	@DELETE
	@Override
	@Path("/{id}")
	public Response deleteCar(final @PathParam("id") String id) {
		LOGGER.info("Deleting car with id:" + id);
		try {
			this.carService.softDeleteCar(id);
			LOGGER.info("Car with id " + id + " Deleted");
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error("Failed to delete Car with id " + id);
			return Response.status(Status.NOT_FOUND).entity("Car with id " + id + " not found").build();
		}
	}

}
