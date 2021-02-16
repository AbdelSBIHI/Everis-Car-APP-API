package com.everis.control;

import static org.junit.Assert.assertTrue;

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
	private CarDao carDao;

	@Test
	public void testGetCars() {

		try {
			final List<Car> allcars = new ArrayList<>();
			Mockito.when(carDao.findAll()).thenReturn(allcars);
			final List<Car> cars = carService.getCars();
			Assert.assertEquals(allcars, cars);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void testGetCar() {

		final Car oneCar = new Car();

		try {
			Mockito.when(carDao.findOne("f6cf16d4-6b91-11eb-9439-0242ac130002")).thenReturn(oneCar);
			final Car car = carService.getCar("f6cf16d4-6b91-11eb-9439-0242ac130002");
			Assert.assertNotNull(oneCar);
			Assert.assertEquals(oneCar, car);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void testCreateCar() {

		try {
			final Car car = new Car("BMW", new Date(), "Germany");
			Mockito.when(carDao.addCar(car)).thenReturn(car);
			final Car newCar = carService.createCar(car);
			Assert.assertEquals(car, newCar);
		} catch (Exception e) {
			Assert.fail();
		}

	}

	@Test
	public void testUpdateCar() {
		try {
			Car car = new Car("BMW", new Date(), "Germany");
			String id = "f6cf16d4-6b91-11eb-9439-0242ac130002";
			Mockito.when(carDao.editCar(id, car)).thenReturn(car);
			Car updatedCar = carService.updateCar(id, car);
			Assert.assertEquals(car, updatedCar);
		} catch (Exception e) {
			Assert.fail();

		}
	}

	@Test
	public void testDeleteCar() {

		try {
			Mockito.when(carDao.deleteCar(Mockito.anyString())).thenReturn(true);
			assertTrue(carService.deleteCar(Mockito.anyString()));
		} catch (Exception e) {
			Assert.fail();
		}
	}

}
