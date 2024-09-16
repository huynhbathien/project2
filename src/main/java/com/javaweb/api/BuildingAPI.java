package com.javaweb.api;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@RestController
public class BuildingAPI {

	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private BuildingService service;

	@GetMapping(value = "/api/building/")
	public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params,
			@RequestParam(value = "typeCode", required = false) List<String> typecode) {
		List<BuildingDTO> result = service.findAll(params, typecode);
		return result;
	}

	/*
	 * @GetMapping(value = "/api/building/{name}") public BuildingDTO
	 * getByNameBuilding(@PathVariable String name) { BuildingDTO bukBuildingDTO=new
	 * BuildingDTO(); List<BuildingEntity>
	 * result=buildingRepository.findByNameContaining(name); return bukBuildingDTO;
	 * }
	 * 
	 * @Autowired ModelMapper mapper;
	 * 
	 * @PutMapping public void UpdateBuilding(@RequestBody BuildingRequestDTO
	 * buildingRequestDTO) { // BuildingEntity
	 * buildingEntity=mapper.map(buildingRequestDTO, BuildingEntity.class); //
	 * buildingRepository.findById(buildingEntity.getId()).get(); BuildingEntity
	 * buildingEntity=buildingRepository.findById(buildingRequestDTO.getId()).get();
	 * buildingEntity.setName(buildingRequestDTO.getName());
	 * buildingEntity.setStreet(buildingRequestDTO.getStreet());
	 * buildingEntity.setWard(buildingRequestDTO.getWard());
	 * 
	 * buildingEntity.setDictrict(null); }
	 */


	@Transactional
	@DeleteMapping(value = "/api/building/{ids}")
	public void DLBuilding(@PathVariable Long[] ids) {
		buildingRepository.deleteByIdIn(ids);
	}

}