package com.peizhiwei.community.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PayInfoDetails {
	private Integer payId;//id
	private double payMoney;//缴费金额
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date payStartTime;//缴费信息发布时间
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date payEndTime;//缴费截止时间
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date payTime;//实际缴费时间，可以为空
	private int payState;//缴费状态，1已缴费，0未缴费
	private PayMethod payMethod;//缴费方式
	private PayType payType;//缴费项目
	private HouseOwner houseOwner;//业主
	public Integer getPayId() {
		return payId;
	}
	public void setPayId(Integer payId) {
		this.payId = payId;
	}
	public double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}
	public Date getPayStartTime() {
		return payStartTime;
	}
	public void setPayStartTime(Date payStartTime) {
		this.payStartTime = payStartTime;
	}
	public Date getPayEndTime() {
		return payEndTime;
	}
	public void setPayEndTime(Date payEndTime) {
		this.payEndTime = payEndTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public int getPayState() {
		return payState;
	}
	public void setPayState(int payState) {
		this.payState = payState;
	}
	public PayMethod getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(PayMethod payMethod) {
		this.payMethod = payMethod;
	}
	public PayType getPayType() {
		return payType;
	}
	public void setPayType(PayType payType) {
		this.payType = payType;
	}
	public HouseOwner getHouseOwner() {
		return houseOwner;
	}
	public void setHouseOwner(HouseOwner houseOwner) {
		this.houseOwner = houseOwner;
	}
}
