package com.peizhiwei.community.admin.dao;

import org.apache.ibatis.annotations.Select;

public interface AdminIndexDao {
	/**
	 * 获取房间总数
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM house_info")
	int selecthousesum();
	/**
	 * 获取房间空闲数
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM house_info WHERE house_state=0")
	int selectnullhouse();
	/**
	 * 获取业主总数
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM house_owner")
	int selectownersum();
	/**
	 * 获取男业主数
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM house_owner WHERE owner_sex=1")
	int selectmanofowner();
	/**
	 * 获取住户总人数，包括业主
	 * @return
	 */
	@Select("SELECT((SELECT COUNT(1) FROM house_owner)+(SELECT COUNT(1) FROM family))")
	int selectresidentsum();
	/**
	 * 获取所有男住户人数
	 * @return
	 */
	@Select("SELECT((SELECT COUNT(1) FROM house_owner WHERE owner_sex=1)+(SELECT COUNT(1) FROM family WHERE family_sex=1))")
	int selectmanresidentsum();
	/**
	 * 获取所有停车位数
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM parking")
	int selectparkingsum();
	/**
	 * 获取所有空闲车位数
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM parking WHERE parking_state=0")
	int selectnullparkingsum();
}
