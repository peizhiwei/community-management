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
			if (paytypeservice.selectpaytypeofpayinfo(payTypeId)==true) {
				rs.setFlag(false);
				rs.setMsg(paytypeservice.selectpaytypenameaccordingtypeid(payTypeId)+"，已存在缴费信息，无法删除");
			}else {
				paytypeservice.deletepaytypeinfo(payTypeId);
				rs.setFlag(true);
				rs.setMsg("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * 批量删除缴费类别
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
			if(paytypeservice.selectpaytypeofpayinfo(listpayTypeId[i])==true) {//判断缴费信息中是否存在在类别的缴费
				//将在缴费信息中存在的缴费类别名存储起来
				listpaytypename += paytypeservice.selectpaytypenameaccordingtypeid(listpayTypeId[i])+",";
			}else {
				int payTypeId = listpayTypeId[i];
				listpayinfonullpaytypeid.add(payTypeId);//将在缴费信息中不存在的缴费类别id存储起来
			}
		}
		int listpayinfonull=listpayinfonullpaytypeid.size();
		int listpaytypeif = listpayTypeId.length;
		if(listpayinfonull==listpaytypeif) {//都能删除
			paytypeservice.checkdelete(listpayinfonullpaytypeid);
			rs.setFlag(true);
			rs.setMsg("已全部删除！");
			return rs;
		}else if(listpayinfonullpaytypeid.size()>0){//有一部分不能删除
			paytypeservice.checkdelete(listpayinfonullpaytypeid);
		}
		rs.setFlag(false);
		rs.setMsg(listpaytypename+"已存在缴费信息，无法删除！");
		return rs;
	}
}
