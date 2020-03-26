package com.peizhiwei.community.admin.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PayInfo {
	private Integer payInfoId;//id
	private PayType payType;//������ɷ���Ŀ
	private BigDecimal payInfoMoney;//�ɷѽ��
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date payInfoEndTime;//�ɷѽ�ֹʱ��
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date payInfoStartTime;//�ɷѷ���ʱ��
	public Date getPayInfoStartTime() {
		return payInfoStartTime;
	}
	public void setPayInfoStartTime(Date payInfoStartTime) {
		this.payInfoStartTime = payInfoStartTime;
	}
	public Integer getPayInfoId() {
		return payInfoId;
	}
	public void setPayInfoId(Integer payInfoId) {
		this.payInfoId = payInfoId;
	}
	
	public PayType getPayType() {
		return payType;
	}
	public void setPayType(PayType payType) {
		this.payType = payType;
	}
	public BigDecimal getPayInfoMoney() {
		return payInfoMoney;
	}
	public void setPayInfoMoney(BigDecimal payInfoMoney) {
		this.payInfoMoney = payInfoMoney;
	}
	public Date getPayInfoEndTime() {
		return payInfoEndTime;
	}
	public void setPayInfoEndTime(Date payInfoEndTime) {
		this.payInfoEndTime = payInfoEndTime;
	}
	
}
