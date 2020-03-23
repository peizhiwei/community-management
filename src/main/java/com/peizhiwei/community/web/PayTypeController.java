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
	 * 获取所有收费类型
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
	 * 修改类别信息
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
			rs.setMsg("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 删除收费类别信息
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
			rs.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 新增收费类别信息
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
				rs.setMsg("此项已存在，无需再添加！");
			}else {
				paytypeservice.insertpaytypeinfo(paytype);
				rs.setFlag(true);
				rs.setMsg("添加成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
