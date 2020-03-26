package com.peizhiwei.community.admin.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HouseOwner {
	private Integer ownerId;//业主id
	private String ownerPhone;//电话，也是账号
	private String ownerPassword;//密码
	private String ownerName;//业主姓名
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date ownerBirthday;//业主出生日期
	private String ownerIdCard;//业主身份证号码
	private String ownerWorkPlace;//工作地点
	private String ownerNativePlace;//籍贯
	private int ownerSex;//性别
	private HouseInfo houseInfo;
	private BuildingInfo buildingInfo;
	public Date getOwnerBirthday() {
		return ownerBirthday;
	}
	public void setOwnerBirthday(Date ownerBirthday) {
		this.ownerBirthday = ownerBirthday;
	}
	public BuildingInfo getBuildingInfo() {
		return buildingInfo;
	}
	public void setBuildingInfo(BuildingInfo buildingInfo) {
		this.buildingInfo = buildingInfo;
	}
	public HouseInfo getHouseInfo() {
		return houseInfo;
	}
	public void setHouseInfo(HouseInfo houseInfo) {
		this.houseInfo = houseInfo;
	}
	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerPhone() {
		return ownerPhone;
	}
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
	public String getOwnerPassword() {
		return ownerPassword;
	}
	public void setOwnerPassword(String ownerPassword) {
		this.ownerPassword = ownerPassword;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerIdCard() {
		return ownerIdCard;
	}
	public void setOwnerIdCard(String ownerIdCard) {
		this.ownerIdCard = ownerIdCard;
	}
	public String getOwnerWorkPlace() {
		return ownerWorkPlace;
	}
	public void setOwnerWorkPlace(String ownerWorkPlace) {
		this.ownerWorkPlace = ownerWorkPlace;
	}
	public String getOwnerNativePlace() {
		return ownerNativePlace;
	}
	public void setOwnerNativePlace(String ownerNativePlace) {
		this.ownerNativePlace = ownerNativePlace;
	}
	public int getOwnerSex() {
		return ownerSex;
	}
	public void setOwnerSex(int ownerSex) {
		this.ownerSex = ownerSex;
	}
}
