package com.peizhiwei.community.owner.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayMethod;
import com.peizhiwei.community.admin.service.PayInfoSumService;
import com.peizhiwei.community.owner.service.OwnerPayService;

@RequestMapping("/ownerpay")
@Controller
public class OwnerPayController {
	@Autowired
	OwnerPayService ownerpayservice;
	@Autowired
	PayInfoSumService payinfosumservice;
	
	/**
	 * 日期格式，在每一个日期实体上添加一个注解，再在这里添加一下语句，这样可以保证后台和页面间交互的日期格式是标准的，不需要再进行格式化
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	
	/**
	 * 根据业主id查询该业主的缴费信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getpayinfodetails")
	@ResponseBody
	public List<PayInfoDetails> getpayinfodetails(HttpSession session){
		List<PayInfoDetails> listpayinfodetails = new ArrayList<PayInfoDetails>();
		Object ownersession = session.getAttribute("owner");
		HouseOwner owner = (HouseOwner) ownersession;
		try {
			int ownerId = owner.getOwnerId();
			listpayinfodetails = ownerpayservice.getpayinfodetails(ownerId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listpayinfodetails;
	}
	/**
	 * 查询除了现金以外的所有支付方式
	 * @return
	 */
	@RequestMapping("/getallpaymethod")
	@ResponseBody
	public List<PayMethod> getallpaymethod(){
		List<PayMethod> listpaymethod = ownerpayservice.getallpaymethod();
		return listpaymethod;
	}
	
	@RequestMapping("/getpaymethodid")
	@ResponseBody
	public int getpaymethodid(String methodName) {
		return ownerpayservice.getpaymethodid(methodName);
	}
	/**
	 * 业主缴费，更改缴费状态，由业主选择缴费方式
	 * @return
	 */
	@RequestMapping("/paid")
	@ResponseBody
	public JspResult updatepaystate(
			@RequestParam(value = "payId",required = false)int payId,
			@RequestParam(value = "methodId",required = false)int methodId) {
		JspResult rs = new JspResult();
		PayInfoDetails payinfodetails = new PayInfoDetails();
		PayMethod method = new PayMethod();
		try {
			payinfodetails.setPayState(1);
			payinfodetails.setPayTime(new Date());
			method.setMethodId(methodId);
			payinfodetails.setPayMethod(method);
			payinfodetails.setPayId(payId);
			ownerpayservice.paid(payinfodetails);
			payinfosumservice.updatepayinfosum();//更新缴费汇总信息
			rs.setFlag(true);
			rs.setMsg("缴费成功！");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
}
