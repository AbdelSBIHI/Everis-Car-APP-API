package com.everis.utils;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


@Interceptor
public class LogsInterceptor {

	
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		
		final Logger LOGGER = LogManager.getLogger(context.getClass());
        String className = context.getTarget().getClass().getSimpleName();
        LOGGER.info("Entering: " + className + ", Method: " + context.getMethod().getName());
        Object result = context.proceed();
        LOGGER.info("Exiting: " + className + ", Method: "  + context.getMethod().getName());
		return result;
	}
}
