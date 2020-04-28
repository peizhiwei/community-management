package com.peizhiwei.community.admin.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayInfoSum;
import com.peizhiwei.community.admin.entity.PayMethod;
import com.peizhiwei.community.admin.service.PayInfoDetailsService;
import com.peizhiwei.community.admin.service.PayInfoSumService;
import com.peizhiwei.community.util.Pager;

@Controller
@RequestMapping("/payinfosum")
public class PayInfoSumController {
	@Autowired
	PayInfoSumService payinfosumservice;
	@Autowired
	PayInfoDetailsService payinfodetailsservice;
	/**
	 * 分页获取所有汇总信息
	 * @return
	 */
	@RequestMapping("/pagegetallpayinfosum")
	@ResponseBody
	public Pager<PayInfoSum> pagegetallpayinfosum(int page,int size){
		Pager<PayInfoSum> pagepayinfosum = payinfosumservice.pagegetallpayinfosum(page, size);
		return pagepayinfosum;
	}
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
	public JspResult updatepaystate(Integer ownerId) {
		JspResult rs = new JspResult();
		PayInfoDetails payinfo = new PayInfoDetails();
		PayMethod paymethod = new PayMethod();
		HouseOwner owner = new HouseOwner();
		try {
			owner.setOwnerId(ownerId);
			payinfo.setHouseOwner(owner);
			payinfo.setPayTime(new Date());
			paymethod.setMethodId(payinfodetailsservice.getpaymethodid("现金"));
			payinfo.setPayMethod(paymethod);
			payinfosumservice.updatepayinfodetailsstate(payinfo);
			payinfosumservice.updatepayinfosum();//更新缴费汇总信息
			rs.setFlag(true);
			rs.setMsg("缴费成功！");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * 批量缴费
	 * @param listpayId
	 * @return
	 */
	@RequestMapping("/batchpaid")
	@ResponseBody
	public JspResult batchpaid(@RequestBody int[] listownerId) {
		JspResult rs = new JspResult();
		List<PayInfoDetails> listpayinfodetails = new ArrayList<PayInfoDetails>();
		try {
			for (int i = 0; i < listownerId.length; i++) {
				PayMethod paymethod = new PayMethod();
				HouseOwner owner = new HouseOwner();
				PayInfoDetails payinfodetails = new PayInfoDetails();
				owner.setOwnerId(listownerId[i]);
				payinfodetails.setHouseOwner(owner);
				payinfodetails.setPayTime(new Date());
				paymethod.setMethodId(payinfodetailsservice.getpaymethodid("现金"));
				payinfodetails.setPayMethod(paymethod);
				listpayinfodetails.add(payinfodetails);
			}
			payinfosumservice.batchpaid(listpayinfodetails);
			payinfosumservice.updatepayinfosum();//更新缴费汇总信息
			rs.setFlag(true);
			rs.setMsg("已全部缴费！");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * 根据欠费情况查询缴费汇总信息
	 * @param page
	 * @param size
	 * @param payState
	 * @return
	 */
	@RequestMapping("/selectpayinfodetailsaccordingispaid")
	@ResponseBody
	public Pager<PayInfoSum> selectpayinfodetailsaccordingispaid(
			@RequestParam(value = "page",required = false)int page,
			@RequestParam(value = "size",required = false)int size,
			@RequestParam(value = "payState",required = false)int payState){
		Pager<PayInfoSum> listpayinfosum = new Pager<PayInfoSum>();
		if(payState==0) {//查询欠费汇总信息
			listpayinfosum=payinfosumservice.selectpayinfodetailsaccordingispaid(page, size);
		}else if(payState==1) {//查询不欠费汇总信息
			listpayinfosum = payinfosumservice.selectpayinfodetailsaccordingnotpaid(page, size);
		}
		return listpayinfosum;
	}
}
