package com.prj.biz.bean.transactionrecords;

import com.prj.biz.bean._base.BaseEntity;
import com.prj.biz.bean.consumers.Consumers;
import com.prj.biz.bean.consumersaccount.ConsumersAccount;
import com.prj.biz.bean.goods.Goods;
import com.prj.biz.bean.goodsspecifications.GoodsSpecifications;

/** 
 * 描述: 交易记录表 实体类<br>
 * @author Liang
 * @date 2017-07-25
 * 
 */
public class TransactionRecords extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    //  
    private String id;
    // 订单id
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
    //商品对应的商家的id
    private String businessId;

    /***********查询用******************/
	private ConsumersAccount consumersAccount;
	
	private Consumers consumers;
	
	private GoodsSpecifications goodsSpecifications;
	
	private String orderNum;
	
	private Goods goods;
	//商品id
	private String goodsId;
	//账号id
	private String accountId;
	
	 //起始日期
    private String beginDate;
    //结束日期
    private String endDate;
    
    //商品名称
    private String goodsName;
    //商品规格名称
    private String goodsUtilName;
    //商品价格
    private String goodsPrice;
    //是否按商品统计
    private String isGoodsTotal;
    //商品图片
    private String goodsPhoto;
    //商品分类
    private String goodsType;
    //是否已评价 0-评价  1：已评价 2：已回复
    private String isEnveate;
    
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
	public ConsumersAccount getConsumersAccount() {
		return consumersAccount;
	}
	public void setConsumersAccount(ConsumersAccount consumersAccount) {
		this.consumersAccount = consumersAccount;
	}
	public Consumers getConsumers() {
		return consumers;
	}
	public void setConsumers(Consumers consumers) {
		this.consumers = consumers;
	}
	public GoodsSpecifications getGoodsSpecifications() {
		return goodsSpecifications;
	}
	public void setGoodsSpecifications(GoodsSpecifications goodsSpecifications) {
		this.goodsSpecifications = goodsSpecifications;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsUtilName() {
		return goodsUtilName;
	}
	public void setGoodsUtilName(String goodsUtilName) {
		this.goodsUtilName = goodsUtilName;
	}
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getIsGoodsTotal() {
		return isGoodsTotal;
	}
	public void setIsGoodsTotal(String isGoodsTotal) {
		this.isGoodsTotal = isGoodsTotal;
	}
	public String getGoodsPhoto() {
		return goodsPhoto;
	}
	public void setGoodsPhoto(String goodsPhoto) {
		this.goodsPhoto = goodsPhoto;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getIsEnveate() {
		return isEnveate;
	}
	public void setIsEnveate(String isEnveate) {
		this.isEnveate = isEnveate;
	}
	
}
