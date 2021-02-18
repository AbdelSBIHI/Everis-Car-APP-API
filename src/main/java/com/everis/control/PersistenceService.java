package com.everis.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class PersistenceService<T, L> {

	@PersistenceContext(unitName = "car-unit")
	private EntityManager em;

	public TypedQuery<T> getEntitiesQuery(Class<T> c, Map<String, String> filterMap, String orderBy) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(c);
		Root<T> root = criteriaQuery.from(c);
		criteriaQuery.select(root);

		List<Predicate> predicates = new ArrayList<Predicate>();

		for (Map.Entry<String, String> entry : filterMap.entrySet()) {
			Expression<String> expression = criteriaBuilder.lower(root.get(entry.getKey()).as(String.class));
			String value = String.format("%%%s%%", entry.getValue().toLowerCase());
			predicates.add(criteriaBuilder.like(expression, value));
		}

		Predicate predicate = criteriaBuilder.or(predicates.toArray(new Predicate[0]));
		criteriaQuery.where(predicate);

		if (orderBy != null && !orderBy.isEmpty()) {
			if (orderBy.charAt(0) == '-') {
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderBy.substring(1))));
			} else {
				criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderBy)));
			}
		}

		TypedQuery<T> query = this.em.createQuery(criteriaQuery);

		return query;
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
