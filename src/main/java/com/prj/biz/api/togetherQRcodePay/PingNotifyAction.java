package com.prj.biz.api.togetherQRcodePay;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pingplusplus.model.Event;
import com.pingplusplus.model.Transfer;
import com.pingplusplus.model.Webhooks;
import com.prj.biz.bean.bankwithdrawal.BankWithdrawal;
import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.bean.order.Order;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.bean.transferrecord.TransferRecord;
import com.prj.biz.bean.withdrawrecord.WithdrawRecord;
import com.prj.biz.service.RebateCalculation.RebateCalculationService;
import com.prj.biz.service.bankwithdrawal.BankWithdrawalService;
import com.prj.biz.service.consumersaccount.ConsumersAccountService;
import com.prj.biz.service.order.OrderService;
import com.prj.biz.service.sysuser.SysUserService;
import com.prj.biz.service.transferrecord.TransferRecordService;
import com.prj.biz.service.withdrawrecord.WithdrawRecordService;
import com.prj.utils.UfdmDateUtil;
import com.prj.utils.ping.TransferExample;
import com.prj.utils.ping.WebhooksVerify;

/**
 * @author: Fengc
 * @date:2017-8-25 下午2:47:26
 * @version :0.0.1
 * @dis:
 */

@Controller
@RequestMapping("/api")
public class PingNotifyAction {
	@Autowired
	private OrderService orderService;

	@Autowired
	private ConsumersAccountService consumersAccountService;

	@Autowired
	private BankWithdrawalService bankWithdrawalService;

	@Autowired
	private WithdrawRecordService withdrawRecordService;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	RebateCalculationService calculationService;

	@Autowired
	TransferRecordService transferRecordService;

	// 第三方回调
	@RequestMapping(value = { "/pingNotify" })
	public void pingSuccess(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
		}

		// 获得 http body 内容
		BufferedReader reader = request.getReader();
		StringBuffer buffer = new StringBuffer();
		String string;
		while ((string = reader.readLine()) != null) {
			buffer.append(string);
		}
		reader.close();

		// 解析异步通知数据
		Event event = Webhooks.eventParse(buffer.toString());
		// 签名
		String signature = request.getHeader("X-Pingplusplus-Signature");
		Boolean isPass = WebhooksVerify
				.checkHooks(buffer.toString(), signature);
		// 如果以上钩子校验在正式数据中还是无法通过验证则使用一下加密方式进行钩子校验
		/*
		 * String orderno = jv.optString("order_no"); String body =
		 * jv.optString("body"); boolean p = ShiroEncryption.validate(body,
		 * orderno);
		 */
		response.setHeader("content-type", "text/html;charset=UTF-8");
		if (isPass) {// 签名校验成功
			if ("charge.succeeded".equals(event.getType())) {
				// App支付成功
				JSONObject s = new JSONObject(buffer.toString());
				JSONObject obj = s.optJSONObject("data");
				JSONObject jv = obj.optJSONObject("object");
				String subject = jv.optString("subject");// subject
				String trade = jv.optString("transaction_no");
				String money = jv.optString("amount");
				String orderno = jv.optString("order_no");
				//String alinum = jv.optString("buyer_id");
				String accounts = jv.optString("buyer_logon_id");
				String accountType = "";
				if (orderno.indexOf("AL") > 0) {
					accountType = "2";
				}
				if (orderno.indexOf("WX") > 0) {
					accountType = "1";
				}
				// 获取订单信息
				String[] orderNo = orderno.split(",");
				for (int i = 0; i < orderNo.length; i++) {
					Order order = new Order();
					order.setOrderNum(orderNo[i]);
					List<Order> orderList = orderService.doGetList(order);
					if (orderList.size() > 0) {
						/************* 添加分成 **************/
						ConsumersAccount account = new ConsumersAccount();
						account.setUserAccount(accounts);
						List<ConsumersAccount> cas = consumersAccountService.doGetList(account);
						if (cas.size() != 0) {
							if ("0".equals(cas.get(0).getIsLocalState())) {
								System.out.println("===被锁定==IsLocalState()=0");
								calculationService.lockedDraw(orderList.get(0).getId(), accounts, orderList.get(0).getBusinessId(), cas.get(0).getId(),accountType);
							}
							if ("1".equals(cas.get(0).getIsLocalState())) {
								System.out.println("===被锁定==IsLocalState()=1");
								calculationService.pumpingCalculation(orderList.get(0).getId(), cas.get(0).getId(),orderList.get(0).getBusinessId());
							}
						} else {
							calculationService.lockedDraw(orderList.get(0).getId(), accounts, orderList.get(0).getBusinessId(), "", accountType);
						}
						calculationService.transferRecord(orderList.get(0));
					}
				}
				response.setStatus(200);
			} else if ("transfer.succeeded".equals(event.getType())) {
				JSONObject s = new JSONObject(buffer.toString());
				JSONObject obj = s.optJSONObject("data");
				JSONObject jv = obj.optJSONObject("object");
				String trade = jv.optString("transaction_no");
				System.out.println("---------------------------------流水号"+trade);
				String money = jv.optString("amount");
				String applyNumber = jv.optString("order_no");
				if (!StringUtils.isEmpty(applyNumber)) {
					// 提现成功之后，改变 提现记录，账户余额
					if (applyNumber.indexOf("HLTX") > 0) {
						WithdrawRecord withdrawRecord = new WithdrawRecord();
						withdrawRecord.setApplyNumber(applyNumber);
						List<WithdrawRecord> withdrawRecords = withdrawRecordService.doGetList(withdrawRecord);
						if (withdrawRecords.size() > 0) {
							withdrawRecords.get(0).setApplyState(UfdmDateUtil.getCurrentTime());
							withdrawRecords.get(0).setApplyState("1");
							withdrawRecords.get(0).setResult("1");
							withdrawRecordService.doSave(withdrawRecords.get(0));
							// 減去用戶的賬戶餘額
							BankWithdrawal bankWithdrawal = bankWithdrawalService.doGetById(withdrawRecords.get(0).getId());
							if (bankWithdrawal != null) {
								SysUser sysUser = sysUserService.doGetById(bankWithdrawal.getUserId());
								if (sysUser != null) {
									sysUser.setAccountBalance(String.valueOf(Float.parseFloat(sysUser.getAccountBalance())- Float.parseFloat(money)));
									sysUserService.doModById(sysUser);
								}
							}

						}
					} else {
						if (applyNumber.indexOf("AL") > 0||applyNumber.indexOf("WX") > 0) {
							System.out.println("转账开始--------------");
							Order order = new Order();
							order.setOrderNum(applyNumber);
							List<Order> orderList = orderService.doGetList(order);
							if (orderList.size() > 0) {
								TransferRecord transferRecord = new TransferRecord();
								transferRecord.setOrderNumber(applyNumber);

								List<TransferRecord> records = transferRecordService.doGetList(transferRecord);
								System.out.println(records.size());
								if (records.size() > 0) {
									TransferRecord record = records.get(0);
									record.setStatus("1");
									record.setSerialNumber(trade);
									transferRecordService.doModById(record);
								}
							}
							

						}
					}
				}

				response.setStatus(200);
				System.out.println("转账成功！");
			}
		} else {
			// 签名校验失败
			response.setStatus(200);
		}
		PrintWriter out = response.getWriter();
		out.write("");
	}
}
