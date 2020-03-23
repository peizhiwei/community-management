package com.peizhiwei.community.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Repair {
	private Integer repairId;//id
	private String repairGoods;//报修物品
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date repairTime;//报修时间
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date repairSettleTime;//问题解决时间
	private String repairReason;//物品损坏原因
	private int repairState;//问题解决状态，0未受理，1已受理(未解决),2已解决
	private HouseOwner houseOwner;//报修人
	private Admin admin;//受理人
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
