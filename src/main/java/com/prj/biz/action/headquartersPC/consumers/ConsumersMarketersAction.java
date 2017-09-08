/**
 * Copyright© 2003-2013 天尔软件, All Rights Reserved <br/>
 * 描述: 业务文件 <br/>
 * @author Liang
 * @date 2017-07-18
 * @version 1.0
 */
package com.prj.biz.action.headquartersPC.consumers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prj.biz.action._base.BaseAction;
import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.service.businessinformation.BusinessInformationService;
import com.prj.biz.service.consumers.ConsumersService;
import com.prj.biz.service.consumersaccount.ConsumersAccountService;
import com.prj.core.bean.resp.RespBean;
import com.prj.core.bean.resp.RespPagination;



/** 
 * 描述: 消费商 Action 类<br>
 * @author Liang
 * @date 2017-07-18
 */
@RestController
@RequestMapping("/headquarters/consumersMarketers")
public class ConsumersMarketersAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	
	@Resource
	private ConsumersService consumersService;
	
	@Resource
	private ConsumersAccountService consumersAccountService;
	
	@Resource
	private BusinessInformationService businessInformationService;

	/**
	 * 描述: 进入列表显示页面
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doEnConsumersMarketersList")
    public ModelAndView doEnConsumersMarketersListAction(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return new ModelAndView("/headquarters/consumersMarketers/consumersMarketersList");
	}
	
	/**
	 * 描述: 分页查询信息
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doReadConsumersMarketersList")
	@ResponseBody
	public RespBean<RespPagination<Consumers>> doReadConsumersMarketersList(Consumers consumers) throws Exception{
		RespBean<RespPagination<Consumers>> respBean = new RespBean<RespPagination<Consumers>>();
		RespPagination<Consumers> respPagination = new RespPagination<Consumers>();
		Integer total = consumersService.doGetTotal(consumers);
		consumers.setIsXfconsumers("1");
		List<Consumers> consumersList = consumersService.doGetList(consumers);
		if(consumersList.size()>0){
			for (int i = 0; i < consumersList.size(); i++) {
				//判断是否为消费商
				if("1".equals(consumersList.get(i).getIsXfconsumers())){
					//锁定用户数
					ConsumersAccount consumersAccount=new ConsumersAccount();
					consumersAccount.setBusinessInformationId(consumersList.get(i).getId());
					List<ConsumersAccount> consumersAccountList=consumersAccountService.doGetList(consumersAccount);
					consumersList.get(i).setLocalUser(consumersAccountList.size());
					//推荐商家
					BusinessInformation businessInformation=new BusinessInformation();
					businessInformation.setFounder(consumersList.get(i).getId());
					List<BusinessInformation> businessInformationList=businessInformationService.doGetList(businessInformation);
					consumersList.get(i).setRecommendedBusiness(businessInformationList.size());
				}
				if(!StringUtils.isEmpty(consumersList.get(i).getProvinces())&&!StringUtils.isEmpty(consumersList.get(i).getCity())&&!StringUtils.isEmpty(consumersList.get(i).getArea())&&!StringUtils.isEmpty(consumersList.get(i).getAddress())){
					String address=consumersList.get(i).getProvinces()+"省"+consumersList.get(i).getCity()+consumersList.get(i).getArea()+consumersList.get(i).getAddress();
					consumersList.get(i).setAddress(address);
				}
			}
		}
		respPagination.setTotal(total);
		respPagination.setRows(consumersList);
		respBean.setBody(respPagination);
		return respBean;
	}
	/**
	 * 描述: 进入查看页面
	 * @auther Liang
	 * @date 2017-07-18
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnConsumersEdit")
	public ModelAndView doEnConsumersEdit(String consumersId,String flag) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Consumers consumers = consumersService.doGetById(consumersId);
		String[] idNoPhoto = new String[]{};
		if(!StringUtils.isEmpty(consumers.getIdPhoto())){
			idNoPhoto = consumers.getIdPhoto().split(",");
		}
		//判断是否为消费商
		if("1".equals(consumers.getIsXfconsumers())){
			//锁定用户数
			ConsumersAccount consumersAccount=new ConsumersAccount();
			consumersAccount.setBusinessInformationId(consumersId);
			List<ConsumersAccount> consumersAccountList=consumersAccountService.doGetList(consumersAccount);
			consumers.setLocalUser(consumersAccountList.size());
			//推荐商家
			BusinessInformation businessInformation=new BusinessInformation();
			businessInformation.setFounder(consumersId);
			List<BusinessInformation> businessInformationList=businessInformationService.doGetList(businessInformation);
			consumers.setRecommendedBusiness(businessInformationList.size());
		}
		
		//消费者账号
		ConsumersAccount consumersAccount=new ConsumersAccount();
		consumersAccount.setConsumersId(consumersId);
		List<ConsumersAccount> consumersAccountList=consumersAccountService.doGetList(consumersAccount);
		model.put("consumers", consumers);
		model.put("idNoPhoto", idNoPhoto);
		model.put("consumersAccountList", consumersAccountList);
		model.put("flag", flag);
		return new ModelAndView("/headquarters/consumersMarketers/consumersEdit",model);
	}
}
