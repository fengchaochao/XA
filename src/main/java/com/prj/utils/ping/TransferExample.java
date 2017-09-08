/* *

 * Ping++ Server SDK
 * 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可根据自己网站需求按照技术文档编写, 并非一定要使用该代码。
 * 接入企业付款流程参考开发者中心：https://www.pingxx.com/docs/server/transfer ，文档可筛选后端语言和接入渠道。
 * 该代码仅供学习和研究 Ping++ SDK 使用，仅供参考。
 */
package com.prj.utils.ping;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Transfer;
import com.pingplusplus.model.TransferCollection;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * 该实例演示如何使用 Ping++ 进行企业转账。
 * 
 * 开发者需要填写 apiKey ，openid 和 appId ,
 * 
 * apiKey 有 TestKey 和 LiveKey 两种。
 * 
 * TestKey 模式下不会产生真实的交易。
 * 
 * openid 是发送红包的对象在公共平台下的 openid ,获得 openid
 * 的方法可以参考微信文档：http://mp.weixin.qq.com/wiki
 * /17/c0f37d5704f0b64713d5d2c37b468d75.html
 * 
 */
public class TransferExample {
	public static String appId = PayConfiger.appId;
	public static String apiKey = PayConfiger.apiKey;

	/**
	 * 接收者的 openid
	 */
	public static String openid = "oIf361GL38CYQUMOzcXUQonGeF18"; // 用户在商户微信公众号下的唯一标识，获取方式可参考
																	// WxPubOAuthExample.java
	/**
	 * 
	 * @param channel	支付类型wx_pub、alipay
	 * @param payType	付款类型，转账到个人用户为 b2c，转账到企业用户为 b2b（微信公众号wx_pub 的企业付款，仅支持 b2c）。
	 * @param amount	金额ping++以分为单位，例如 : 100 = 1RMB
	 * @param description	转账描述
	 * @param realName	真实姓名,转账时必须强制校验
	 * @param recipient	收款账户,支付宝为手机号，微信为对应公众号的openid
	 * @return	Transfer
	 * @throws RateLimitException 
	 * @throws ChannelException 
	 * @throws APIException 
	 * @throws APIConnectionException 
	 * @throws InvalidRequestException 
	 * @throws AuthenticationException 
	 * @createBy Fengc 2017-5-26 14:21:33
	 */
	public static Transfer getTranfer(String channel,
			String payType, Double amount, String description, String realName,
			String recipient,String orderNo) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException, RateLimitException {

		Pingpp.apiKey = apiKey;
		TransferExample tr = new TransferExample();
		Pingpp.privateKey = PayConfiger.privateKey;
		return tr.create(channel, payType, amount, description,
				realName, recipient,orderNo);
	}

