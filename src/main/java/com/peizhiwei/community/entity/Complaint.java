package com.peizhiwei.community.entity;

import java.util.Date;

public class Complaint {
	private Integer complaintId;
	private String complaintReason;//投诉原因\投诉内容
	private Date complaintTime;//投诉时间
	private Date complaintSettleTime;//投诉解决时间
	private int complaintState;//投诉状态，0未受理，1未解决(已受理)，2已解决
	private HouseOwner houseOwner;//投诉人信息
	private Admin admin;//投诉受理人
	public Integer getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}
	public String getComplaintReason() {
		return complaintReason;
	}
	public void setComplaintReason(String complaintReason) {
		this.complaintReason = complaintReason;
	}
	public Date getComplaintTime() {
		return complaintTime;
	}
	public void setComplaintTime(Date complaintTime) {
		this.complaintTime = complaintTime;
	}
	public Date getComplaintSettleTime() {
		return complaintSettleTime;
	}
	public void setComplaintSettleTime(Date complaintSettleTime) {
		this.complaintSettleTime = complaintSettleTime;
	}
	public int getComplaintState() {
		return complaintState;
	}
	public void setComplaintState(int complaintState) {
		this.complaintState = complaintState;
	}
	public HouseOwner getHouseOwner() {
		return houseOwner;
	}
	public void setHouseOwner(HouseOwner houseOwner) {
		this.houseOwner = houseOwner;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}
