package com.peizhiwei.community.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HouseOwner {
	private Integer ownerId;//ҵ��id
	private String ownerPhone;//�绰��Ҳ���˺�
	private String ownerPassword;//����
	private String ownerName;//ҵ������
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date ownerbirthday;//ҵ����������
	private String ownerIdCard;//ҵ�����֤����
	private String ownerWorkPlace;//�����ص�
	private String ownerNativePlace;//����
	private int ownerSex;//�Ա�
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
	public Date getOwnerbirthday() {
		return ownerbirthday;
	}
	public void setOwnerbirthday(Date ownerbirthday) {
		this.ownerbirthday = ownerbirthday;
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
