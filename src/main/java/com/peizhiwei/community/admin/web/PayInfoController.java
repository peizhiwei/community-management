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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.entity.PayInfo;
import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayType;
import com.peizhiwei.community.admin.service.HouseOwnerService;
import com.peizhiwei.community.admin.service.PayInfoDetailsService;
import com.peizhiwei.community.admin.service.PayInfoService;
import com.peizhiwei.community.admin.service.PayInfoSumService;
import com.peizhiwei.community.admin.service.PayTypeService;
import com.peizhiwei.community.util.Pager;
@Controller
@RequestMapping("/payinfo")
public class PayInfoController {
	@Autowired
	PayInfoService payinfoservice;
	@Autowired
	PayTypeService paytypeservice;
	@Autowired
	HouseOwnerService houseownerservice;
	@Autowired
	PayInfoDetailsService payinfodetailsservice;
	@Autowired
	PayInfoSumService payinfosumservice;
	
	/**
	 * ���ڸ�ʽ����ÿһ������ʵ�������һ��ע�⣬�����������һ����䣬�������Ա�֤��̨��ҳ��佻�������ڸ�ʽ�Ǳ�׼�ģ�����Ҫ�ٽ��и�ʽ��
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	/**
	 * ��ҳ��ȡ���еĽɷ���Ϣ
	 * @return
	 */
	@RequestMapping("/pagegetallpayinfo")
	@ResponseBody
	public Pager<PayInfo> pagegetallpayinfo(int page,int size){
		Pager<PayInfo> listpayinfo = payinfoservice.pagegetallpayinfo(page, size);
		return listpayinfo;
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
	 * ͬʱ��������ɷ�����
	 * ��������ɷѻ�����Ϣ�����ܱ���
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
			if(payinfoservice.insertnewpayinfo(payinfo)==true) {
				//��ӽɷ���Ϣ�ɹ�
				List<PayInfoDetails> payinfodetailslist = new ArrayList<PayInfoDetails>();
				List<HouseOwner> houseownerlist = new ArrayList<HouseOwner>();
				houseownerlist = houseownerservice.getallhouseownerinfo();
				int size = houseownerlist.size();
				for(int i=0;i<size;i++) {//��������ɷ�����
					PayInfoDetails payinfodetails = new PayInfoDetails();
					payinfodetails.setHouseOwner(houseownerlist.get(i));
					PayInfo ipayinfo = new PayInfo();
					ipayinfo.setPayInfoId(payinfo.getPayInfoId());//��ȡ�ող���Ľɷ���Ϣ��id
					payinfodetails.setPayInfo(ipayinfo);
					payinfodetails.setPayState(0);
					payinfodetailslist.add(payinfodetails);
				}
				payinfoservice.insertpayinfodetailslist(payinfodetailslist);
				//�������½ɷѻ�����Ϣ
				payinfosumservice.updatepayinfosum();
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
			payinfosumservice.updatepayinfosum();//���½ɷѻ�����Ϣ
			rs.setFlag(true);
			rs.setMsg("�޸ĳɹ���");
		} catch (Exception e) {
			System.out.println("������");
		}
		return rs;
	}
	
	/**
	 * ɾ���ɷ���Ϣ
	 * �����޸Ľɷѻ�����Ϣ
	 * @param payInfoId
	 * @return
	 */
	@RequestMapping("/deletepayinfo")
	@ResponseBody
	public JspResult deletepayinfo(Integer payInfoId) {
		JspResult rs = new JspResult();
		try {
			payinfoservice.deletepayinfo(payInfoId);
			payinfosumservice.updatepayinfosum();//���½ɷѻ�����Ϣ
			rs.setFlag(true);
			rs.setMsg("ɾ���ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * ����ɾ���ɷ���Ϣ
	 * ���½ɷѻ�����Ϣ
	 * @param listpayInfoId
	 * @return
	 */
	@RequestMapping("/checkdelete")
	@ResponseBody
	public JspResult checkdelete(@RequestBody int[] listpayInfoId) {
		JspResult rs = new JspResult();
		payinfoservice.checkdelete(listpayInfoId);
		payinfosumservice.updatepayinfosum();//���½ɷѻ�����Ϣ
		rs.setFlag(true);
		rs.setMsg("��ȫ��ɾ����");
		return rs;
	}
	/**
	 * ���ݽɷ���Ŀ��ѯ�ɷ���Ϣ
	 * @param page
	 * @param size
	 * @param payTypeName
	 * @return
	 */
	@RequestMapping("/selectpayinfoaccordingpaytypename")
	@ResponseBody
	public Pager<PayInfo> selectpayinfoaccordingpaytypename(int page,int size,String payTypeName){
		Pager<PayInfo> listpayinfo = payinfoservice.selectpayinfoaccordingpaytypename(page,size,payTypeName);
		return listpayinfo;
	}
	/**
	 * �������²�ѯ�ɷ���Ϣ
	 * @param page
	 * @param size
	 * @param payInfoStartTime
	 * @return
	 */
	@RequestMapping("/selectpayinfoaccordingpayintostarttime")
	@ResponseBody
	public Pager<PayInfo> selectpayinfoaccordingpayintostarttime(@RequestParam(value = "page",required = false)int page,
			@RequestParam(value = "size",required = false)int size,
			@RequestParam(value = "payInfoStartTime",required = false)String payInfoStartTime){
		String year = payInfoStartTime.substring(0, payInfoStartTime.indexOf("��"));
		String month = payInfoStartTime.substring(payInfoStartTime.indexOf("��")+1, payInfoStartTime.indexOf("��"));
		Pager<PayInfo> listpayinfo = payinfoservice.selectpayinfoaccordingpayintostarttime(page, size, year, month);
		return listpayinfo;
	}
}
