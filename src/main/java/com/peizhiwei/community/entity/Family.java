package com.peizhiwei.community.entity;

public class Family {
	private Integer familyId;//家庭成员id
	private String familyName;//家庭成员姓名
	private int familySex;//家庭成员性别
	private String familyNativePlace;//籍贯
	private String familyPhone;//电话，也是登录账号，可以为空
	private String familyWorkPlace;//工作地点，可以为空
	private String familyRelation;//与业主的关系
	private HouseOwner houseOwner;//外键，业主
	public Integer getFamilyId() {
		return familyId;
	}
	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public int getFamilySex() {
		return familySex;
	}
	public void setFamilySex(int familySex) {
		this.familySex = familySex;
	}
	public String getFamilyNativePlace() {
		return familyNativePlace;
	}
	public void setFamilyNativePlace(String familyNativePlace) {
		this.familyNativePlace = familyNativePlace;
	}
	public String getFamilyPhone() {
		return familyPhone;
	}
	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}
	public String getFamilyWorkPlace() {
		return familyWorkPlace;
	}
	public void setFamilyWorkPlace(String familyWorkPlace) {
		this.familyWorkPlace = familyWorkPlace;
	}
	public String getFamilyRelation() {
		return familyRelation;
	}
	public void setFamilyRelation(String familyRelation) {
		this.familyRelation = familyRelation;
	}
	public HouseOwner getHouseOwner() {
		return houseOwner;
	}
	public void setHouseOwner(HouseOwner houseOwner) {
		this.houseOwner = houseOwner;
	}
}
