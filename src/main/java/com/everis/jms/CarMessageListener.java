package com.everis.jms;

import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.everis.control.CarService;
import com.everis.entity.Car;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import javax.ejb.ActivationConfigProperty;


@MessageDriven(activationConfig = {
	    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/destination"),
	    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
	    @ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "jms/CarsApp_API_ManagementQueue")
	})
public class CarMessageListener implements MessageListener {
	
	@Inject
	CarService carService;
	
	private static Logger logger = LoggerFactory.getLogger(CarMessagePublisher.class);
	
	@Override
	public void onMessage(Message message) {

		try {
		    logger.info("Message received: " + message.getBody(String.class));
		    ObjectMapper objectMapper = new ObjectMapper();
		    Car car = objectMapper.readValue(message.getBody(String.class), Car.class);
		    logger.info("Parsed car: " + car);
		    carService.updateCar(car.getId(),car);
		    logger.info("Created Car: " + car);
		    
		} catch (JMSException | IOException e) {
			logger.error(e.getMessage());
		}
	}

}
