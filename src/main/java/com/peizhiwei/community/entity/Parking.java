package com.peizhiwei.community.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Parking {
	private Integer parkingId;//id
	private String parkingNumber;//��λ���
	private int parkingstate;//��λ��ǰ״̬��1��ʾ���ۣ�0��ʾδ��
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date parkingSellTime;//��λ����ʱ�䣬����Ϊ��
	private BigDecimal parkingPrice;//��λ���ۼ۸�
	private HouseOwner houseOwner;
	public Integer getParkingId() {
		return parkingId;
	}
	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}
	public String getParkingNumber() {
		return parkingNumber;
	}
	public void setParkingNumber(String parkingNumber) {
		this.parkingNumber = parkingNumber;
	}
	public int getParkingstate() {
		return parkingstate;
	}
	public void setParkingstate(int parkingstate) {
		this.parkingstate = parkingstate;
	}
	public Date getParkingSellTime() {
		return parkingSellTime;
	}
	public void setParkingSellTime(Date parkingSellTime) {
		this.parkingSellTime = parkingSellTime;
	}
	public BigDecimal getParkingPrice() {
		return parkingPrice;
	}
	public void setParkingPrice(BigDecimal parkingPrice) {
		this.parkingPrice = parkingPrice;
	}
	public HouseOwner getHouseOwner() {
		return houseOwner;
	}
	public void setHouseOwner(HouseOwner houseOwner) {
		this.houseOwner = houseOwner;
	}
}
