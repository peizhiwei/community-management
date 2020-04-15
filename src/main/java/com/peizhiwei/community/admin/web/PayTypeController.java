package com.peizhiwei.community.admin.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.entity.PayType;
import com.peizhiwei.community.admin.service.PayTypeService;

@Controller
@RequestMapping("/paytypeinfo")
public class PayTypeController {
	@Autowired
	PayTypeService paytypeservice;
	
	/**
	 * ��ȡ�����շ�����
	 * @return
	 */
	@RequestMapping("/getallpaytypeinfo")
	@ResponseBody
	public List<PayType> getallpaytypeinfo(){
		List<PayType> typelist = new ArrayList<PayType>();
		typelist = paytypeservice.getallpaytype();
		return typelist;
	}
	/**
	 * �޸������Ϣ
	 * @return
	 */
	@RequestMapping("/updatepayinfo")
	@ResponseBody
	public JspResult updatepayinfo(
			@RequestParam(value = "payTypeId",required = false)Integer payTypeId,
			@RequestParam(value = "payTypeName",required = false)String payTypeName,
			@RequestParam(value = "payTypeRemarks",required = false)String payTypeRemarks) {
		JspResult rs = new JspResult();
		PayType paytype = new PayType();
		try {
			paytype.setPayTypeId(payTypeId);
			paytype.setPayTypeName(payTypeName);
			paytype.setPayTypeRemarks(payTypeRemarks);
			paytypeservice.updatepaytypeinfo(paytype);
			rs.setFlag(true);
			rs.setMsg("�޸ĳɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * ɾ���շ������Ϣ
	 * @param payTypeId
	 * @return
	 */
	@RequestMapping("/deletepaytypeinfo")
	@ResponseBody
	public JspResult deletepaytypeinfo(Integer payTypeId) {
		JspResult rs = new JspResult();
		try {
			if (paytypeservice.selectpaytypeofpayinfo(payTypeId)==true) {
				rs.setFlag(false);
				rs.setMsg(paytypeservice.selectpaytypenameaccordingtypeid(payTypeId)+"���Ѵ��ڽɷ���Ϣ���޷�ɾ��");
			}else {
				paytypeservice.deletepaytypeinfo(payTypeId);
				rs.setFlag(true);
				rs.setMsg("ɾ���ɹ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * �����շ������Ϣ
	 * @param payTypeName
	 * @param payTypeRemarks
	 * @return
	 */
	@RequestMapping("/insertpaytypeinfo")
	@ResponseBody
	public JspResult insertpaytypeinfo(
			@RequestParam(value = "payTypeName",required = false)String payTypeName,
			@RequestParam(value = "payTypeRemarks",required = false)String payTypeRemarks) {
		JspResult rs = new JspResult();
		PayType paytype = new PayType();
		try {
			paytype.setPayTypeName(payTypeName);
			paytype.setPayTypeRemarks(payTypeRemarks);
			if(paytypeservice.selectpaytypename(payTypeName)==true) {
				rs.setFlag(false);
				rs.setMsg("�����Ѵ��ڣ���������ӣ�");
			}else {
				paytypeservice.insertpaytypeinfo(paytype);
				rs.setFlag(true);
				rs.setMsg("��ӳɹ�");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * ����ɾ���ɷ����
	 * @param listpayTypeId
	 * @return
	 */
	@RequestMapping("/checkdelete")
	@ResponseBody
	public JspResult checkdelete(@RequestBody int[] listpayTypeId) {
		JspResult rs = new JspResult();
		String listpaytypename = new String();
		List<Integer> listpayinfonullpaytypeid = new ArrayList<Integer>();
		for(int i=0;i<listpayTypeId.length;i++) {
			if(paytypeservice.selectpaytypeofpayinfo(listpayTypeId[i])==true) {//�жϽɷ���Ϣ���Ƿ���������Ľɷ�
				//���ڽɷ���Ϣ�д��ڵĽɷ�������洢����
				listpaytypename += paytypeservice.selectpaytypenameaccordingtypeid(listpayTypeId[i])+",";
			}else {
				int payTypeId = listpayTypeId[i];
				listpayinfonullpaytypeid.add(payTypeId);//���ڽɷ���Ϣ�в����ڵĽɷ����id�洢����
			}
		}
		int listpayinfonull=listpayinfonullpaytypeid.size();
		int listpaytypeif = listpayTypeId.length;
		if(listpayinfonull==listpaytypeif) {//����ɾ��
			paytypeservice.checkdelete(listpayinfonullpaytypeid);
			rs.setFlag(true);
			rs.setMsg("��ȫ��ɾ����");
			return rs;
		}else if(listpayinfonullpaytypeid.size()>0){//��һ���ֲ���ɾ��
			paytypeservice.checkdelete(listpayinfonullpaytypeid);
		}
		rs.setFlag(false);
		rs.setMsg(listpaytypename+"�Ѵ��ڽɷ���Ϣ���޷�ɾ����");
		return rs;
	}
}
