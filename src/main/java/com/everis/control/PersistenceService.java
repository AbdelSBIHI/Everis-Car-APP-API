package com.everis.control;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
public class PersistenceService<T,L> {

	@PersistenceContext(unitName = "car-unit")
	private EntityManager em;

	
	public List<T> getEntitiesWithNamedQuery(String namedQuery, Class<T> c) {
		TypedQuery<T> query = em.createNamedQuery(namedQuery, c);
		return query.getResultList();
	}


	public T getEntityByID(Class<T> c, L id) {
		return this.em.find(c, id);
	}

	
	public T persistEntity(T entity) {
		this.em.persist(entity);
		return entity;
	}

	public T mergeEntity(T entity) {
		 this.em.merge(entity);
		 return entity;
	}

	public boolean deleteEntity(T entity) {
		
		try {
			this.em.remove(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
