package com.javaweb.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
@Primary
public class JPABuildingRepository {

	@PersistenceContext
	private EntityManager entityManager;
	public List<BuildingEntity> finAll(BuildingSearchBuilder builder) {
		//JPQL
		/*
		 * String sql="FROM BuildingEntity"; Query
		 * query=entityManager.createQuery(sql,BuildingEntity.class); //sql Native
		 */		
		String sql="SELECT * FROM building";
		Query query=entityManager.createNativeQuery(sql, BuildingEntity.class);

		return query.getResultList();
	}
	
}
