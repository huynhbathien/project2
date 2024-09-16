package com.javaweb.convert;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params,List<String>typeCode) {
		BuildingSearchBuilder builder=new BuildingSearchBuilder.Builder()
							.setName(MapUtil.getBuild(params, "name", String.class))
							.setFloorArea(MapUtil.getBuild(params, "floorArea", Long.class))
							.setWard(MapUtil.getBuild(params, "ward", String.class))
							.setStreet(MapUtil.getBuild(params, "street", String.class))
							.setdistrictId(MapUtil.getBuild(params, "districtId", Long.class))
							.setNumberOfBasement(MapUtil.getBuild(params, "numberOfBasement", Integer.class))
							.setTypeCode(typeCode)
							.setManagerName(MapUtil.getBuild(params, "managerName", String.class))
							.setManagerPhoneNumber(MapUtil.getBuild(params, "managerPhoneNumber", String.class))
							.setRentPriceFrom(MapUtil.getBuild(params, "rentPriceFrom", Long.class))
							.setRentPriceTo(MapUtil.getBuild(params, "rentPriceTo", Long.class))
							.setAreaFrom(MapUtil.getBuild(params, "areaFrom", Long.class))
							.setAreaTo(MapUtil.getBuild(params, "areaTo", Long.class))
							.setStaffId(MapUtil.getBuild(params, "staffId", Long.class))
							.build();
	return builder;
	}
}
