package com.prj.biz.bean.goods;

import com.prj.biz.bean._base.BaseEntity;
import com.prj.biz.bean.goodsclassification.GoodsClassification;
import com.prj.biz.bean.sysuser.SysUser;

/** 
 * 描述: 商品信息 实体类<br>
 * @author Liang
 * @date 2017-07-13
 * 
 */
public class Goods extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    //  
    private String id;
    // 商品名称 
    private String commodityName;
    // 商品编号
    private String commodityNumber;
    // 商品分类id 
    private String commodityTypeId;
    // 商品描述 
    private String commodityDescription;
    // 商品图片 
    private String goodsPhotos;
    // 商品轮播图片 
    private String commodityImages;
    // 推广费 
    private String promotionFee;
    // 配送费 
    private String shippingFee;
    // 上传时间 
    private String createDate;
    // 商品状态0-未发布 1-待审核 2-未通过 3-已发布 4-已下架 
    private String goodsState;
    // 发布者 id
    private String publisher;
    
    //
    private GoodsClassification goodsClassification;
    //
    private SysUser sysUser;
    //商品价格（显示用）
    private String goodsPrice;
    //商品总库存（显示用）
    private String inventory;
    
    /***外键关系  查询用***/
    // 商家编号
    private String merchantsNo;
    // 商家名称
    private String merchantsName;
    // 商品分类
    private String typeName;
    //销量
    private int sales;
    //起始日期
    private String beginDate;
    //结束日期
    private String endDate;
    //商品状态
    private String goodState;
    //区域ID
    private String areaId;
    //App首页的关键字查询
    private String keySeach;
    //商家ID
    private String businessId;
    //购物车数量(APP用)
    private int shippingNum;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getCommodityName() {
        return commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }


    public String getCommodityTypeId() {
        return commodityTypeId;
    }
    public void setCommodityTypeId(String commodityTypeId) {
        this.commodityTypeId = commodityTypeId;
    }


    public String getCommodityDescription() {
        return commodityDescription;
    }
    public void setCommodityDescription(String commodityDescription) {
        this.commodityDescription = commodityDescription;
    }


    public String getCommodityImages() {
        return commodityImages;
    }
    public void setCommodityImages(String commodityImages) {
        this.commodityImages = commodityImages;
    }

    public String getPromotionFee() {
		return promotionFee;
	}
	public void setPromotionFee(String promotionFee) {
		this.promotionFee = promotionFee;
	}
	public String getShippingFee() {
		return shippingFee;
	}
	public void setShippingFee(String shippingFee) {
		this.shippingFee = shippingFee;
	}
	public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


    public String getGoodsState() {
        return goodsState;
    }
    public void setGoodsState(String goodsState) {
        this.goodsState = goodsState;
    }


    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
	public String getMerchantsNo() {
		return merchantsNo;
	}
	public void setMerchantsNo(String merchantsNo) {
		this.merchantsNo = merchantsNo;
	}
	public String getMerchantsName() {
		return merchantsName;
	}
	public void setMerchantsName(String merchantsName) {
		this.merchantsName = merchantsName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
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
	public GoodsClassification getGoodsClassification() {
		return goodsClassification;
	}
	public void setGoodsClassification(GoodsClassification goodsClassification) {
		this.goodsClassification = goodsClassification;
	}
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public String getCommodityNumber() {
		return commodityNumber;
	}
	public void setCommodityNumber(String commodityNumber) {
		this.commodityNumber = commodityNumber;
	}
	public String getGoodsPhotos() {
		return goodsPhotos;
	}
	public void setGoodsPhotos(String goodsPhotos) {
		this.goodsPhotos = goodsPhotos;
	}
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	public String getGoodState() {
		return goodState;
	}
	public void setGoodState(String goodState) {
		this.goodState = goodState;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getKeySeach() {
		return keySeach;
	}
	public void setKeySeach(String keySeach) {
		this.keySeach = keySeach;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public int getShippingNum() {
		return shippingNum;
	}
	public void setShippingNum(int shippingNum) {
		this.shippingNum = shippingNum;
	}

}