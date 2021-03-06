package com.everis.boundary;

import javax.validation.Valid;


import javax.ws.rs.core.Response;

import com.everis.entity.CarDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * @author AbdelSBIHI interface for mapping the API with swagger documentation
 */
public interface ICarResources {

	/**
	 * fetching all cars
	 * 
	 * @return Response
	 */
	@Operation(description = "Get a list of cars")
	@ApiResponse(responseCode = "200", description = "Returns List of Cars Available")
	@Parameter(description = "number of pages", required = false)
	@Parameter(description = "size of data to present in one page", required = false)
	@Parameter(description = "value to be searched in the data", required = false)
	@Parameter(description = "how data will be sorted asc or desc", required = false)
	public Response getCars(int page, int size, String filterBy, String orderBy,String sort);

	/**
	 * Pick a Car by its Id
	 * 
	 * @param id id of the car
	 * @return Response
	 */
	@Operation(description = "Pick a Car by its Id", responses = {
			@ApiResponse(responseCode = "200", description = "Returns a Car by its id"),
			@ApiResponse(responseCode = "404", description = "Car with given id doesn't exists") })
	@Parameter(description = "Refference of Car's Id selected by the user", required = true)
	public Response getCarById(final String id);

	/**
	 * create a new car
	 * 
	 * @param car the car to be created
	 * @return Response
	 */
	@Operation(description = "Create new car", responses = {
			@ApiResponse(responseCode = "201", description = "Car has been successfully created"),
			@ApiResponse(responseCode = "400", description = "creation of a new car has been failed") })
	@Parameter(description = "Refference of object car to be created", required = true)
	public Response createCar(@Valid final CarDto car);

	/**
	 * update existing car
	 * 
	 * @param car car to be updated
	 * @return Response
	 */
	@Operation(description = "Update new car", responses = {
			@ApiResponse(responseCode = "200", description = "Car has been successfully updated"),
			@ApiResponse(responseCode = "400", description = "Car cannot be udpated"),
			@ApiResponse(responseCode = "404", description = "Car with given id doesn't exists") })
	@Parameter(description = "Refference of Car's id to be updated", required = true)
	@Parameter(description = "Refference of Object Car to be updated", required = true)
	public Response updateCar(final String id, @Valid final CarDto car);

	/**
	 * delete an existing car
	 * 
	 * @param id id of the car to be deleted
	 * @return Response
	 */
	@Operation(description = "Delete existing car", responses = {
			@ApiResponse(responseCode = "200", description = "Car has been successfully deleted"),
			@ApiResponse(responseCode = "404", description = "Car with given id doesn't exists") })
	@Parameter(description = "Refference of Car's id selected by the user", required = true)
	public Response deleteCar(final String id);

}
