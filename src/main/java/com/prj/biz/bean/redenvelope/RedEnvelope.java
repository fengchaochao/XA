package com.prj.biz.bean.redenvelope;

import com.prj.biz.bean._base.BaseEntity;

/** 
 * 描述: 红包 实体类<br>
 * @author Liang
 * @date 2017-07-26
 * 
 */
public class RedEnvelope extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    //  
    private String id;
    // 发布日期 
    private String createDate;
    // 发布时间 
    private String createTime;
    // 总金额 
    private String totalPrice;
    // 红包个数 
    private String number;
    // 红包状态 0-待发布 1-已发布 2-已领取 3-失效
    private String redEnvelopeState;
    // 红包类型0-随机红包1-固定红包 
    private String redEnvelopeType;
    // 最小金额 
    private String minPrice;
    // 最大金额 
    private String maxPrice;
    // 创建人ID
    private String founderId;
    //红包失效时间（分钟为单位）
    private String expirationDate;

    /***查询用**/
    //起始日期
    private String beginDate;
    //结束日期
    private String endDate;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }


    public String getRedEnvelopeState() {
        return redEnvelopeState;
    }
    public void setRedEnvelopeState(String redEnvelopeState) {
        this.redEnvelopeState = redEnvelopeState;
    }


    public String getRedEnvelopeType() {
        return redEnvelopeType;
    }
    public void setRedEnvelopeType(String redEnvelopeType) {
        this.redEnvelopeType = redEnvelopeType;
    }
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getFounderId() {
		return founderId;
	}
	public void setFounderId(String founderId) {
		this.founderId = founderId;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	

}
