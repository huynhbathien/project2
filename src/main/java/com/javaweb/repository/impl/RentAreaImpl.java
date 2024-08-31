package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.utils.ConnectionJDBCUtil;

@Repository
public class RentAreaImpl implements RentAreaRepository {

	@Override
	public List<RentAreaEntity> findValueByName(Long id) {
		
		String sql="select * from rentarea re where re.buildingid=	"+id;
		List<RentAreaEntity> listRentAreaEntities=new ArrayList<RentAreaEntity>();
		try (Connection conn = ConnectionJDBCUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());) {
			while (rs.next()) {
				RentAreaEntity areaEntity=new RentAreaEntity();
				areaEntity.setId(rs.getLong("id"));
				areaEntity.setValue(rs.getString("value"));
				areaEntity.setBuildingId(rs.getLong("buildingid"));
				listRentAreaEntities.add(areaEntity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to fetch buildings");
		}
		return listRentAreaEntities;
		}

	
}
