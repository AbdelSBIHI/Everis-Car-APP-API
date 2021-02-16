package com.everis.utils;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.everis.boundary.ErrorDto;

@Provider
public class CarExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		
		return Response.status(Response.Status.BAD_REQUEST)
                .entity(prepareMessage(exception))
                .type(MediaType.APPLICATION_JSON)
                .build();
	}
	
	  private ErrorDto prepareMessage(ConstraintViolationException exception) {
	      List<String> msg = new ArrayList<String>(); 
	      for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
	          msg.add(cv.getMessage());
	      }
	      return new ErrorDto(msg);
	  }

}
