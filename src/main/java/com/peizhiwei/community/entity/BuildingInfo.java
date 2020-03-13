package com.peizhiwei.community.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BuildingInfo {
	private Integer buildId;//id
	private String buildNumber;//楼房编号
	private int buildLayer;//楼房层数
	private int buildSumHouse;//房间总数
	private BigDecimal buildArea;//建筑面积
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date buildEndTime;//竣工时间
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date buildStartTime;//开工时间
	public Integer getBuildId() {
		return buildId;
	}
	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}
	public String getBuildNumber() {
		return buildNumber;
	}
	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}
	public int getBuildLayer() {
		return buildLayer;
	}
	public void setBuildLayer(int buildLayer) {
		this.buildLayer = buildLayer;
	}
	public BigDecimal getBuildArea() {
		return buildArea;
	}
	public void setBuildArea(BigDecimal buildArea) {
		this.buildArea = buildArea;
	}
	
	public int getBuildSumHouse() {
		return buildSumHouse;
	}
	public void setBuildSumHouse(int buildSumHouse) {
		this.buildSumHouse = buildSumHouse;
	}
	public Date getBuildEndTime() {
		return buildEndTime;
	}
	public void setBuildEndTime(Date buildEndTime) {
		this.buildEndTime = buildEndTime;
	}
	public Date getBuildStartTime() {
		return buildStartTime;
	}
	public void setBuildStartTime(Date buildStartTime) {
		this.buildStartTime = buildStartTime;
	}
	
}
