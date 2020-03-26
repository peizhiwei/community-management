package com.peizhiwei.community.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PayInfoDetails {
	private Integer payId;//id
	private double payMoney;//�ɷѽ��
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date payStartTime;//�ɷ���Ϣ����ʱ��
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date payEndTime;//�ɷѽ�ֹʱ��
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date payTime;//ʵ�ʽɷ�ʱ�䣬����Ϊ��
	private int payState;//�ɷ�״̬��1�ѽɷѣ�0δ�ɷ�
	private PayMethod payMethod;//�ɷѷ�ʽ
	private PayType payType;//�ɷ���Ŀ
	private HouseOwner houseOwner;//ҵ��
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
