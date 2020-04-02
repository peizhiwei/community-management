package com.peizhiwei.community.admin.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayInfoSum;
import com.peizhiwei.community.admin.entity.PayMethod;
import com.peizhiwei.community.admin.service.PayInfoDetailsService;
import com.peizhiwei.community.admin.service.PayInfoSumService;

@Controller
@RequestMapping("/payinfosum")
public class PayInfoSumController {
	@Autowired
	PayInfoSumService payinfosumservice;
	@Autowired
	PayInfoDetailsService payinfodetailsservice;
	/**
	 * 获取所有汇总信息
	 * @return
	 */
	@RequestMapping("/getallpayinfosum")
	@ResponseBody
	public List<PayInfoSum> getallpayinfosum(){
		List<PayInfoSum> listpayinfosum = new ArrayList<PayInfoSum>();
		listpayinfosum=payinfosumservice.getallpayinfosum();
		return listpayinfosum;
	}
	/**
	 * 一键缴费
	 * @param payId
	 * @return
	 */
	@RequestMapping("/updatepaystate")
	@ResponseBody
	public JspResult updatepaystate(Integer payId) {
		JspResult rs = new JspResult();
		PayInfoDetails payinfo = new PayInfoDetails();
		PayMethod paymethod = new PayMethod();
		try {
			payinfo.setPayId(payId);
			payinfo.setPayTime(new Date());
			paymethod.setMethodId(payinfodetailsservice.getpaymethodid("现金"));
			payinfo.setPayMethod(paymethod);
			payinfodetailsservice.updatepaystate(payinfo);
			payinfosumservice.updatepayinfosum();//更新缴费汇总信息
			rs.setFlag(true);
			rs.setMsg("缴费成功！");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
}
