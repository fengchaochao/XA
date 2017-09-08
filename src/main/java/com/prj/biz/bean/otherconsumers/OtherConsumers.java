package com.prj.biz.bean.otherconsumers;

import com.prj.biz.bean._base.BaseEntity;

/** 
 * 描述: 其他消费者 视图 实体类<br>
 * @author Liang
 * @date 2017-07-31
 * 
 */
public class OtherConsumers extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    //  
    private String id;
    // 订单编号 
    private String orderNo;
    // 消费者账号id 
    private String cunsumersId;
    // 交易数量 
    private String transactionNum;
    // 金额 
    private String totalPrice;
    // 商品规格id 
    private String goodsUnitId;
    // 商品抽成 
    private String goodsAs;
    // 返利 
    private String rebate;
    // 交易时间 
    private String createDate;
    //  
    private String businessId;
    // 用户账号 
    private String userAccount;
    // 用户类型1-微信用户 2-支付宝用户 
    private String userType;

    //起始日期（显示用）
    private String beginDate;
    //结束日期（显示用）
    private String endDate;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    public String getCunsumersId() {
        return cunsumersId;
    }
    public void setCunsumersId(String cunsumersId) {
        this.cunsumersId = cunsumersId;
    }


    public String getTransactionNum() {
        return transactionNum;
    }
    public void setTransactionNum(String transactionNum) {
        this.transactionNum = transactionNum;
    }


    public String getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getGoodsUnitId() {
        return goodsUnitId;
    }
    public void setGoodsUnitId(String goodsUnitId) {
        this.goodsUnitId = goodsUnitId;
    }


    public String getGoodsAs() {
        return goodsAs;
    }
    public void setGoodsAs(String goodsAs) {
        this.goodsAs = goodsAs;
    }


    public String getRebate() {
        return rebate;
    }
    public void setRebate(String rebate) {
        this.rebate = rebate;
    }


    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


    public String getBusinessId() {
        return businessId;
    }
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }


    public String getUserAccount() {
        return userAccount;
    }
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }


    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
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

}
