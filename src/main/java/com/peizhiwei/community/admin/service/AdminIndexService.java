package com.peizhiwei.community.admin.service;


public interface AdminIndexService {
	/**
	 * 获取房间总数
	 * @return
	 */
	int selecthousesum();
	/**
	 * 获取房间空闲数
	 * @return
	 */
	int selectnullhouse();
	/**
	 * 获取业主总数
	 * @return
	 */
	int selectownersum();
	/**
	 * 获取男业主数
	 * @return
	 */
	int selectmanofowner();
	/**
	 * 获取住户总人数，包括业主
	 * @return
	 */
	int selectresidentsum();
	/**
	 * 获取所有男住户人数
	 * @return
	 */
	int selectmanresidentsum();
	/**
	 * 获取所有停车位数
	 * @return
	 */
	int selectparkingsum();
	/**
	 * 获取所有空闲车位数
	 * @return
	 */
	int selectnullparkingsum();
}
