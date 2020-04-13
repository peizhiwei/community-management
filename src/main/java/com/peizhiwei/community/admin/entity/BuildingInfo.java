package com.peizhiwei.community.admin.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BuildingInfo {
	private Integer buildId;//id
	private String buildNumber;//¥�����
	private int buildUnitSum;//¥����Ԫ��
	private int buildLayer;//¥������
	private int buildUnitSingleLayerRooms;//��Ԫ���㷿����
	private int buildSumHouse;//��������
	private BigDecimal buildArea;//�������
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date buildEndTime;//����ʱ��
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date buildStartTime;//����ʱ��
	public int getBuildUnitSingleLayerRooms() {
		return buildUnitSingleLayerRooms;
	}
	public void setBuildUnitSingleLayerRooms(int buildUnitSingleLayerRooms) {
		this.buildUnitSingleLayerRooms = buildUnitSingleLayerRooms;
	}
	public int getBuildUnitSum() {
		return buildUnitSum;
	}
	public void setBuildUnitSum(int buildUnitSum) {
		this.buildUnitSum = buildUnitSum;
	}
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
