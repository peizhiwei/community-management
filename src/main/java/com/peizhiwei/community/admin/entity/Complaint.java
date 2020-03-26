package com.peizhiwei.community.admin.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Complaint {
	private Integer complaintId;
	private String complaintReason;//Ͷ��ԭ��\Ͷ������
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date complaintTime;//Ͷ��ʱ��
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date complaintSettleTime;//Ͷ�߽��ʱ��
	private int complaintState;//Ͷ��״̬��0δ����1δ���(������)��2�ѽ��
	private HouseOwner houseOwner;//Ͷ������Ϣ
	private Admin admin;//Ͷ��������
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
