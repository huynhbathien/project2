package com.javaweb.convert;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component 
public class BuildingDTOConvert {

	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	public BuildingDTO convertToBuildingDto(BuildingEntity item) {
		BuildingDTO dto = mapper.map(item,BuildingDTO.class);
		DistrictEntity districtEntity=districtRepository.findNameById(item.getDistrictid()); 
		dto.setAddress(item.getStreet() + "," + item.getWard() + ","+districtEntity.getName());
		List<RentAreaEntity> listAreaEntities=rentAreaRepository.findValueByName(item.getId());
		//chuyen tu list sang String dung stream cua jv8
		String areaResult=listAreaEntities.stream().map(it ->it.getValue().toString()).collect(Collectors.joining(","));
		dto.setRentArea(areaResult);
		return dto;
	}
}
