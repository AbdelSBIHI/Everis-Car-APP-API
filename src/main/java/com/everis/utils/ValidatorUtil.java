package com.everis.utils;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;

public class ValidatorUtil {
	
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();


	public static <T> ArrayList<String> validate(T car) {

		Set<ConstraintViolation<T>> violations = validator.validate(car);
		ArrayList<String> violationMessages = new ArrayList<String>();
		for (ConstraintViolation<T> violation : violations) 	violationMessages.add(violation.getMessage());
		
		return violationMessages;

	}

}
