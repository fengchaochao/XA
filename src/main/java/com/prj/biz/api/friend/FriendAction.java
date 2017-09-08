/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-08-26
 * @version 1.0
 */
package com.prj.biz.api.friend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.friend.Friend;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.friend.FriendService;
import com.prj.biz.action._base.BaseAction;
import com.prj.biz.api.easemob.server.example.test.PropKit;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;
import com.prj.utils.UfdmDateUtil;



/** 
 * 描述: 好友列表 Action 类<br>
 * @author Liang
 * @date 2017-08-26
 */
@RestController
@RequestMapping("/api")
public class FriendAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private FriendService friendService;
	@Resource
	private ConsumersService consumersService;

	
	/**
	 * 描述: 好友列表
	 * @auther Liang
	 * @date 2017-08-26
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadFriendList")
	@ResponseBody
	public RespBean<RespPagination<Friend>> doReadFriendList(Friend friend) throws Exception{
		RespBean<RespPagination<Friend>> respBean = new RespBean<RespPagination<Friend>>();
		RespPagination<Friend> respPagination = new RespPagination<Friend>();
		friend.setStatus("2");//已同意状态
		Integer total = friendService.doGetTotal(friend);
		List<Friend> friendList = friendService.doGetList(friend);
		respPagination.setTotal(total);
		respPagination.setRows(friendList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 新好友个数
	 */
	@RequestMapping("/doEnFriendNum")
	public RespBean<Map<String, Object>> doEnFriendNum(Friend friend) throws Exception{
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		friend.setStatus("1");//申请状态
		Integer friendNum = friendService.doGetTotal(friend);
		map.put("friendNum", friendNum);
		respBean.setBody(map);
		return respBean;
	}
	
	/**
	 * 描述: 新好友提醒页面
	 * @auther Liang
	 * @date 2017-08-26
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnFriendNewInfo")
	public RespBean<RespPagination<Friend>> doEnFriendNewInfo(Friend friend) throws Exception{
		RespBean<RespPagination<Friend>> respBean = new RespBean<RespPagination<Friend>>();
		RespPagination<Friend> respPagination = new RespPagination<Friend>();
		friend.setStatus("1");//申请状态
		friend.setOrder("desc");
		friend.setOrderName("creattime");
		Integer total = friendService.doGetTotal(friend);
		List<Friend> newFriendList = friendService.selectListNewFriend(friend);
		respPagination.setTotal(total);
		respPagination.setRows(newFriendList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 描述: 同意好友或者拒绝好友
	 * @auther Liang
	 * @date 2017-08-26
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEditFriend")
	public RespBean<Friend> doEditFriend(Friend friend) throws Exception{
		RespBean<Friend> respBean = new RespBean<Friend>();
		if(friend.getStatus().equals("2")){//同意好友申请
			Friend ff = friendService.doGetById(friend.getId());
			//同意好友申请时，需要好友用户也添加一次好友
			if(ff != null){
				Friend f = new Friend();
				f.setZuserId(ff.getBuserId());
				f.setStatus("2");
				f.setBuserId(ff.getZuserId());
				
				List<Friend> list = friendService.doGetList(f);
				if(list.size() > 0 ){
					friendService.doSave(f);
					
					//环信添加好友
					Consumers consumersz = consumersService.doGetById(ff.getZuserId());
					Consumers consumersb = consumersService.doGetById(ff.getBuserId());
					if(consumersz != null ){
						if(consumersb != null){
							PropKit.addFriend(consumersz.getPhone(), consumersb.getPhone());
						}
					}
				}
				friendService.doModById(friend);
				
			}
			
		}else if(friend.getStatus().equals("3")){//拒绝好友申请
			friendService.doModById(friend);
			respBean.setBody(friend);
		}
		
		return respBean;
	}
	
	/**
	 * 描述: 添加好友
	 * @auther Liang
	 * @date 2017-08-26
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doAddFriend")
	public RespBean<Friend> doAddFriend(Friend friend) throws Exception{
		RespBean<Friend> respBean = new RespBean<Friend>();
		//需判断本次添加的用户是否已添加过
		Friend ff = new Friend();
		ff.setZuserId(friend.getZuserId());
		ff.setBuserId(friend.getBuserId());
		List<Friend> list  =  friendService.doGetList(ff);
		if(list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getStatus().equals("3")){
					friendService.doSave(friend);
					respBean.setBody(friend);
				}else {
					Friend f = new Friend();
					f.setId(list.get(i).getId());
					f.setCreattime(UfdmDateUtil.getCurrentDateTime());
					friendService.doModById(f);
				}
			}
		}else {
			friendService.doSave(friend);
			respBean.setBody(friend);
		}
		return respBean;
	}
	
	/**
	 * 按照名称搜索用户数据
	 */
	@RequestMapping("/doEnFriendSelectName")
	public RespBean<RespPagination<Consumers>> doEnFriendSelectName(Consumers consumers,String sellerId) throws Exception{
		RespBean<RespPagination<Consumers>> respBean = new RespBean<RespPagination<Consumers>>();
		RespPagination<Consumers> respPagination = new RespPagination<Consumers>();
		List<Consumers> consumersList = consumersService.doGetList(consumers);
		//好友搜索拒绝搜到自己
		if(consumersList.size() > 0){
			for (int i = 0; i < consumersList.size(); i++) {
				if(consumersList.get(i).getId().equals(sellerId)){
					consumersList.remove(consumersList.get(i));
				}
			}
		}
		respPagination.setRows(consumersList);
		respBean.setBody(respPagination);
		return respBean;
	}
	
	/**
	 * 好友详情
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AppFriendInfo")
	@ResponseBody
	public RespBean<Map<String, Object>> AppFriendInfo(Consumers con,String zuserId) throws Exception {
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		Consumers consumers = consumersService.selectByIdInfo(con);
		if(consumers == null){
			Consumers consumer = new Consumers();
			map.put("consumers", consumer);
		}else {
			//好友id
			String id = consumers.getId();
			Friend friend = new Friend();
			friend.setZuserId(zuserId);
			friend.setBuserId(id);
			friend.setStatus("2");
			List<Friend> friendList = friendService.doGetList(friend);
			if(friendList.size() > 0 ){
				consumers.setStatus("1");
			}else {
				consumers.setStatus("0");
			}
			consumers.setFriendName(consumers.getNickName());
			map.put("consumers", consumers);
		}
		
		respBean.setBody(map);
		return respBean;
	}
	
	/**
	 * 描述: 删除
	 * @auther Liang
	 * @date 2017-08-26
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDelFriend")
	@ResponseBody
	public RespBean<String> doDelFriendAction(String friendIds) throws Exception{
		RespBean<String> respBean = new RespBean<String>();
		friendService.doRmByIds(friendIds);
		respBean.setBody("删除成功");
		return respBean;
	}
	
}
