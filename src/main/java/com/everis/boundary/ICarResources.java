package com.everis.boundary;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import com.everis.entity.Car;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@OpenAPIDefinition(info = @Info(title = "Everis-Car-App_API", version = "0.0", description = "Car's CRUD Functionality"))
public interface ICarResources {
	

    @GET
    @Operation(description = "Get a list of cars")
    @ApiResponse(responseCode = "200", description = "Returns List of Cars Available")
    public Response getCars();
    

    @GET
    @Path("/{id}")
    @Operation(description = "Pick a Car by its Id", responses = {
    	    @ApiResponse(responseCode = "200", description = "Returns a Car by its id"),
    	    @ApiResponse(responseCode = "404", description = "Car with given id doesn't exists") })
    @Parameter(description = "Refference of Car's Id selected by the user", required = true)
    public Response getCarById(final @PathParam("id") int id);
    

    @POST
    @Operation(description = "Create new car", responses = {
    	    @ApiResponse(responseCode = "200", description = "Car has been successfully created"),
    	    @ApiResponse(responseCode = "400", description = "creation of a new car has been failed") })
    @Parameter(description = "Refference of object car to be created", required = true)
    public Response createCar(final Car car); 
    
    
    @PUT
    @Path("/{id}")
    @Operation(description = "Update new car", responses = {
    	    @ApiResponse(responseCode = "200", description = "Car has been successfully updated"),
    	    @ApiResponse(responseCode = "400", description = "Car cannot be udpated"),
    	    @ApiResponse(responseCode = "404", description = "Car with given id doesn't exists") } )
        @Parameter(description = "Refference of Car's id to be updated", required = true)
        @Parameter(description = "Refference of Object Car to be updated", required = true)
    public Response updateCar(final @PathParam("id") int id);
    @DELETE
    @Path("/{id}")
    @Operation(description = "Delete existing car", responses = {
    	    @ApiResponse(responseCode = "200", description = "Car has been successfully deleted"),
    	    @ApiResponse(responseCode = "404", description = "Car with given id doesn't exists") } )
    @Parameter(description = "Refference of Car's id selected by the user", required = true)
    public Response deleteCar(final @PathParam("id") int id); 


}
