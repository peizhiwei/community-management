package com.peizhiwei.community.admin.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.entity.PayMethod;
import com.peizhiwei.community.admin.service.PayMethodService;

@RequestMapping("/paymethodinfo")
@Controller
public class PayMethodController {
	@Autowired
	PayMethodService paymethodservice;
	
	/**
	 * 获取所有的支付方式
	 * @return
	 */
	@RequestMapping("/getallpaymethodinfo")
	@ResponseBody
	public List<PayMethod> getallpaymethodinfo(){
		List<PayMethod> listmethod = new ArrayList<PayMethod>();
		try {
			listmethod=paymethodservice.getallpaymethodinfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listmethod;
	}
	/**
	 * 修改支付方式
	 * @param methodId
	 * @param methodName
	 * @param methodRemarks
	 * @return
	 */
	@RequestMapping("/updatepaymethodinfo")
	@ResponseBody
	public JspResult updatepaymethodinfo(
			@RequestParam(value = "",required = false)Integer methodId,
			@RequestParam(value = "methodName",required = false)String methodName,
			@RequestParam(value = "methodRemarks",required = false)String methodRemarks) {
		JspResult rs = new JspResult();
		PayMethod paymethod = new PayMethod();
		try {
			paymethod.setMethodId(methodId);
			paymethod.setMethodName(methodName);
			paymethod.setMethodRemarks(methodRemarks);
			paymethodservice.updatepaymethodinfo(paymethod);
			rs.setFlag(true);
			rs.setMsg("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 删除支付方式
	 * @param methodId
	 * @return
	 */
	@RequestMapping("/deletepaymethodinfo")
	@ResponseBody
	public JspResult deletepaymethodinfo(Integer methodId) {
		JspResult rs = new JspResult();
		try {
			paymethodservice.deletepaymethodinfo(methodId);
			rs.setFlag(true);
			rs.setMsg("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 新增支付方式
	 * @param methodName
	 * @param methodRemarks
	 * @return
	 */
	@RequestMapping("/insertpaymethodinfo")
	@ResponseBody
	public JspResult insertpaymethodinfo(
			@RequestParam(value = "methodName",required = false)String methodName,
			@RequestParam(value = "methodRemarks",required = false)String methodRemarks) {
		JspResult rs = new JspResult();
		PayMethod paymethod = new PayMethod();
		try {
			if(paymethodservice.selectpaymethodname(methodName)==true) {
				rs.setFlag(false);
				rs.setMsg("该支付方式已存在，无需再添加！");
			}else {
				paymethod.setMethodName(methodName);
				paymethod.setMethodRemarks(methodRemarks);
				paymethodservice.insertpaymethodinfo(paymethod);
				rs.setFlag(true);
				rs.setMsg("添加成功！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
