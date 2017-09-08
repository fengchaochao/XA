package com.prj.biz.bean.sysuser;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.prj.biz.bean._base.BaseEntity;
import com.prj.biz.bean.permission.PerResource;
import com.prj.biz.bean.permission.PerRole;


/**
 * 描述: 系统用户<br>
 * @author 胡义振
 * @date 2013-08-06
 */
public class SysUser extends BaseEntity 
{
	private static final long serialVersionUID = -6312563813337117267L;
	
	// ID
	private String			    id;
	// 登录名
	private String				loginName;
	// 老密码
	private String              oldLoginPass;
	// 登录密码
	private String				loginPass;
	// 确认密码
	private String              repeatLoginPass;
	// 真实姓名
	private String				realName;
	// 手机号
	private String              mobile;
	// 电话号码
	private String              telephone;
	// 电子邮件
	private String              email;
	// 用户状态0-正常1-停用
	private String				userStatus;
	// 建立时间
	private Date				createTime;
	
	// 用户资源
	private List<PerResource>	hxResourceList;
	// 用户菜单资源
	private List<PerResource>   hxResourceMenuList;
	// 用户菜单资源
	private JSONArray           hxResourceMenuListJson;
	// 用户角色
	private List<PerRole>		hxRoleList;
	// 验证码
	private String				verifyCode;
	// 用户分配的角色ID集
	private String		        hxRoleIds;
	//用户部门id
	private String              depId;   
	//用户部门名称
	private String              depName;
	// 记住密码
	private boolean             rememberMe;
	//个人头像
	private String              personPhoto;
	
	// 代理商id/商家id
	private String              merchantsId;
	// 总部/代理商员工对应的创建人ID(即老板)
	private String              creater;
	
	// 用户类型0-总部 1-代理商 2-商家  3-消费商 4-消费者 5-消费者账户 6-代理商员工
	private String              userState;
	
	//商家id(作废)
	private String              businessInformationId;
	//账户余额
	private String              accountBalance;
	//商家区域id
	private String              areaId;
	//商家名称/消费者昵称/消费商昵称
	private String              verName;
	//二维码
	private String              qrcode;
	
	//手机设备号(极光id)
	private String deviceNumber;
	
	
	public String getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getBusinessInformationId() {
		return businessInformationId;
	}
	public void setBusinessInformationId(String businessInformationId) {
		this.businessInformationId = businessInformationId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getOldLoginPass() {
		return oldLoginPass;
	}
	public void setOldLoginPass(String oldLoginPass) {
		this.oldLoginPass = oldLoginPass;
	}
	public String getLoginPass() {
		return loginPass;
	}
	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}
	public String getRepeatLoginPass() {
		return repeatLoginPass;
	}
	public void setRepeatLoginPass(String repeatLoginPass) {
		this.repeatLoginPass = repeatLoginPass;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<PerResource> getHxResourceList() {
		return hxResourceList;
	}
	public void setHxResourceList(List<PerResource> hxResourceList) {
		this.hxResourceList = hxResourceList;
	}
	public List<PerResource> getHxResourceMenuList() {
		return hxResourceMenuList;
	}
	public void setHxResourceMenuList(List<PerResource> hxResourceMenuList) {
		this.hxResourceMenuList = hxResourceMenuList;
	}
	public JSONArray getHxResourceMenuListJson() {
		return hxResourceMenuListJson;
	}
	public void setHxResourceMenuListJson(JSONArray hxResourceMenuListJson) {
		this.hxResourceMenuListJson = hxResourceMenuListJson;
	}
	public List<PerRole> getHxRoleList() {
		return hxRoleList;
	}
	public void setHxRoleList(List<PerRole> hxRoleList) {
		this.hxRoleList = hxRoleList;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getHxRoleIds() {
		return hxRoleIds;
	}
	public void setHxRoleIds(String hxRoleIds) {
		this.hxRoleIds = hxRoleIds;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	
	public boolean getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	public String getMerchantsId() {
		return merchantsId;
	}
	public void setMerchantsId(String merchantsId) {
		this.merchantsId = merchantsId;
	}
	
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	
	public String getPersonPhoto() {
		return personPhoto;
	}
	public void setPersonPhoto(String personPhoto) {
		this.personPhoto = personPhoto;
	}
	
	public String getVerName() {
		return verName;
	}
	public void setVerName(String verName) {
		this.verName = verName;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	
	public String getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	@Override
	public String toString() {
		return "SysUser [id=" + id + ", loginName=" + loginName
				+ ", oldLoginPass=" + oldLoginPass + ", loginPass=" + loginPass
				+ ", repeatLoginPass=" + repeatLoginPass + ", realName="
				+ realName + ", mobile=" + mobile + ", telephone=" + telephone
				+ ", email=" + email + ", userStatus=" + userStatus
				+ ", createTime=" + createTime + ", hxResourceList="
				+ hxResourceList + ", hxResourceMenuList=" + hxResourceMenuList
				+ ", hxResourceMenuListJson=" + hxResourceMenuListJson
				+ ", hxRoleList=" + hxRoleList + ", verifyCode=" + verifyCode
				+ ", hxRoleIds=" + hxRoleIds + ", depId=" + depId
				+ ", depName=" + depName + ", rememberMe=" + rememberMe
				+ ", personPhoto=" + personPhoto + ", merchantsId="
				+ merchantsId + ", userState=" + userState
				+ ", businessInformationId=" + businessInformationId
				+ ", accountBalance=" + accountBalance + ", areaId=" + areaId
				+ ", verName=" + verName + ", qrcode=" + qrcode + "]";
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
}
