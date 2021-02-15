package com.everis.boundary;

import java.util.List;

public class ErrorDto {
	
	List<String> errors;

	public ErrorDto(List<String> msg) {
		super();
		this.errors=msg;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	

}
