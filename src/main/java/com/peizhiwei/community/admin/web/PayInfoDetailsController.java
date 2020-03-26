package com.peizhiwei.community.admin.web;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayMethod;
import com.peizhiwei.community.admin.service.PayInfoDetailsService;

@Controller
@RequestMapping("/payinfodetails")
public class PayInfoDetailsController {
	@Autowired
	PayInfoDetailsService payinfodetailsservice;
	
	/**
	 * ���ڸ�ʽ����ÿһ������ʵ�������һ��ע�⣬�����������һ����䣬�������Ա�֤��̨��ҳ��佻�������ڸ�ʽ�Ǳ�׼�ģ�����Ҫ�ٽ��и�ʽ��
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	/**
	 * ��ȡ���нɷ���Ϣ
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
	 * �ѽɷѣ��޸Ľɷ�״̬Ϊ�ѽɷѣ��ɹ���Ա�����Ľɷѷ�ʽĬ��Ϊ�ֽ�
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
			paymethod.setMethodId(payinfodetailsservice.getpaymethodid("�ֽ�"));
			payinfo.setPayMethod(paymethod);
			payinfodetailsservice.updatepaystate(payinfo);
			rs.setFlag(true);
			rs.setMsg("�ɷѳɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
