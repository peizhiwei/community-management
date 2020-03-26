package com.peizhiwei.community.admin.entity;

public class PayMethod {
	private Integer methodId;//id
	private String methodName;//支付方法名
	private String methodRemarks;//备注
	public Integer getMethodId() {
		return methodId;
	}
	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getMethodRemarks() {
		return methodRemarks;
	}
	public void setMethodRemarks(String methodRemarks) {
		this.methodRemarks = methodRemarks;
	}
}
