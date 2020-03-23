package com.peizhiwei.community.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Repair {
	private Integer repairId;//id
	private String repairGoods;//������Ʒ
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date repairTime;//����ʱ��
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date repairSettleTime;//������ʱ��
	private String repairReason;//��Ʒ��ԭ��
	private int repairState;//������״̬��0δ����1������(δ���),2�ѽ��
	private HouseOwner houseOwner;//������
	private Admin admin;//������
	public Integer getRepairId() {
		return repairId;
	}
	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}
	public String getRepairGoods() {
		return repairGoods;
	}
	public void setRepairGoods(String repairGoods) {
		this.repairGoods = repairGoods;
	}
	public Date getRepairTime() {
		return repairTime;
	}
	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}
	public Date getRepairSettleTime() {
		return repairSettleTime;
	}
	public void setRepairSettleTime(Date repairSettleTime) {
		this.repairSettleTime = repairSettleTime;
	}
	public String getRepairReason() {
		return repairReason;
	}
	public void setRepairReason(String repairReason) {
		this.repairReason = repairReason;
	}
	public int getRepairState() {
		return repairState;
	}
	public void setRepairState(int repairState) {
		this.repairState = repairState;
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
