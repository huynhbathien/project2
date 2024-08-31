package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.convert.BuildingDTOConvert;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private BuildingDTOConvert buildingDTOConvert;

	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typecode) {

		List<BuildingEntity> listBuildingEntity = buildingRepository.finAll(params, typecode);
		List<BuildingDTO> listBuildingDTO = new ArrayList<BuildingDTO>();
		for (BuildingEntity item : listBuildingEntity) {
			BuildingDTO dto = buildingDTOConvert.convertToBuildingDto(item);
			listBuildingDTO.add(dto);
		}
		return listBuildingDTO;
	}

}
