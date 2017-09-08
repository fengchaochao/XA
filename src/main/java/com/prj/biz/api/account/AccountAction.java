package com.prj.biz.api.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prj.biz.bean.bankwithdrawal.BankWithdrawal;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.service.bankwithdrawal.BankWithdrawalService;
import com.prj.biz.service.consumersaccount.ConsumersAccountService;
import com.prj.biz.service.sysuser.SysUserService;
import com.prj.biz.service.withdrawrecord.WithdrawRecordService;
import com.prj.core.bean.resp.RespBean;


/**
 * @author: xjt
 * @date:2017-8-17 
 * @version :0.0.1
 * @dis:Api账户管理
 */
@Controller
@RequestMapping("/api")
public class AccountAction {

	@Autowired
	private ConsumersAccountService consumersAccountService;
	@Autowired
	private BankWithdrawalService bankWithdrawalService;
	@Autowired
	private WithdrawRecordService recordService;
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * App添加银行卡
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AppBrankAdd")
	@ResponseBody
	public RespBean<BankWithdrawal> AppBrankAdd(BankWithdrawal bankWithdrawal,String sellerId) throws Exception{
		RespBean<BankWithdrawal> respBean = new RespBean<BankWithdrawal>();
		if(sellerId != null && !sellerId.equals("")){
			
			SysUser sysUser = sysUserService.doGetById(sellerId);
			if(sysUser != null) {
				String number = sysUser.getAccountBalance();
				bankWithdrawal.setTotalRevenue(number);
			}
		}
		bankWithdrawal.setUserId(sellerId);
		bankWithdrawalService.doSave(bankWithdrawal);
		respBean.setBody(bankWithdrawal);
		return respBean;
	}
	
	
	/**
	 * App修改银行卡
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AppBrankUpd")
	@ResponseBody
	public RespBean<BankWithdrawal> AppBrankUpd(BankWithdrawal bankWithdrawal) throws Exception{
		RespBean<BankWithdrawal> respBean = new RespBean<BankWithdrawal>();
		bankWithdrawalService.doModById(bankWithdrawal);
		respBean.setBody(bankWithdrawal);
		return respBean;
	}
	
	/**
	 * App查看银行卡信息
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AppBrankById")
	@ResponseBody
	public RespBean<BankWithdrawal> AppBrankById(String id) throws Exception{
		RespBean<BankWithdrawal> respBean = new RespBean<BankWithdrawal>();
		BankWithdrawal bankWithdrawal = bankWithdrawalService.doGetById(id);
		respBean.setBody(bankWithdrawal);
		return respBean;
	}
	
	
	
	/**
	 * App根据银行卡号前6个字符获取银行卡发卡行
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AppBrankByNum")
	@ResponseBody
	public static RespBean<Map<String, Object>> AppBrankByNum(String cardnumber) {  
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
    	String charBin = cardnumber.substring(0, 6);
        int bin = 0, index = 0;  
        bin=Integer.valueOf(charBin);  
        index = binarySearch(BrankInfo.getBankbin(), bin);  
        if (index == -1 ||  index > BrankInfo.getBankname().length) {  
            return null;  
        }  
       
        map.put("brankName", BrankInfo.getBankname()[index]);
        respBean.setBody(map);
		return respBean;
  
    }  
	
	
    public static int binarySearch(int[] srcArray, int des) {  
        int low = 0;  
        int high = srcArray.length;  
        while (low < high) {  
              
            if (des == srcArray[low]) {  
                return low;  
            }  
            low++;  
        }  
        return -1;  
    }
}
