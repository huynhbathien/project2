package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {


	public static void joinTable(Map<String, Object> params, List<String> typecode, StringBuilder sql) {
		String staffID = (String) params.get("staffId");
		if (StringUtil.checkString(staffID)) {
			sql.append(" INNER JOIN assignmentbuilding b ON a.id = b.buildingid ");
		}
		if (typecode != null && !typecode.isEmpty()) {
			sql.append(" INNER JOIN buildingrenttype d ON a.id = d.buildingid ");
			sql.append(" INNER JOIN renttype e ON d.renttypeid = e.id ");
		}
		/*
		 * String rentAreaFrom = (String) params.get("areaFrom"); String rentAreaTo =
		 * (String) params.get("areaTo"); if (StringUtil.checkString(rentAreaFrom) ||
		 * StringUtil.checkString(rentAreaTo)) {
		 * sql.append(" INNER JOIN rentarea ra ON a.id = ra.buildingid "); }
		 */
	}

	public static void queryNomal(Map<String, Object> params, StringBuilder where) {
		for (Map.Entry<String, Object> item : params.entrySet()) {
			String key = item.getKey();
			String value = item.getValue().toString();

			if (!key.equals("staffId") && !key.equals("typeCode") && !key.startsWith("area")
					&& !key.startsWith("rentPrice")) {
				if (StringUtil.checkString(value)) {
					if (NumberUtil.checkNumber(value)) {
						where.append(" AND a.").append(key).append(" = ").append(value).append(" ");
					} else {
						where.append(" AND a.").append(key).append(" LIKE '%").append(value).append("%' ");
					}
				}
			}
		}
	}

	public static void querySpecial(Map<String, Object> params, List<String> typecode, StringBuilder where) {
		String staffID = (String) params.get("staffId");
		if (StringUtil.checkString(staffID)) {
			where.append(" AND b.staffid =").append(staffID);
		}

		String rentAreaFrom = (String) params.get("areaFrom");
		String rentAreaTo = (String) params.get("areaTo");
		if(StringUtil.checkString(rentAreaTo)==true || StringUtil.checkString(rentAreaFrom)==true)
		{
			where.append(" AND  EXITS (Select * from rentarea ra where ra.buildingid=a.id)");
			if (StringUtil.checkString(rentAreaFrom)) {
				where.append(" AND ra.value >=").append(rentAreaFrom).append(" ");
			}
			if (StringUtil.checkString(rentAreaTo)) {
				where.append(" AND ra.value <=").append(rentAreaTo).append(" ");
			}
			where.append(" ) ");
		}

		String rentPriceFrom = (String) params.get("rentPriceFrom");
		String rentPriceTo = (String) params.get("rentPriceTo");
		if (StringUtil.checkString(rentPriceFrom)) {
			where.append(" AND a.rentprice >=").append(rentPriceFrom).append(" ");
		}
		if (StringUtil.checkString(rentPriceTo)) {
			where.append(" AND a.rentprice <=").append(rentPriceTo).append(" ");
		}
		//java 7
		/*
		 * if (typecode != null && !typecode.isEmpty()) { List<String> code = new
		 * ArrayList<>(); for (String item : typecode) { code.add("'" + item + "'"); }
		 * where.append(" AND e.code IN(").append(String.join(",", code)).append(") ");
		 * }
		 */
		//java 8
		if (typecode != null && !typecode.isEmpty()) {
			where.append(" AND ( ");
			String sql=typecode.stream().map(it ->"e.code like '%"+it+"%' ").collect(Collectors.joining(" OR "));
			where.append(sql);
			where.append(" ) ");
		}
	}

	@Override
	public List<BuildingEntity> finAll(Map<String, Object> params, List<String> typecode) {
		StringBuilder sql = new StringBuilder("SELECT a.id, a.name, a.districtid, a.street, a.ward, ")
				.append("a.structure, a.numberofbasement, a.floorarea, a.direction, a.level, a.rentprice, ")
				.append("a.managername, a.managerphonenumber, a.servicefee, a.brokeragefee ")
				.append("FROM building a ");

		joinTable(params, typecode, sql);

		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		queryNomal(params, where);
		querySpecial(params, typecode, where);
		where.append(" GROUP BY a.id");

		sql.append(where);

		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = ConnectionJDBCUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString())) {

			while (rs.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setId(rs.getLong("id"));
				buildingEntity.setName(rs.getString("name"));
				buildingEntity.setWard(rs.getString("ward"));
				buildingEntity.setDistrictid(rs.getLong("districtid"));
				buildingEntity.setStreet(rs.getString("street"));
				buildingEntity.setFloorArea(rs.getLong("floorarea"));
				buildingEntity.setRentPrice(rs.getLong("rentprice"));
				buildingEntity.setServiceFee(rs.getString("servicefee"));
				buildingEntity.setBrokerageFee(rs.getLong("brokeragefee"));
				buildingEntity.setManagerName(rs.getString("managername"));
				buildingEntity.setManagerPhoneNumber(rs.getString("managerphonenumber"));
				result.add(buildingEntity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to fetch buildings");
		}
		return result;
	}
}