	/**
	 * 创建企业转账
	 * 
	 * 创建企业转账需要传递一个 map 给 Transfer.create(); map
	 * 填写的具体介绍可以参考：https://www.pingxx.com/api#api-t-new
	 * 
	 * @return
	 * @throws RateLimitException 
	 * @throws ChannelException 
	 * @throws APIException 
	 * @throws APIConnectionException 
	 * @throws InvalidRequestException 
	 * @throws AuthenticationException 
	 */
	public Transfer create(String channel, String payType,
			Double amount, String description, String realName, String recipient,String orderNo) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException, RateLimitException {
		Transfer transfer = null;

		/*String orderNo = new SimpleDateFormat("yyyyMMddhhmmss")
				.format(new Date());*/
		Map<String, Object> transferMap = new HashMap<String, Object>();
		transferMap.put("channel", channel);
		// 目前支持
		// 支付宝：alipay，银联：unionpay，微信公众号：wx_pub，通联：allinpay，京东：jdpay
		// 付款使用的商户内部订单号。
		// wx_pub 规定为 1 ~ 50 位不能重复的数字字母组合;
		// alipay 为 1 ~ 64 位不能重复的数字字母组合;
		// unionpay 为 1 ~ 16 位的纯数字;
		// jdpay 限长 1 ~ 64 位不能重复的数字字母组合;
		// allinpay 限长 20 ~ 40 位不能重复的数字字母组合，必须以签约的通联的商户号开头（建议组合格式：通联商户号 + 时间戳 +
		// 固定位数顺序流水号，不包含+号）
	

		transferMap.put("order_no", orderNo);
		transferMap.put("amount", amount); // 付款金额，相关渠道的限额，请查看
										// https://help.pingxx.com/article/133366/
										// 。单位为对应币种的最小货币单位，例如：人民币为分。
		transferMap.put("type", payType); 
		transferMap.put("currency", "cny");
		transferMap.put("recipient", recipient); // 接收者

		// 备注信息。
		// 渠道为 unionpay 时，最多 99 个 Unicode 字符；
		// 渠道为 wx_pub 时，最多 99 个英文和数字的组合或最多 33 个中文字符，不可以包含特殊字符；
		// 渠道为 alipay 时，最多 100 个 Unicode 字符。
		// 渠道为 jdpay 最多100个 Unicode 字符。
		// 渠道为 allinpay 最多30个 Unicode 字符
		transferMap.put("description", description);

		transferMap.put("extra", channelExtra(channel,realName));

		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appId);
		transferMap.put("app", app);
		transfer = Transfer.create(transferMap);
		System.out.println(transfer);
		
