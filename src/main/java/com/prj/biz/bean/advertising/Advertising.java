package com.prj.biz.bean.advertising;

import com.prj.biz.bean._base.BaseEntity;

/** 
 * 描述: 广告表 实体类<br>
 * @author Liang
 * @date 2017-07-20
 * 
 */
public class Advertising extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    //  
    private String id;
    // 广告标题 
    private String headline;
    // 发布时间 
    private String createDate;
    // 内容 
    private String context;
    // 发布人 
    private String userId;
    // 审核状态 1-待审核 2-被退回 3-审核通过 4-已发布 
    private String checkState;
    // 商家区域
    private String releaseRange;
    //商家区域(显示用)
    private String areaName;
    //商家姓名(显示用)
    private String vendorName;

    
    public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getHeadline() {
        return headline;
    }
    public void setHeadline(String headline) {
        this.headline = headline;
    }


    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


    public String getContext() {
        return context;
    }
    public void setContext(String context) {
        this.context = context;
    }


    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getCheckState() {
        return checkState;
    }
    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }


    public String getReleaseRange() {
        return releaseRange;
    }
    public void setReleaseRange(String releaseRange) {
        this.releaseRange = releaseRange;
    }

}
