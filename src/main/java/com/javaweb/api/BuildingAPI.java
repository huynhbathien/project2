package com.javaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;

@RestController
public class BuildingAPI {

	@Autowired
	private BuildingService service;

	@GetMapping(value = "/api/building/")
	public List<BuildingDTO> getBuilding(@RequestParam(value="name",required = false)String name,
										@RequestParam(value="districtid",required = false)Long district) {
		List<BuildingDTO> result=service.finBuilding(name,district);
		return result;
	}

	/*
	 * @GetMapping(value = "/api/building/") public List<BuildingDTO>
	 * getBuilding(@RequestParam Map<String, String> params) {
	 * 
	 * BuildingDTO building = new BuildingDTO();
	 * building.setName(params.get("name"));
	 * building.setStreet(params.get("street")); building.setWar(params.get("war"));
	 * String basementStr = params.get("numberOfBasement"); if (basementStr != null)
	 * { try { building.setNumberOfBasement(Integer.parseInt(basementStr)); } catch
	 * (Exception e) { building.setNumberOfBasement(null); } } return building;
	 * 
	 * List<BuildingDTO> listBD=new ArrayList<BuildingDTO>(); BuildingDTO dto1=new
	 * BuildingDTO(); dto1.setName("ABC"); dto1.setNumberOfBasement(4);
	 * dto1.setStreet("My An"); dto1.setWar("Đà Nẵng"); listBD.add(dto1);
	 * BuildingDTO dto2=new BuildingDTO(); dto2.setName("DEF");
	 * dto2.setNumberOfBasement(4); dto2.setStreet("An Thuong");
	 * dto2.setWar("Đà Nẵng"); listBD.add(dto2); return listBD; }
	 */
	/*
	 * @PostMapping(value = "/api/building/") public BuildingDTO
	 * postBuilding(@RequestBody BuildingDTO buildingDTO) { return buildingDTO; }
	 */
	/*
	 * @PostMapping(value = "/api/building/") public Object
	 * CreateBuilding(@RequestBody BuildingDTO building) { valiDate(building);
	 * return building; } public void valiDate(BuildingDTO buildingDTO) {
	 * if(buildingDTO.getName()==null || buildingDTO.getName()=="" ||
	 * buildingDTO.getNumberOfBasement()==null) { throw new
	 * FieldRequiredException("name or number is null"); } }
	 */
	@DeleteMapping(value = "/api/building/{id}")
	public void DLBuilding(@PathVariable Integer id) {
		System.out.println("Da Xoa Toa Nhan Co ID=" + id);
	}

}