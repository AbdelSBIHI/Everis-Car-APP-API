package com.everis.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.everis.entity.Car;
import com.everis.utils.LogInterceptor;

@Stateless
@Interceptors(LogInterceptor.class)
public class CarMessagePublisher {

private static Logger logger = LoggerFactory.getLogger(CarMessagePublisher.class);
	
	@Resource(mappedName = "jms/destination")
	private Queue queue;
	
	@Resource(mappedName = "jms/CarsApp_API_ManagementQueue")
	private ConnectionFactory cf;
	
	public void carEditionJms(Car car) {
		try {
			Connection connection = cf.createConnection();
			Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			MessageProducer mp = session.createProducer(queue);
			mp.send(session.createObjectMessage(car));
		} catch (JMSException e) {
			logger.error(e.getMessage());
		}
	}
}
