package com.peizhiwei.community.admin.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Family {
	private Integer familyId;//��ͥ��Աid
	private String familyName;//��ͥ��Ա����
	private int familySex;//��ͥ��Ա�Ա�
	private String familyNativePlace;//����
	private String familyPhone;//�绰��Ҳ�ǵ�¼�˺ţ�����Ϊ��
	private String familyWorkPlace;//�����ص㣬����Ϊ��
	private String familyRelation;//��ҵ���Ĺ�ϵ
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date familyBirthday;//��������
	private HouseOwner houseOwner;//�����ҵ��
	public Date getFamilyBirthday() {
		return familyBirthday;
	}
	public void setFamilyBirthday(Date familyBirthday) {
		this.familyBirthday = familyBirthday;
	}
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
