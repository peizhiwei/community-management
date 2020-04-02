package com.peizhiwei.community.admin.entity;
/**
 * 缴费信息汇总
 * @author PEIZHIWEI
 *
 */

import java.math.BigDecimal;

public class PayInfoSum {
	private BigDecimal payInfoSumId;//id
	private BigDecimal payInfoSumPayable;//应缴金额
	private BigDecimal payInfoSumPaid;//实缴金额
	private HouseOwner houseOwner;//业主
	public BigDecimal getPayInfoSumId() {
		return payInfoSumId;
	}
	public void setPayInfoSumId(BigDecimal payInfoSumId) {
		this.payInfoSumId = payInfoSumId;
	}
	public BigDecimal getPayInfoSumPayable() {
		return payInfoSumPayable;
	}
	public void setPayInfoSumPayable(BigDecimal payInfoSumPayable) {
		this.payInfoSumPayable = payInfoSumPayable;
	}
	public BigDecimal getPayInfoSumPaid() {
		return payInfoSumPaid;
	}
	public void setPayInfoSumPaid(BigDecimal payInfoSumPaid) {
		this.payInfoSumPaid = payInfoSumPaid;
	}
	public HouseOwner getHouseOwner() {
		return houseOwner;
	}
	public void setHouseOwner(HouseOwner houseOwner) {
		this.houseOwner = houseOwner;
	}
}
