package com.peizhiwei.community.admin.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PayInfoDetails {
	private Integer payId;//id
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date payTime;//ʵ�ʽɷ�ʱ�䣬����Ϊ��
	private int payState;//�ɷ�״̬��1�ѽɷѣ�0δ�ɷ�
	private PayInfo payInfo;//�ɷ���Ϣ
	private PayMethod payMethod;//�ɷѷ�ʽ
	private HouseOwner houseOwner;//ҵ��
	public PayInfo getPayInfo() {
		return payInfo;
	}
	public void setPayInfo(PayInfo payInfo) {
		this.payInfo = payInfo;
	}
	public Integer getPayId() {
		return payId;
	}
	public void setPayId(Integer payId) {
		this.payId = payId;
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
	public HouseOwner getHouseOwner() {
		return houseOwner;
	}
	public void setHouseOwner(HouseOwner houseOwner) {
		this.houseOwner = houseOwner;
	}
}
