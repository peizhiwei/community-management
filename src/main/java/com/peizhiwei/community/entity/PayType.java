package com.peizhiwei.community.entity;

public class PayType {
	private Integer PayTypeId;//id
	private String PayTypeName;//收费名称
	private double payTypePrice;//收费价格
	public Integer getPayTypeId() {
		return PayTypeId;
	}
	public void setPayTypeId(Integer payTypeId) {
		PayTypeId = payTypeId;
	}
	public String getPayTypeName() {
		return PayTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		PayTypeName = payTypeName;
	}
	public double getPayTypePrice() {
		return payTypePrice;
	}
	public void setPayTypePrice(double payTypePrice) {
		this.payTypePrice = payTypePrice;
	}
}
