package com.peizhiwei.community.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PayInfo {
	private Integer payId;//id
	private double payMoney;//�ɷѽ��
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date payTime;//�ɷ�ʱ�䣬����Ϊ��
	private int payState;//�ɷ�״̬��1�ѽɷѣ�0δ�ɷ�
	private PayType payType;
	private HouseOwner houseOwner;
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
