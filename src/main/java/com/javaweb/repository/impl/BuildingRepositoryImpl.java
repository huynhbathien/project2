package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
@PropertySource("classpath:application.properties")

public class BuildingRepositoryImpl implements BuildingRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Value("${spring.datasource.url}")
	private String DB_URL ;
	@Value("${spring.datasource.username}")
	private String USER ;
	@Value("${spring.datasource.password}")
	private String PASS ;
	
	
	public static void joinTable(BuildingSearchBuilder builder, StringBuilder sql) {
		Long staffID = builder.getStaffId();
		if (staffID!=null) {
			sql.append(" INNER JOIN assignmentbuilding b ON a.id = b.buildingid ");
		}
		List<String> typeCode=builder.getTypeCode();
		if (typeCode != null && !typeCode.isEmpty()) {
			sql.append(" INNER JOIN buildingrenttype d ON a.id = d.buildingid ");
			sql.append(" INNER JOIN renttype e ON d.renttypeid = e.id ");
		}
	}

	public static void queryNomal(BuildingSearchBuilder builder, StringBuilder where) {

		try {
			Field[] fields=builder.getClass().getFields();
			for(Field item: fields) {
				item.setAccessible(true);
				String fieldName=item.getName();
				if(fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
						&& !fieldName.startsWith("rentPrice")) {
					Object  fieldValue=item.get(builder).toString();
					if (item!=null) {
						if (item.getType().getName().equals("java.lang.Long")|| item.getType().getName().equals("java.lang.Integer")) {
							where.append(" AND a."+fieldName+"="+fieldValue+" ");
						} else {
							where.append(" AND a."+fieldName+" LIKE '%"+fieldValue+"%' ");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
}
		
	}

	public static void querySpecial(BuildingSearchBuilder builder,StringBuilder where) {
		Long staffID = builder.getStaffId();
		if (staffID!=null) {
			where.append(" AND b.staffid =").append(staffID);
		}

		Long rentAreaFrom =builder.getAreaFrom();
		Long rentAreaTo = builder.getAreaTo();
		if (rentAreaTo != null || rentAreaFrom != null) {
		    where.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE ra.buildingid = a.id ");
		    if (rentAreaFrom != null) {
		        where.append(" AND ra.value >= ").append(rentAreaFrom).append(" ");
		    }
		    if (rentAreaTo != null) {
		        where.append(" AND ra.value <= ").append(rentAreaTo).append(" ");
		    }
		    where.append(") ");
		}


		Long rentPriceFrom = builder.getRentPriceFrom();
		Long rentPriceTo =builder.getRentPriceTo();
		if (rentPriceFrom!=null) {
			where.append(" AND a.rentprice >=").append(rentPriceFrom).append(" ");
		}
		if (rentPriceTo!=null) {
			where.append(" AND a.rentprice <=").append(rentPriceTo).append(" ");
		}
		List<String> typecode=builder.getTypeCode();
		if (typecode != null && !typecode.isEmpty()) {
			where.append(" AND ( ");
			String sql=typecode.stream().map(it ->"e.code like '%"+it+"%' ").collect(Collectors.joining(" OR "));
			where.append(sql);
			where.append(" ) ");
		}
	}

	@Override
	public List<BuildingEntity> finAll(BuildingSearchBuilder builder) {
		StringBuilder sql = new StringBuilder("SELECT a.id, a.name, a.districtid, a.street, a.ward, ")
				.append("a.structure, a.numberofbasement, a.floorarea, a.direction, a.level, a.rentprice, ")
				.append("a.managername, a.managerphonenumber, a.servicefee, a.brokeragefee ")
				.append("FROM building a ");

		joinTable(builder, sql);
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		queryNomal(builder, where);
		querySpecial(builder, where);
		where.append(" GROUP BY a.id");

		sql.append(where);

		Query query=entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();
	}
}
