package com.javaweb.repository.entity;

public class BuildingEntity {
	private long id;
	private String name;
	private String ward;
	private String street;
	private long districtid;
	private String managerName;
	private String managerPhoneNumber;
	private long floorArea;
	private String emtyArea;
	private long rentPrice;
	private String serviceFee;
	private long brokerageFee;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public long getDistrictid() {
		return districtid;
	}
	public void setDistrictid(long districtid) {
		this.districtid = districtid;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}
	public long getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(long floorArea) {
		this.floorArea = floorArea;
	}
	public String getEmtyArea() {
		return emtyArea;
	}
	public void setEmtyArea(String emtyArea) {
		this.emtyArea = emtyArea;
	}
	public long getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(long rentPrice) {
		this.rentPrice = rentPrice;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	public long getBrokerageFee() {
		return brokerageFee;
	}
	public void setBrokerageFee(long brokerageFee) {
		this.brokerageFee = brokerageFee;
	}
	
	
}

