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
	 * ��ҳ��ȡ���л�����Ϣ
	 * @return
	 */
	@RequestMapping("/pagegetallpayinfosum")
	@ResponseBody
	public Pager<PayInfoSum> pagegetallpayinfosum(int page,int size){
		Pager<PayInfoSum> pagepayinfosum = payinfosumservice.pagegetallpayinfosum(page, size);
		return pagepayinfosum;
	}
	/**
	 * ��ȡ���л�����Ϣ
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
	 * һ���ɷ�
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
			paymethod.setMethodId(payinfodetailsservice.getpaymethodid("�ֽ�"));
			payinfo.setPayMethod(paymethod);
			payinfosumservice.updatepayinfodetailsstate(payinfo);
			payinfosumservice.updatepayinfosum();//���½ɷѻ�����Ϣ
			rs.setFlag(true);
			rs.setMsg("�ɷѳɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * �����ɷ�
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
				paymethod.setMethodId(payinfodetailsservice.getpaymethodid("�ֽ�"));
				payinfodetails.setPayMethod(paymethod);
				listpayinfodetails.add(payinfodetails);
			}
			payinfosumservice.batchpaid(listpayinfodetails);
			payinfosumservice.updatepayinfosum();//���½ɷѻ�����Ϣ
			rs.setFlag(true);
			rs.setMsg("��ȫ���ɷѣ�");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * ����Ƿ�������ѯ�ɷѻ�����Ϣ
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
		if(payState==0) {//��ѯǷ�ѻ�����Ϣ
			listpayinfosum=payinfosumservice.selectpayinfodetailsaccordingispaid(page, size);
		}else if(payState==1) {//��ѯ��Ƿ�ѻ�����Ϣ
			listpayinfosum = payinfosumservice.selectpayinfodetailsaccordingnotpaid(page, size);
		}
		return listpayinfosum;
	}
}
