package com.everis.control;


import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.everis.entity.Car;
import com.everis.entity.CarDto;
import com.everis.utils.PagesPresentation;

@Stateless
public class PersistenceService<T, L> {

	@PersistenceContext(unitName = "car-unit")
	private EntityManager em;
	
	
	
	public PagesPresentation<CarDto> findCars(int size, int page, String sort, String orderBy, String filterBy) {
		
		if(sort == null || sort.trim().isEmpty() || !sort.equalsIgnoreCase("desc")) sort="asc";	
		if(orderBy == null || orderBy.trim().isEmpty()) orderBy="id";
		if(page <= 0) page=1;
		if(size <= 0 || size >= 20) size=10;
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);

		Root<Car> root = criteriaQuery.from(Car.class);
		
        countQuery.select(criteriaBuilder.count(countQuery.from(Car.class)));
        
		if (filterBy != null) {
			Predicate brandPredict = criteriaBuilder.like(root.get("brand"), "%" + filterBy + "%");
			Predicate countryPredict = criteriaBuilder.like(root.get("country"), "%" + filterBy + "%");
			Predicate mergePredicates = criteriaBuilder.or(brandPredict, countryPredict);
			criteriaQuery.where(mergePredicates).distinct(true);
			countQuery.where(mergePredicates).distinct(true);
		}

		if(sort == "asc")
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderBy)));
		else
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderBy)));

		TypedQuery<Car> createQuery = em.createQuery(criteriaQuery);

		// count total elements
		Long total = em.createQuery(countQuery).getSingleResult();

		createQuery.setMaxResults(size);
		createQuery.setFirstResult(page * size);

		List<Car> resultList = createQuery.getResultList();
		List<CarDto> data = resultList.stream().map(c -> c.mapToDto()).collect(Collectors.toList());
		PagesPresentation<CarDto> pagesPresentation = new PagesPresentation<CarDto>(data,total.intValue(), size, page,
			 sort, orderBy);
		return pagesPresentation;
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
