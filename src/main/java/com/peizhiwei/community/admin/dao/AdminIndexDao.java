package com.peizhiwei.community.admin.dao;

import org.apache.ibatis.annotations.Select;

public interface AdminIndexDao {
	/**
	 * ��ȡ��������
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM house_info")
	int selecthousesum();
	/**
	 * ��ȡ���������
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM house_info WHERE house_state=0")
	int selectnullhouse();
	/**
	 * ��ȡҵ������
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM house_owner")
	int selectownersum();
	/**
	 * ��ȡ��ҵ����
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM house_owner WHERE owner_sex=1")
	int selectmanofowner();
	/**
	 * ��ȡס��������������ҵ��
	 * @return
	 */
	@Select("SELECT((SELECT COUNT(1) FROM house_owner)+(SELECT COUNT(1) FROM family))")
	int selectresidentsum();
	/**
	 * ��ȡ������ס������
	 * @return
	 */
	@Select("SELECT((SELECT COUNT(1) FROM house_owner WHERE owner_sex=1)+(SELECT COUNT(1) FROM family WHERE family_sex=1))")
	int selectmanresidentsum();
	/**
	 * ��ȡ����ͣ��λ��
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM parking")
	int selectparkingsum();
	/**
	 * ��ȡ���п��г�λ��
	 * @return
	 */
	@Select("SELECT COUNT(1) FROM parking WHERE parking_state=0")
	int selectnullparkingsum();
}
