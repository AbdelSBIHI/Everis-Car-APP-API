package com.everis.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;


import com.everis.utils.UUIDConverter;

public class CarPk implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1924858706276850371L;
	@Column(name = "ID")
    @Convert(converter = UUIDConverter.class)
	private UUID id;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarPk other = (CarPk) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
	

}
