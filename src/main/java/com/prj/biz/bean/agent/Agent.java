package com.prj.biz.bean.agent;

import com.prj.biz.bean._base.BaseEntity;

/** 
 * 描述: 代理商客户信息 实体类<br>
 * @author Liang
 * @date 2017-07-10
 * 
 */
public class Agent extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    //  
    private String id;
    // 代理商编号 
    private String agentCode;
    //  联系电话
    private String phone;
    // 省份 
    private String provincesId;
    // 市 
    private String cityId;
    // 区 
    private String areaId;
    // 地址
    private String address;
    //经纬度
    private String jyd;
    //  钻石卡号
    private String diamondCard;
    //  代理商状态 0 正常 1 停用
    private String agentState;
    // 代理商类型 1.公司代理商；2.个人代理商  3.公众号申请
    private String agentType;
    //  公司名称
    private String companyName;
    // 组织机构代码 
    private String organizationCode;
    // 省份 
    private String companyProvincesId;
    // 市 
    private String companyCityId;
    // 区 
    private String companyAreaId;
    
    //代理级别 0-省级 1-市级 2-区级
    private String resellerLevel;
    
    //公众号申请状态 0-申请中 1-完成注册
    private String applicationState;
    
    //  公司详细地址
    private String companyAddress;
    //营业执照 
    private String businessLicensePhoto;
    //  姓名
    private String userName;
    // 身份证号 
    private String idNo;
    // 省份 
    private String userProvincesId;
    // 市 
    private String userCityId;
    // 区 
    private String userAreaId;
    //  个人代理地址
    private String userAddress;
    //  身份证照片
    private String idNoPhoto;
    //  代理商名称
    private String agentName;
    //  消费商数量上限
    private String consumersNumber;
    //创建时间
    private String createDate;
    
    //代理区域（只是查看页面做展示用）
    private String showAgentArea;
    
    /**查询用***/
    
    // 省份 
    private String provinces;
    // 市 
    private String city;
    // 区 
    private String area;
    // 省份 
    private String companyProvinces;
    // 市 
    private String companyCity;
    // 区 
    private String companyArea;
    // 省份 
    private String userProvinces;
    // 市 
    private String userCity;
    // 区 
    private String userArea;
    //代理商区域(显示用)
    private String agentArea;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getAgentCode() {
        return agentCode;
    }
    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }


    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }


    public String getDiamondCard() {
        return  diamondCard;
    }
    public void setDiamondCard(String  diamondCard) {
        this.diamondCard = diamondCard;
    }

    public String getAgentState() {
		return agentState;
	}
	public void setAgentState(String agentState) {
		this.agentState = agentState;
	}
	public String getAgentType() {
        return agentType;
    }
    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }


    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getOrganizationCode() {
        return organizationCode;
    }
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }


    public String getCompanyAddress() {
        return companyAddress;
    }
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }


    public String getBusinessLicensePhoto() {
        return businessLicensePhoto;
    }
    public void setBusinessLicensePhoto(String businessLicensePhoto) {
        this.businessLicensePhoto = businessLicensePhoto;
    }


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getIdNo() {
        return idNo;
    }
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }


    public String getUserAddress() {
        return userAddress;
    }
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }


    public String getIdNoPhoto() {
        return idNoPhoto;
    }
    public void setIdNoPhoto(String idNoPhoto) {
        this.idNoPhoto = idNoPhoto;
    }


    public String getAgentName() {
        return agentName;
    }
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
	public String getConsumersNumber() {
		return consumersNumber;
	}
	public void setConsumersNumber(String consumersNumber) {
		this.consumersNumber = consumersNumber;
	}
	public String getProvincesId() {
		return provincesId;
	}
	public void setProvincesId(String provincesId) {
		this.provincesId = provincesId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getCompanyProvincesId() {
		return companyProvincesId;
	}
	public void setCompanyProvincesId(String companyProvincesId) {
		this.companyProvincesId = companyProvincesId;
	}
	public String getCompanyCityId() {
		return companyCityId;
	}
	public void setCompanyCityId(String companyCityId) {
		this.companyCityId = companyCityId;
	}
	public String getCompanyAreaId() {
		return companyAreaId;
	}
	public void setCompanyAreaId(String companyAreaId) {
		this.companyAreaId = companyAreaId;
	}
	public String getUserProvincesId() {
		return userProvincesId;
	}
	public void setUserProvincesId(String userProvincesId) {
		this.userProvincesId = userProvincesId;
	}
	public String getUserCityId() {
		return userCityId;
	}
	public void setUserCityId(String userCityId) {
		this.userCityId = userCityId;
	}
	public String getUserAreaId() {
		return userAreaId;
	}
	public void setUserAreaId(String userAreaId) {
		this.userAreaId = userAreaId;
	}
	public String getProvinces() {
		return provinces;
	}
	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCompanyProvinces() {
		return companyProvinces;
	}
	public void setCompanyProvinces(String companyProvinces) {
		this.companyProvinces = companyProvinces;
	}
	public String getCompanyCity() {
		return companyCity;
	}
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}
	public String getCompanyArea() {
		return companyArea;
	}
	public void setCompanyArea(String companyArea) {
		this.companyArea = companyArea;
	}
	public String getUserProvinces() {
		return userProvinces;
	}
	public void setUserProvinces(String userProvinces) {
		this.userProvinces = userProvinces;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getUserArea() {
		return userArea;
	}
	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}
	public String getJyd() {
		return jyd;
	}
	public void setJyd(String jyd) {
		this.jyd = jyd;
	}
	public String getResellerLevel() {
		return resellerLevel;
	}
	public void setResellerLevel(String resellerLevel) {
		this.resellerLevel = resellerLevel;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getApplicationState() {
		return applicationState;
	}
	public void setApplicationState(String applicationState) {
		this.applicationState = applicationState;
	}
	public String getAgentArea() {
		return agentArea;
	}
	public void setAgentArea(String agentArea) {
		this.agentArea = agentArea;
	}
	public String getShowAgentArea() {
		return showAgentArea;
	}
	public void setShowAgentArea(String showAgentArea) {
		this.showAgentArea = showAgentArea;
	}
	

}
