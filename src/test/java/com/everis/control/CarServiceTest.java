package com.everis.control;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.everis.entity.Car;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

	@InjectMocks
	CarService carService;

	@Mock
	private PersistenceService<Car, String> carPersistenceService;

	@Test
	public void testGetCars() {

		final List<Car> allcars = new ArrayList<>();
		Mockito.when(carPersistenceService.getEntitiesWithNamedQuery("Car.findAll", Car.class)).thenReturn(allcars);
		final List<Car> cars = carService.getCars();
		Assert.assertEquals(allcars, cars);

	}

	@Test
	public void testGetCar() {

		final Car oneCar = new Car();

		Mockito.when(carPersistenceService.getEntityByID(Car.class,"f6cf16d4-6b91-11eb-9439-0242ac130002")).thenReturn(oneCar);
		final Car car = carService.getCar("f6cf16d4-6b91-11eb-9439-0242ac130002");
		Assert.assertNotNull(oneCar);
		Assert.assertEquals(oneCar, car);

	}

	@Test
	public void testCreateCar() {

		final Car car = new Car("BMW", new Date(), "Germany");
		Mockito.when(carPersistenceService.persistEntity(car)).thenReturn(car);
		final Car newCar = carService.createCar(car);
		Assert.assertEquals(car, newCar);

	}

	@Test
	public void testUpdateCar() {

		Car car = new Car("BMW", new Date(), "Germany");
		String id = "f6cf16d4-6b91-11eb-9439-0242ac130002";
		Mockito.when(carPersistenceService.mergeEntity(car)).thenReturn(car);
		Car updatedCar = carService.updateCar(id, car);
		Assert.assertEquals(car, updatedCar);

	}

	
	@Test
	public void testDeleteCar() {
		Car car = new Car("BMW", new Date(), "Germany");
		String id = "f6cf16d4-6b91-11eb-9439-0242ac130002";
		
		when(this.carPersistenceService.getEntityByID(Car.class, id)).thenReturn(car);
		this.carService.deleteCar(id);
		Mockito.verify(this.carPersistenceService, Mockito.times(1)).deleteEntity(car);

	}

}
