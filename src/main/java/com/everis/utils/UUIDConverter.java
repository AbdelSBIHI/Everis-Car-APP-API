package com.everis.utils;

import java.util.UUID;

import javax.persistence.AttributeConverter;

public class UUIDConverter implements AttributeConverter<UUID, String> {

	@Override
	public String convertToDatabaseColumn(UUID attribute) {
		
		return String.valueOf(attribute);
	}

	@Override
	public UUID convertToEntityAttribute(String dbData) {
		
		return UUID.fromString(dbData);
	}

}
