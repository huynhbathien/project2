package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class BuildingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "ward")
	private String ward;

	@Column(name = "street")
	private String street;

	@Column(name = "managername")
	private String managerName;

	@Column(name = "managerphonenumber")
	private String managerPhoneNumber;

	@Column(name = "floorarea")
	private long floorArea;

	/*
	 * @Column(name = "emtyarea") private String emtyArea;
	 */

	@Column(name = "rentprice")
	private long rentPrice;

	@Column(name = "servicefee")
	private String serviceFee;

	@Column(name = "brokeragefee")
	private Long brokerageFee;
	
	@OneToMany(mappedBy = "building",fetch = FetchType.LAZY)
	private List<RentAreaEntity> rentArea  =new ArrayList<>();  
	
	
	//fetch thuộc tính của các anotation lấy dữ liệu các ano có đuôi many nên dùng lazy và đuôi one là eager
	//fetch mặc định là lazy: là lấy tất cả các  dữ liệu có liên quan đến bảng district 
	@ManyToOne
	@JoinColumn(name="districtid")
	private DistrictEntity district;
	

	public List<RentAreaEntity> getRentArea() {
		return rentArea;
	}

	public void setRentArea(List<RentAreaEntity> rentArea) {
		this.rentArea = rentArea;
	}

	public DistrictEntity getDictrict() {
		return district;
	}

	public void setDictrict(DistrictEntity district) {
		this.district = district;
	}

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

	/*
	 * public String getEmtyArea() { return emtyArea; }
	 * 
	 * public void setEmtyArea(String emtyArea) { this.emtyArea = emtyArea; }
	 */

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

	public Long getBrokerageFee() {
		return brokerageFee;
	}

	public void setBrokerageFee(Long brokerageFee) {
		this.brokerageFee = brokerageFee;
	}

}
