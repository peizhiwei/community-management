package com.peizhiwei.community.admin.service;


public interface AdminIndexService {
	/**
	 * ��ȡ��������
	 * @return
	 */
	int selecthousesum();
	/**
	 * ��ȡ���������
	 * @return
	 */
	int selectnullhouse();
	/**
	 * ��ȡҵ������
	 * @return
	 */
	int selectownersum();
	/**
	 * ��ȡ��ҵ����
	 * @return
	 */
	int selectmanofowner();
	/**
	 * ��ȡס��������������ҵ��
	 * @return
	 */
	int selectresidentsum();
	/**
	 * ��ȡ������ס������
	 * @return
	 */
	int selectmanresidentsum();
	/**
	 * ��ȡ����ͣ��λ��
	 * @return
	 */
	int selectparkingsum();
	/**
	 * ��ȡ���п��г�λ��
	 * @return
	 */
	int selectnullparkingsum();
}
