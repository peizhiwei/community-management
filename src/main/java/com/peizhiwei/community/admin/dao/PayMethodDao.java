package com.peizhiwei.community.admin.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.PayMethod;

public interface PayMethodDao {
	/**
	 * ��ȡ���еĽɷѷ�ʽ
	 * @return
	 */
	List<PayMethod> getallpaymethodinfo();
}
