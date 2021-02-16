package com.everis.control;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.everis.entity.Car;

@RunWith(MockitoJUnitRunner.class)
public class CarDaoTest {
	
	@InjectMocks
	CarDao carDao;
	
	@Mock
	EntityManager entityManager;
	
	Car car;

	@Before
	public void setUp() throws Exception {
		car= new Car();
	}

	@Test
	public void testFindAll() {
		final List<Car> allcars = new ArrayList<>();
		Mockito.when(entityManager.createNamedQuery("Car.findAll", Car.class).getResultList()).thenReturn(allcars);
		final List<Car> cars = carDao.findAll();
		Assert.assertEquals(allcars, cars);
	}

	@Test
	public void testFindOne() {
		when(entityManager.find(any(), anyString())).thenReturn(car);
		Car findCar = carDao.findOne(any());
		assertEquals(car, findCar);
	}

	@Test
	public void testAddCar() {
		doNothing().when(entityManager).persist(any(Car.class));	
		Car createdCar = carDao.addCar(car);	
		assertEquals(car,createdCar);
		
	}

	@Test
	public void testEditCar() {
		when(entityManager.merge(any(Car.class))).thenReturn(car);		
		Car updatedCar = carDao.editCar(car.getId(),car);	
		assertEquals(car, updatedCar);
	}

}
