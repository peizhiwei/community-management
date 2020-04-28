package com.peizhiwei.community.admin.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.entity.PayInfo;
import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayMethod;
import com.peizhiwei.community.admin.service.PayInfoDetailsService;
import com.peizhiwei.community.admin.service.PayInfoSumService;
import com.peizhiwei.community.util.Pager;

@Controller
@RequestMapping("/payinfodetails")
public class PayInfoDetailsController {
	@Autowired
	PayInfoDetailsService payinfodetailsservice;
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
	 * 分页获取所有缴费信息
	 * @return
	 */
	@RequestMapping("/pagegetallpayinfodetails")
	@ResponseBody
	public Pager<PayInfoDetails> pagegetallpayinfo(int page,int size){
		Pager<PayInfoDetails> pagepayinfodetails = payinfodetailsservice.pagegetallpayinfo(page, size);
		return pagepayinfodetails;
	}
	/**
	 * 获取所有缴费信息
	 * @return
	 */
	@RequestMapping("/getallpayinfodetails")
	@ResponseBody
	public List<PayInfoDetails> getallpayinfo(){
		List<PayInfoDetails> listpayinfo = new ArrayList<PayInfoDetails>();
		listpayinfo=payinfodetailsservice.getallpayinfo();
		return listpayinfo;
	}
	/**
	 * 已缴费，修改缴费状态为已缴费，由管理员操作的缴费方式默认为现金
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
			e.printStackTrace();
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
	public JspResult batchupdate(@RequestBody int[] listpayId) {
		JspResult rs = new JspResult();
		List<PayInfoDetails> listpayinfodetails = new ArrayList<PayInfoDetails>();
		try {
			for(int i=0;i<listpayId.length;i++) {
				PayMethod payMethod = new PayMethod();
				PayInfoDetails payinfo = new PayInfoDetails();
				payinfo.setPayId(listpayId[i]);
				payinfo.setPayTime(new Date());
				payMethod.setMethodId(payinfodetailsservice.getpaymethodid("现金"));
				payinfo.setPayMethod(payMethod);
				listpayinfodetails.add(payinfo);
			}
			payinfodetailsservice.batchpaid(listpayinfodetails);//批量缴费
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
	 * 根据缴费项目查询缴费信息
	 * @param page
	 * @param size
	 * @param payTypeName
	 * @return
	 */
	@RequestMapping("/selectpayinfoaccordingpaytypename")
	@ResponseBody
	public Pager<PayInfoDetails> selectpayinfoaccordingpaytypename(int page,int size,String payTypeName){
		Pager<PayInfoDetails> listpayinfo = payinfodetailsservice.selectpayinfoaccordingpaytypename(page,size,payTypeName);
		return listpayinfo;
	}
	/**
	 * 根据年月查询缴费信息
	 * @param page
	 * @param size
	 * @param payInfoStartTime
	 * @return
	 */
	@RequestMapping("/selectpayinfoaccordingpayintostarttime")
	@ResponseBody
	public Pager<PayInfoDetails> selectpayinfoaccordingpayintostarttime(@RequestParam(value = "page",required = false)int page,
			@RequestParam(value = "size",required = false)int size,
			@RequestParam(value = "payInfoStartTime",required = false)String payInfoStartTime){
		String year = payInfoStartTime.substring(0, payInfoStartTime.indexOf("年"));
		String month = payInfoStartTime.substring(payInfoStartTime.indexOf("年")+1, payInfoStartTime.indexOf("月"));
		Pager<PayInfoDetails> listpayinfo = payinfodetailsservice.selectpayinfoaccordingpayintostarttime(page, size, year, month);
		return listpayinfo;
	}
	/**
	 * 根据缴费状态查询缴费详情
	 * @param page
	 * @param size
	 * @param payState
	 * @return
	 */
	@RequestMapping("/selectpayinfoaccordingpaystate")
	@ResponseBody
	public Pager<PayInfoDetails> selectpayinfoaccordingpaystate(
			@RequestParam(value = "page",required = false)int page,
			@RequestParam(value = "size",required = false)int size,
			@RequestParam(value = "payState",required = false)String payState){
		Pager<PayInfoDetails> listpayinfodetails = payinfodetailsservice.selectpayinfoaccordingpaystate(page, size, payState);
		return listpayinfodetails;
	}
}
