package com.everis.boundary;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.everis.control.CarService;
import com.everis.entity.Car;

public class CarResourcesTest {

	@InjectMocks
	private CarResources carResource;

	@Mock
	private CarService carService;

	private Car car;

	@Before
	public void setUp() throws Exception {
		car = new Car("bmw", new Date(), "germany");
		car.setId("e72fd0a4-f7a5-42d4-908e-7bc1dc62f857");
	}

	@Test
	public void testCreateCar() {
		doNothing().when(carService).createCar(car);
		Response responseTest = this.carResource.createCar(car);
		assertEquals(Status.CREATED.getStatusCode(), responseTest.getStatus());
		assertEquals(car, responseTest.getEntity());

	}

	@Test
	public void testGetCars() {

		List<Car> expectedCars = new ArrayList<Car>();
		when(this.carService.getCars()).thenReturn(expectedCars);
		Response responseTest = this.carResource.getCars();
		assertEquals(expectedCars, responseTest.getEntity());
		assertEquals(Status.OK.getStatusCode(), responseTest.getStatus());

	}

	@Test
	public void testGetCarById() {
		when(this.carService.getCar(car.getId())).thenReturn(car);
		Response response = this.carResource.getCarById(car.getId());
		assertEquals(car, response.getEntity());
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	@Test
	public void testUpdateCar() {
		String brand = "Renault";
		when(this.carService.getCar(car.getId())).thenReturn(car);
		car.setBrand(brand);
		doNothing().when(this.carService).updateCar(car.getId(), car);
		Response response = this.carResource.updateCar(car.getId(), car);
		assertEquals(car.getBrand(), ((Car) response.getEntity()).getBrand());
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	@Test
	public void testDeleteCar() {

		doNothing().when(this.carService).deleteCar(car.getId());
		Response response = this.carResource.deleteCar(car.getId());
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	@Test
	public void testCreateCarWithInvalidID() {

		Response response = null;
		Car carErrors = new Car();
		carErrors.setId(car.getId());
		carErrors.setBrand("Renault");
		carErrors.setCountry(null);
		doThrow(PersistenceException.class).when(this.carService).createCar(carErrors);
		response = this.carResource.createCar(carErrors);
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	public void testGetCarByIdWithInvalidID() {

		when(this.carService.getCar(car.getId())).thenThrow(EntityNotFoundException.class);
		Response response = this.carResource.getCarById(car.getId());
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testUpdateCarWithInvalidID() {

		Response response = null;
		when(this.carService.getCar(car.getId())).thenReturn(car);
		doThrow(EntityNotFoundException.class).when(this.carService).updateCar(car.getId(), car);
		response = this.carResource.updateCar(car.getId(), car);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

	}

	@Test
	public void testDeleteCarWithInvalidID() {

		doThrow(EntityNotFoundException.class).when(this.carService).deleteCar(car.getId());
		Response response = this.carResource.deleteCar(car.getId());
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

}
