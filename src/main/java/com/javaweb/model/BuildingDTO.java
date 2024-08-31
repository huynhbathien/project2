package com.javaweb.model;

public class BuildingDTO {
	private String name;
	private String address;
	private String managerName;
	private String managerPhoneNumber;
	private Integer floorArea;
	private String rentArea;
	private Integer emptyArea;
	private Integer rentPrice;
	private Integer serviceFee;
	private Double brokerageFee;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public Integer getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}
	public Integer getEmptyArea() {
		return emptyArea;
	}
	public void setEmptyArea(Integer emptyArea) {
		this.emptyArea = emptyArea;
	}
	public Integer getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Integer rentPrice) {
		this.rentPrice = rentPrice;
	}
	public Integer getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(Integer serviceFee) {
		this.serviceFee = serviceFee;
	}
	public Double getBrokerageFee() {
		return brokerageFee;
	}
	public void setBrokerageFee(Double brokerageFee) {
		this.brokerageFee = brokerageFee;
	}
	public String getRentArea() {
		return rentArea;
	}
	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}

}
