package com.peizhiwei.community.admin.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.entity.PayInfo;
import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayType;
import com.peizhiwei.community.admin.service.HouseOwnerService;
import com.peizhiwei.community.admin.service.PayInfoService;
import com.peizhiwei.community.admin.service.PayTypeService;
@Controller
@RequestMapping("/payinfo")
public class PayInfoController {
	@Autowired
	PayInfoService payinfoservice;
	@Autowired
	PayTypeService paytypeservice;
	@Autowired
	HouseOwnerService houseownerservice;
	
	/**
	 * ���ڸ�ʽ����ÿһ������ʵ�������һ��ע�⣬�����������һ����䣬�������Ա�֤��̨��ҳ��佻�������ڸ�ʽ�Ǳ�׼�ģ�����Ҫ�ٽ��и�ʽ��
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	/**
	 * ��ȡ���еĽɷ���Ϣ
	 * @return
	 */
	@RequestMapping("/getallpayinfo")
	@ResponseBody
	public List<PayInfo> getallpayinfo(){
		List<PayInfo> listpayinfo = payinfoservice.getallpayinfo();
		return listpayinfo;
	}
	/**
	 * �����ɷ���Ϣ
	 * @param payTypeName
	 * @param payMoney
	 * @param payEndTime
	 * @return
	 */
	@RequestMapping("/insertpayinfo")
	@ResponseBody
	public JspResult insertpayinfo(
			@RequestParam(value = "payTypeName",required = false)String payTypeName,
			@RequestParam(value = "payMoney",required = false)BigDecimal payMoney,
			@RequestParam(value = "payEndTime",required = false)Date payEndTime) {
		JspResult rs = new JspResult();
		PayInfo payinfo = new PayInfo();
		PayType paytype = new PayType();
		try {
			int payTypeId = paytypeservice.selectpaytypeid(payTypeName);
			paytype.setPayTypeId(payTypeId);
			payinfo.setPayType(paytype);
			payinfo.setPayInfoMoney(payMoney);
			payinfo.setPayInfoStartTime(new Date());
			payinfo.setPayInfoEndTime(payEndTime);
			if(payinfoservice.insertnewpayinfo(payinfo)==true) {//��ӽɷ���Ϣ�ɹ�
				List<PayInfoDetails> payinfodetailslist = new ArrayList<PayInfoDetails>();
				List<HouseOwner> houseownerlist = new ArrayList<HouseOwner>();
				houseownerlist = houseownerservice.getallhouseownerinfo();
				int size = houseownerlist.size();
				System.out.println(payinfo.getPayType().getPayTypeName());
				System.out.println(payinfo.getPayInfoMoney());
				System.out.println(payinfo.getPayInfoStartTime());
				System.out.println(payinfo.getPayInfoEndTime());
				System.out.println(payinfo.getPayInfoId());
				for(int i=0;i<size;i++) {//��������ɷ�����
					PayInfoDetails payinfodetails = new PayInfoDetails();
					payinfodetails.setHouseOwner(houseownerlist.get(i));
					PayInfo ipayinfo = new PayInfo();
					ipayinfo.setPayInfoId(payinfo.getPayInfoId());//��ȡ�ող���Ľɷ���Ϣ��id
//					System.out.println(payinfo.getPayInfoId());
					payinfodetails.setPayInfo(ipayinfo);
					payinfodetails.setPayState(0);
					payinfodetailslist.add(payinfodetails);
				}
				payinfoservice.insertpayinfodetailslist(payinfodetailslist);
				rs.setFlag(true);
				rs.setMsg("�����ɹ���");
			}else {
				rs.setFlag(false);
				rs.setMsg("����ʧ�ܣ�");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * �޸Ľɷ���Ϣ
	 * @param payInfoId
	 * @param payTypeName
	 * @param payMoney
	 * @param payEndTime
	 * @return
	 */
	@RequestMapping("/updatepayinfo")
	@ResponseBody
	public JspResult updatepayinfo(
			@RequestParam(value = "payInfoId",required = false)Integer payInfoId,
			@RequestParam(value = "payTypeName",required = false)String payTypeName,
			@RequestParam(value = "payMoney",required = false)BigDecimal payMoney,
			@RequestParam(value = "payEndTime",required = false)Date payEndTime) {
		JspResult rs = new JspResult();
		PayInfo payinfo = new PayInfo();
		PayType paytype = new PayType();
		try {
			payinfo.setPayInfoId(payInfoId);
			int paytypeid = paytypeservice.selectpaytypeid(payTypeName);
			paytype.setPayTypeId(paytypeid);
			payinfo.setPayType(paytype);
			payinfo.setPayInfoMoney(payMoney);
			payinfo.setPayInfoEndTime(payEndTime);
			payinfoservice.updatepayinfo(payinfo);
			rs.setFlag(true);
			rs.setMsg("�޸ĳɹ���");
		} catch (Exception e) {
			System.out.println("������");
		}
		return rs;
	}
	
	/**
	 * ɾ���ɷ���Ϣ
	 * @param payInfoId
	 * @return
	 */
	@RequestMapping("/deletepayinfo")
	@ResponseBody
	public JspResult deletepayinfo(Integer payInfoId) {
		JspResult rs = new JspResult();
		try {
			payinfoservice.deletepayinfo(payInfoId);
			rs.setFlag(true);
			rs.setMsg("ɾ���ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
