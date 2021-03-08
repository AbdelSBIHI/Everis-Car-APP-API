package com.everis.boundary;

import static org.junit.Assert.*;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.everis.control.CarService;
import com.everis.entity.Car;
import com.everis.entity.CarDto;


@RunWith(MockitoJUnitRunner.class)
public class CarResourcesTest {

	@InjectMocks
	private CarResources carResource;

	@Mock
	private CarService carService;

	private Car car;

	private String carId = "f6cf16d4-6b91-11eb-9439-0242ac130002";

	@Before
	public void setUp() throws Exception {
		car = new Car();
		car.setId(carId);
//		car.setBrand("Renault");
//		car.setCountry("France");

	}

//	@Test
//	public void getCarsTest() {
//		when(this.carService.getCars(0, 0, null, null, carId)).thenReturn(new ArrayList<CarDto>());
//		List<Car> expectedCars = new ArrayList<Car>();
//		Response responseTest = this.carResource.getCars(0, 0, null, null, carId);
//		assertEquals(expectedCars, responseTest.getEntity());
//		assertEquals(Status.OK.getStatusCode(), responseTest.getStatus());
//	}

	@Test
	public void getCarByIdWithValidID() {

		when(this.carService.getCar(car.getId())).thenReturn(car);
		Response response = this.carResource.getCarById(car.getId());
		assertEquals(car, response.getEntity());
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	@Test
	public void createCarrWithValidValues() {

		when(this.carService.createCar(CarDto.MapToCarDto(car))).thenReturn(CarDto.MapToCarDto(car) );
		Response responseTest = this.carResource.createCar(CarDto.MapToCarDto(car));
		assertEquals(car, responseTest.getEntity());
		assertEquals(Status.CREATED.getStatusCode(), responseTest.getStatus());

	}

	@Test
	public void updateCarWithValidID() {

		
//		car.setBrand(brand);
		when(this.carService.updateCar(carId, car)).thenReturn(CarDto.MapToCarDto(car));
		Response response = this.carResource.updateCar(carId, CarDto.MapToCarDto(car));
		assertEquals(car.getBrand(), ((Car) response.getEntity()).getBrand());
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	@Test
	public void deleteCarCarWithValidID() {

		when(this.carService.deleteCar(carId)).thenReturn(true);
		Response response = this.carResource.deleteCar(carId);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}


//	@Test
//	public void createCarrWithInvalidValues() {
//
//		Response response = null;
//		Car invalidCar = new Car();
//		invalidCar.setBrand("Renault");
//		invalidCar.setCountry(null);
//		when(this.carService.createCar(invalidCar)).thenReturn(invalidCar);
//		response = this.carResource.createCar(invalidCar);
//		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
//
//	}

	@Test
	public void getCarByIdWithInvalidID() {
		when(this.carService.getCar(carId)).thenThrow(EntityNotFoundException.class);
		Response response = this.carResource.getCarById(carId);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

//	@Test
//	public void updateCarWithInvalidID() {
//
//		String id = "e72fd0a4-f7a5-42d4-908e-7bc1dc62f000";
//		Response response = null;
//		when(this.carService.updateCar(id, car)).thenReturn(car);
//		response = this.carResource.updateCar(id, car);
//		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
//	}

	@Test
	public void deleteCarCarWithInvalidID() {
		String id = "e72fd0a4-f7a5-42d4-908e-7bc1dc62f000";
		doThrow(EntityNotFoundException.class).when(this.carService).deleteCar(id);
		Response response = this.carResource.deleteCar(id);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}
}
