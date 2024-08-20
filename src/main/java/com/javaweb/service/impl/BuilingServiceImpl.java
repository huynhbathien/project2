package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuilingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;
	
	@Override
	public List<BuildingDTO> finBuilding(String name,Long districtid) {
		List<BuildingEntity> lisstBuidingEntities=buildingRepository.findBuilding(name,districtid);
		List<BuildingDTO> result=new ArrayList<BuildingDTO>();
		for(BuildingEntity item: lisstBuidingEntities)
		{
			BuildingDTO buildingDTO=new BuildingDTO();
			buildingDTO.setName(item.getName());
			buildingDTO.setAddress( item.getStreet()+" "+item.getWard());
			buildingDTO.setNumberOfBasement(item.getNumberOfBasement());
			result.add(buildingDTO);
		}
		return result;
	}

}
