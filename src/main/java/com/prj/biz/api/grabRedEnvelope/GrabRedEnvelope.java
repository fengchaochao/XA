package com.prj.biz.api.grabRedEnvelope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tutorial.redis.RedisUtil;

import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.bean.redenveloperecord.RedEnvelopeRecord;
import com.prj.biz.bean.sysuser.SysUser;
import com.prj.biz.service.redenvelope.RedEnvelopeService;
import com.prj.biz.service.redenveloperecord.RedEnvelopeRecordService;
import com.prj.biz.service.sysuser.SysUserService;
import com.prj.utils.JsonUtils;
import com.prj.utils.ObjectUtil;

/**
 * @author: Fengc
 * @date:2017-8-7 下午5:04:54
 * @version :0.0.1
 * @dis:App端抢红包
 */

@Controller
@RequestMapping("/api")
public class GrabRedEnvelope {
	@Resource
	private RedEnvelopeService redEnvelopeService;

	@Resource
	private RedEnvelopeRecordService envelopeRecordService;

	@Resource
	private SysUserService userService;

	/**
	 * 
	 * @param request
	 * @param response
	 * @param receiver
	 * @param redEnvelopeId
	 * @return
	 */
	@RequestMapping(value = "/grabRedEnvelope")
	@ResponseBody
	public synchronized String lockedCustomerList(HttpServletRequest request,
			HttpServletResponse response, String receiver, String redEnvelopeId) {

		String msg = "";

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String isExitsKey = "RedPacketIsExits" + redEnvelopeId;
			String mainRdKey = "RedPacket" + redEnvelopeId;
			if (!RedisUtil.getJedis().sismember(isExitsKey, receiver)) {

				RedEnvelopeRecord redEnvelopeRecord;
				try {
					redEnvelopeRecord = (RedEnvelopeRecord) ObjectUtil.bytesToObject((RedisUtil.getJedis().lpop(mainRdKey.getBytes())));
					if (null != redEnvelopeRecord) {
						// 红包记录更新

						SysUser user = userService.doGetById(receiver);

						String userState = user.getUserState();
						redEnvelopeRecord.setReceiverType(userState);

						redEnvelopeRecord.setReceiver(receiver);
						envelopeRecordService.doModById(redEnvelopeRecord);

						RedisUtil.getJedis().sadd(isExitsKey, receiver); // **
						msg = "恭喜您已经获得" + redEnvelopeRecord.getPrice() + "元红包!";
					} else {
						msg = "本轮红包已经抢完,请等待下轮红包!";
					}
				} catch (Exception e) {
					msg = "系统异常！";
				}

			} else {
				msg = "你已经领取过红包了！";
			}

			map.put("code", "1");
			map.put("data", msg);
			map.put("msg", "抢红包失败成功！");
		} catch (Exception e) {
			map.put("code", "0");
			map.put("data", msg);
			map.put("msg", "抢红包失败！");
		}

		return JsonUtils.toJson(map);
	}
}
