package com.peizhiwei.community.admin.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Admin {
	private Integer adminId;
	private String adminName;
	private Integer adminSex;
	private String adminNumber;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date adminEntryTime;
	private String adminPhone;
	private String password;
	private Integer adminMan;
	private String adminNativePlace;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date adminBirthday;
	private String adminIdCard;
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Integer getAdminSex() {
		return adminSex;
	}
	public void setAdminSex(Integer adminSex) {
		this.adminSex = adminSex;
	}
	public String getAdminNumber() {
		return adminNumber;
	}
	public void setAdminNumber(String adminNumber) {
		this.adminNumber = adminNumber;
	}
	public Date getAdminEntryTime() {
		return adminEntryTime;
	}
	public void setAdminEntryTime(Date adminEntryTime) {
		this.adminEntryTime = adminEntryTime;
	}
	public String getAdminPhone() {
		return adminPhone;
	}
	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAdminMan() {
		return adminMan;
	}
	public void setAdminMan(Integer adminMan) {
		this.adminMan = adminMan;
	}
	public String getAdminNativePlace() {
		return adminNativePlace;
	}
	public void setAdminNativePlace(String adminNativePlace) {
		this.adminNativePlace = adminNativePlace;
	}
	public Date getAdminBirthday() {
		return adminBirthday;
	}
	public void setAdminBirthday(Date adminBirthday) {
		this.adminBirthday = adminBirthday;
	}
	public String getAdminIdCard() {
		return adminIdCard;
	}
	public void setAdminIdCard(String adminIdCard) {
		this.adminIdCard = adminIdCard;
	}
	
	
}
