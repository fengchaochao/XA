package com.prj.biz.api.payCallback;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prj.biz.bean.withdrawrecord.WithdrawRecord;
import com.prj.biz.service.withdrawrecord.WithdrawRecordService;
import com.unionpay.acp.demo.DemoBase;
import com.unionpay.acp.sdk.AcpService;
import com.unionpay.acp.sdk.LogUtil;
import com.unionpay.acp.sdk.SDKConfig;
import com.unionpay.acp.sdk.SDKConstants;

/**
 * @author: Fengc
 * @date:2017-8-16 下午5:46:28
 * @version :0.0.1
 * @dis:支付回调 前台类交易成功才会发送后台通知。后台类交易（有后台通知的接口）交易结束之后成功失败都会发通知。
 *           为保证安全，涉及资金类的交易，收到通知后请再发起查询接口确认交易成功。不涉及资金的交易可以以通知接口respCode=00判断成功。
 *           未收到通知时
 *           ，查询接口调用时间点请参照此FAQ：https://open.unionpay.com/ajweb/help/faq/list
 *           ?id=77&level=0&from=0
 */
@Controller
@RequestMapping("/api")
public class PayNotify {
	
	//银联付款方商户号
	private static final String merId ="777290058150459";
	@Resource
	private WithdrawRecordService withdrawRecordService;
	/**
	 * 银联支付回调
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	@RequestMapping(value = "/unionpayNotify")
	public void AppRegister(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		LogUtil.writeLog("BackRcvResponse接收后台通知开始");

		String encoding = req.getParameter(SDKConstants.param_encoding);
		// 获取银联通知服务器发送的后台通知参数
		Map<String, String> reqParam = getAllRequestParam(req);
		LogUtil.printRequestLog(reqParam);

		// 重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
		if (!AcpService.validate(reqParam, encoding)) {
			LogUtil.writeLog("验证签名结果[失败].");
			// 验签失败，需解决验签问题

		} else {
			LogUtil.writeLog("验证签名结果[成功].");
			// 【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态

			String orderId = reqParam.get("orderId"); // 获取后台通知的数据，其他字段也可用类似方式获取
			String respCode = reqParam.get("respCode");
			if("00".equals(respCode)){
				
				// 判断respCode=00、A6后，对涉及资金类的交易，请再发起查询接口查询，确定交易成功后更新数据库。
				WithdrawRecord withdrawRecord = new WithdrawRecord();
				withdrawRecord.setApplyNumber(orderId);
				
				List<WithdrawRecord> withdrawRecords = withdrawRecordService.doGetList(withdrawRecord);
				//正常情况此处只会有一条记录，不再做校验
				for (WithdrawRecord withdrawRecord2 : withdrawRecords) {
					Map<Integer,String> map = doQueryUnpayResutl(withdrawRecord2);
					 if(map.get(0) != null){
						 //打款成功
						 withdrawRecord2.setResult("0");
						 withdrawRecordService.doModById(withdrawRecord2);						 
					 }else{
						 withdrawRecord2.setResult("1");
						 withdrawRecord2.setProcessingTime(map.get(0));
						 withdrawRecordService.doModById(withdrawRecord2);	
						 
					 }
					 
				}
			}
		}
		LogUtil.writeLog("BackRcvResponse接收后台通知结束");
		// 返回给银联服务器http 200 状态码
		resp.getWriter().print("ok");
	}

	/**
	 * 获取请求参数中所有的信息 当商户上送frontUrl或backUrl地址中带有参数信息的时候，
	 * 这种方式会将url地址中的参数读到map中，会导多出来这些信息从而致验签失败
	 * ，这个时候可以自行修改过滤掉url中的参数或者使用getAllRequestParamStream方法。
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParam(
			final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				// 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				if (res.get(en) == null || "".equals(res.get(en))) {
					// System.out.println("======为空的字段名===="+en);
					res.remove(en);
				}
			}
		}
		return res;
	}

	/**
	 * 获取请求参数中所有的信息。
	 * 非struts可以改用此方法获取，好处是可以过滤掉request.getParameter方法过滤不掉的url中的参数。
	 * struts可能对某些content
	 * -type会提前读取参数导致从inputstream读不到信息，所以可能用不了这个方法。理论应该可以调整struts配置使不影响，但请自己去研究。
	 * 调用本方法之前不能调用req.getParameter("key");这种方法，否则会导致request取不到输入流。
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParamStream(
			final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		try {
			String notifyStr = new String(IOUtils.toByteArray(request
					.getInputStream()), DemoBase.encoding);
			LogUtil.writeLog("收到通知报文：" + notifyStr);
			String[] kvs = notifyStr.split("&");
			for (String kv : kvs) {
				String[] tmp = kv.split("=");
				if (tmp.length >= 2) {
					String key = tmp[0];
					String value = URLDecoder.decode(tmp[1], DemoBase.encoding);
					res.put(key, value);
				}
			}
		} catch (UnsupportedEncodingException e) {
			LogUtil.writeLog("getAllRequestParamStream.UnsupportedEncodingException error: "
					+ e.getClass() + ":" + e.getMessage());
		} catch (IOException e) {
			LogUtil.writeLog("getAllRequestParamStream.IOException error: "
					+ e.getClass() + ":" + e.getMessage());
		}
		return res;
	}
	
	
	public static Map doQueryUnpayResutl(WithdrawRecord req)
			throws ServletException, IOException {
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		String orderId = req.getApplyNumber();
		String txnTime = req.getWithdrawDate();
		
		Map<String, String> data = new HashMap<String, String>();
		
		/***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
		data.put("version", DemoBase.version);                 //版本号
		data.put("encoding", DemoBase.encoding);               //字符集编码 可以使用UTF-8,GBK两种方式
		data.put("signMethod", SDKConfig.getConfig().getSignMethod()); //签名方法
		data.put("txnType", "00");                             //交易类型 00-默认
		data.put("txnSubType", "00");                          //交易子类型  默认00
		data.put("bizType", "000401");                         //业务类型 代付
		
		/***商户接入参数***/
		data.put("merId", merId);                  			   //商户号码，请改成自己申请的商户号或者open上注册得来的777商户号测试
		data.put("accessType", "0");                           //接入类型，商户接入固定填0，不需修改
		