		return transfer;
	}

	/**
	 * 根据 ID 查询
	 * 
	 * 根据 ID 查询企业转账记录。 参考文档：https://www.pingxx.com/api#api-t-inquiry
	 * 
	 * @param id
	 */
	public void retrieve(String id) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			Transfer transfer = Transfer.retrieve(id, param);
			System.out.println(transfer);
		} catch (AuthenticationException e) {
			System.out.println("AuthenticationException");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			System.out.println("InvalidRequestException");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (APIConnectionException e) {
			System.out.println("APIConnectionException");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (APIException e) {
			System.out.println("APIException");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ChannelException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (RateLimitException e) {
			System.out.println("RateLimitException");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 批量查询
	 * 
	 * 批量查询企业转账记录，默认一次查询 10 条，用户可以使用 limit 自定义查询数目，但是最多不超过 100 条。
	 */
	public void list() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("limit", 3);
		param.put("app[id]", appId);

		try {
			TransferCollection transferCollection = Transfer.list(param);
			System.out.println(transferCollection);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			e.printStackTrace();
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		} catch (ChannelException e) {
			e.printStackTrace();
		} catch (RateLimitException e) {
			e.printStackTrace();
		}
	}


	private Map<String, Object> channelExtra(String channel,String realName) {
		Map<String, Object> extra = new HashMap<>();

		switch (channel) {
		case "alipay":
			extra = alipayExtra(realName);
			break;
		case "wx_pub":
			extra = wxPubExtra(realName);
			break;
		case "unionpay":
			extra = unionpayExtra();
			break;
		case "allinpay":
			extra = allinpayExtra();
			break;
		case "jdpay":
			extra = jdpayExtra();
			break;
		}

		return extra;
	}

	private Map<String, Object> alipayExtra(String realName) {
		Map<String, Object> extra = new HashMap<>();
		// 必须，收款人姓名，1~50位。
		extra.put("recipient_name", realName);

		// 可选，收款方账户类型。可取值：1、 ALIPAY_USERID ：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。
		// 2、 ALIPAY_LOGONID （默认值）：支付宝登录号，支持邮箱和手机号格式。
		extra.put("recipient_account_type", "ALIPAY_LOGONID");

		return extra;
	}

	private Map<String, Object> wxPubExtra(String realName) {
		Map<String, Object> extra = new HashMap<>();
		// 可选，收款人姓名。当该参数为空，则不校验收款人姓名。
		extra.put("user_name", realName);

		// 可选，是否强制校验收款人姓名。仅当 user_name 参数不为空时该参数生效。
		extra.put("force_check", true);

		return extra;
	}

	private Map<String, Object> unionpayExtra() {
		Map<String, Object> extra = new HashMap<>();
		// 必须，1~32位，收款人银行卡号或者存折号。
		extra.put("card_number", "6228480402564890011");

		// 必须，1~100位，收款人姓名。
		extra.put("user_name", "张三");

		/**
		 * open_bank_code 和 open_bank 两个参数必传一个，建议使用 open_bank_code ，若都传参则优先使用
		 * open_bank_code 读取规则；prov 和 city 均为可选参数，如果不传参，则使用默认值 "上海" 给渠道接口。
		 */

		// 条件可选，4位，开户银行编号，详情请参考
		// 企业付款（银行卡）银行编号说明：https://www.pingxx.com/api#%E9%93%B6%E8%A1%8C%E7%BC%96%E5%8F%B7%E8%AF%B4%E6%98%8E。
		extra.put("open_bank_code", "0103");

		// 条件可选，1~50位，开户银行，详情请参考
		// 企业付款（银行卡）银行编号说明：https://www.pingxx.com/api#%E9%93%B6%E8%A1%8C%E7%BC%96%E5%8F%B7%E8%AF%B4%E6%98%8E。
		extra.put("open_bank", "农业银行");

		// 可选，1～20位，省份。
		// extra.put("prov", "上海");

		// 可选，1～40位，城市。
		// extra.put("city", "上海");

		// 可选，1～80位，开户支行名称。
		// extra.put("sub_bank", "上海陆家嘴支行");

		return extra;
	}

	private Map<String, Object> allinpayExtra() {
		Map<String, Object> extra = new HashMap<>();
		// 必须，1~32位，收款人银行卡号或者存折号。
		extra.put("card_number", "6228480402564890011");

		// 必须，1~100位，收款人姓名。
		extra.put("user_name", "张三");

		// 必须，4位，开户银行编号，详情请参考
		// 企业付款（银行卡）银行编号说明：https://www.pingxx.com/api#%E9%93%B6%E8%A1%8C%E7%BC%96%E5%8F%B7%E8%AF%B4%E6%98%8E。
		extra.put("open_bank_code", "0103");

		// 可选，5位，业务代码，根据通联业务人员提供，不填使用通联提供默认值09900。
		// extra.put("business_code", "09900");

		// 可选，1位，银行卡号类型，0：银行卡、1：存折，不填默认使用银行卡。
		// extra.put("card_type", 0);

		return extra;
	}

	private Map<String, Object> jdpayExtra() {
		Map<String, Object> extra = new HashMap<>();
		// 必须，1~32位，收款人银行卡号或者存折号。
		extra.put("card_number", "6228480402564890011");

		// 必须，1~100位，收款人姓名。
		extra.put("user_name", "张三");

		// 必须，4位，开户银行编号，详情请参考
		// 企业付款（银行卡）银行编号说明：https://www.pingxx.com/api#%E9%93%B6%E8%A1%8C%E7%BC%96%E5%8F%B7%E8%AF%B4%E6%98%8E。
		extra.put("open_bank_code", "0103");

		return extra;
	}

	/**
	 * 
	 * @param channel	支付类型wx_pub、alipay
	 * @param payType	付款类型，转账到个人用户为 b2c，转账到企业用户为 b2b（微信公众号wx_pub 的企业付款，仅支持 b2c）。
	 * @param amount	金额ping++以分为单位，例如 : 100 = 1RMB
	 * @param description	转账描述
	 * @param realName	真实姓名,转账时必须强制校验
	 * @param recipient	收款账户,支付宝为手机号，微信为对应公众号的openid
	 * @return	Transfer
	 * @createBy Fengc 2017-5-26 14:21:33
	 */
	public static void main(String[] args) {
		Transfer transfer=new Transfer();
		String str=null;
		 try {
		  transfer=	getTranfer("alipay", "b2c", 10.0, "测", "梁建芳", "18220682639", "SSS1");

		 } catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | APIException | ChannelException
				| RateLimitException e) {
			 str=e.getMessage();
			e.printStackTrace();
		}
		 
		 System.out.println(str);
	}
}
