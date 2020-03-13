package com.peizhiwei.community.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HouseInfo {
	private Integer houseId;//id
	private String houseNumber;//房间号
	private BigDecimal houseArea;//房间面积
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date houseInTime;//入住时间，可以为空
	private BuildingInfo buildInfo;
	private HouseOwner houseOwner;
	private HouseType houseType;
	private HouseUse houseUse;
	private int houseState;//状态0表示待售，1表示有人居住
	public int getHouseState() {
		return houseState;
	}
	public void setHouseState(int houseState) {
		this.houseState = houseState;
	}
	public Integer getHouseId() {
		return houseId;
	}
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public BigDecimal getHouseArea() {
		return houseArea;
	}
	public void setHouseArea(BigDecimal houseArea) {
		this.houseArea = houseArea;
	}
	public Date getHouseInTime() {
		return houseInTime;
	}
	public void setHouseInTime(Date houseInTime) {
		this.houseInTime = houseInTime;
	}
	public BuildingInfo getBuildInfo() {
		return buildInfo;
	}
	public void setBuildInfo(BuildingInfo buildInfo) {
		this.buildInfo = buildInfo;
	}
	public HouseOwner getHouseOwner() {
		return houseOwner;
	}
	public void setHouseOwner(HouseOwner houseOwner) {
		this.houseOwner = houseOwner;
	}
	public HouseType getHouseType() {
		return houseType;
	}
	public void setHouseType(HouseType houseType) {
		this.houseType = houseType;
	}
	public HouseUse getHouseUse() {
		return houseUse;
	}
	public void setHouseUse(HouseUse houseUse) {
		this.houseUse = houseUse;
	}
	
}