		/***要调通交易以下字段必须修改***/
		data.put("orderId", orderId);                 //****商户订单号，每次发交易测试需修改为被查询的交易的订单号
		data.put("txnTime", txnTime);                 //****订单发送时间，每次发交易测试需修改为被查询的交易的订单发送时间

		/**请求参数设置完毕，以下对请求参数进行签名并发送http post请求，接收同步应答报文------------->**/
		
		Map<String, String> reqData = AcpService.sign(data,DemoBase.encoding);			        //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
		String url = SDKConfig.getConfig().getSingleQueryUrl();										//交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.singleQueryUrl
		Map<String, String> rspData = AcpService.post(reqData,url,DemoBase.encoding);     //发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
		
		/**对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考------------->**/
		
		//应答码规范参考open.unionpay.com帮助中心 下载  产品接口规范  《平台接入接口规范-第5部分-附录》
		if(!rspData.isEmpty()){
			if(AcpService.validate(rspData, DemoBase.encoding)){
				LogUtil.writeLog("验证签名成功");
				if(("00").equals(rspData.get("respCode"))){//如果查询交易成功
					String origRespCode = rspData.get("origRespCode");
					//处理被查询交易的应答码逻辑
					if(("00").equals(origRespCode) ||("A6").equals(origRespCode)){
						map.put(1, "交易成功！");
						//TODO
					}else if(("03").equals(origRespCode)||
							 ("04").equals(origRespCode)||
							 ("05").equals(origRespCode)||
							 ("01").equals(origRespCode)||
							 ("12").equals(origRespCode)||
							 ("34").equals(origRespCode)||
							 ("60").equals(origRespCode)){
						//订单处理中或交易状态未明，需稍后发起交易状态查询交易 【如果最终尚未确定交易是否成功请以对账文件为准】
						
						//TODO
					}else{
						map.put(0, "交易失败！【code = '"+origRespCode+"'】");
						//其他应答码为交易失败
						//TODO
					}
				}else if(("34").equals(rspData.get("respCode"))){
					//订单不存在，可认为交易状态未明，需要稍后发起交易状态查询，或依据对账结果为准
					map.put(0, "交易失败！【code = '"+rspData.get("respCode")+"'.订单不存在!】");
				}else{//查询交易本身失败，如应答码10/11检查查询报文是否正确
					//TODO
					map.put(0, "交易失败！【code = '"+rspData.get("respCode")+"'.应答码报文错误!】");
				}
			}else{
				LogUtil.writeErrorLog("验证签名失败");
				//TODO 检查验证签名失败的原因
				map.put(0, "交易失败！【code = '"+rspData.get("respCode")+"'.验证签名失败!】");
			}
		}else{
			//未返回正确的http状态
			LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
			map.put(0, "交易失败！【code = '"+rspData.get("respCode")+"'.未获取到返回报文或返回http状态码非200!】");
		}
			
		String reqMessage = DemoBase.genHtmlResult(reqData);
		String rspMessage = DemoBase.genHtmlResult(rspData);
		return map;
	}
}
