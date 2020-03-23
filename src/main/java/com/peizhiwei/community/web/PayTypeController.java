package com.peizhiwei.community.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.entity.JspResult;
import com.peizhiwei.community.entity.PayType;
import com.peizhiwei.community.service.PayTypeService;

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
			paytypeservice.deletepaytypeinfo(payTypeId);
			rs.setFlag(true);
			rs.setMsg("ɾ���ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
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
		}
		return rs;
	}
}
