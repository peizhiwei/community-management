package com.peizhiwei.community.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.PayInfo;
import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.util.Pager;

public interface PayInfoService {
	/**
	 * ��ҳ��ȡ���еĽɷ���Ϣ
	 * @return
	 */
	Pager<PayInfo> pagegetallpayinfo(int page,int size);
	/**
	 * ��ȡ���еĽɷ���Ϣ
	 * @return
	 */
	List<PayInfo> getallpayinfo();
	/**
	 * �����µĽɷ���Ϣ
	 * @param payinfo
	 */
	boolean insertnewpayinfo(PayInfo payinfo);
	/**
	 * �ڷ����ɷ���Ϣ��ͬʱ����������ɷ�����
	 * @param houseinfolist
	 */
	void insertpayinfodetailslist(@Param("list")List<PayInfoDetails> list);
	/**
	 * ɾ���ɷ���Ϣ
	 * @param payInfoId
	 */
	void deletepayinfo(int payInfoId);
	/**
	 * �޸Ľɷ���Ϣ
	 * @param payinfo
	 */
	void updatepayinfo(PayInfo payinfo);
	/**
	 * ����ɾ���ɷ���Ϣ
	 * @param listpayInfoId
	 */
	void checkdelete(int[] listpayInfoId);
}
