package com.peizhiwei.community.admin.entity;

public class PayType {
	private Integer PayTypeId;//id
	private String PayTypeName;//�շ�����
	private String payTypeRemarks;//��ע
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
	public String getPayTypeRemarks() {
		return payTypeRemarks;
	}
	public void setPayTypeRemarks(String payTypeRemarks) {
		this.payTypeRemarks = payTypeRemarks;
	}
}
