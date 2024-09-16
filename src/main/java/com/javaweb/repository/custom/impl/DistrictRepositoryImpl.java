package com.javaweb.repository.custom.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.ConnectionJDBCUtil;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

	@Override
	public DistrictEntity findNameById(Long id) {
		String sql = "select d.name from district d where d.id=" + id + ";";
		DistrictEntity districtEntity = new DistrictEntity();
		try (Connection conn = ConnectionJDBCUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());) {
			while (rs.next()) {
				districtEntity.setName(rs.getString("name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to fetch buildings");
		}
		return districtEntity;
	}

}
