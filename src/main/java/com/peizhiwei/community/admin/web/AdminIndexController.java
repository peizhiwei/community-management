package com.peizhiwei.community.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.service.AdminIndexService;

@RequestMapping("/adminindex")
@Controller
public class AdminIndexController {
	@Autowired
	AdminIndexService adminindexservice;
	/**
	 * ��ȡ��������
	 * @return
	 */
	@RequestMapping("/gethouseinfosum")
	@ResponseBody
	public int gethouseinfosum() {
		int houseinfosum = adminindexservice.selecthousesum();
		return houseinfosum;
	}
	/**
	 * ��ȡ�շ�����
	 * @return
	 */
	@RequestMapping("/getnullhouseinfo")
	@ResponseBody
	public int getnullhouseinfo() {
		int nullhouse = adminindexservice.selectnullhouse();
		return nullhouse;
	}
	/**
	 * ��ȡҵ������
	 */
	@RequestMapping("getownersum")
	@ResponseBody
	public int getownersum() {
		int ownersum = adminindexservice.selectownersum();
		return ownersum;
	}
	/**
	 * ��ȡ��ҵ����
	 */
	@RequestMapping("/getmanofowner")
	@ResponseBody
	public int getmanowner() {
		int manofowner = adminindexservice.selectmanofowner();
		return manofowner;
	}
	/**
	 * ��ȡס������
	 */
	@RequestMapping("/getresidentsum")
	@ResponseBody
	public int getresidentsum() {
		int residentsum = adminindexservice.selectresidentsum();
		return residentsum;
	}
	/**
	 * ��ȡ��ס����
	 */
	@RequestMapping("/getmanresidentsum")
	@ResponseBody
	public int getmanresidentsum() {
		int manresidentsum = adminindexservice.selectmanresidentsum();
		return manresidentsum;
	}
	/**
	 * ��ȡͣ��λ����
	 */
	@RequestMapping("/getparkingsum")
	@ResponseBody
	public int getparkingsum() {
		int parkingSum = adminindexservice.selectparkingsum();
		return parkingSum;
	}
	/**
	 * ��ȡ����ͣ��λ��
	 */
	@RequestMapping("/getnullparkingsum")
	@ResponseBody
	public int getnullparkingsum() {
		int nullparkingsum = adminindexservice.selectnullparkingsum();
		return nullparkingsum;
	}
	
}
