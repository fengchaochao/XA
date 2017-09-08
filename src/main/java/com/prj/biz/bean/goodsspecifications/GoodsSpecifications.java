package com.prj.biz.bean.goodsspecifications;

import com.prj.biz.bean._base.BaseEntity;
import com.prj.biz.bean.businessinformation.BusinessInformation;
import com.prj.biz.bean.goods.Goods;
import com.prj.biz.bean.goodsclassification.GoodsClassification;

/** 
 * 描述: 商品规格表 实体类<br>
 * @author Liang
 * @date 2017-07-13
 * 
 */
public class GoodsSpecifications extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    //  
    private String id;
    // 规格名称 
    private String specificationsName;
    // 库存 
    private Integer inventory;
    // 价格 
    private double price;
    // 商品id 
    private String goodsId;
    
    /**查询用**/
    
    private Goods goods;
    
    private BusinessInformation businessInformation;
    
    private GoodsClassification goodsClassification;
    // 销量
    private int sale;
    /**
     *查询
     * */
    private String typeId;
    
	private String goodsName;
	
	private String businessName;
	
	private String businessNo;
	
	 // 省份 
    private String provincesId;
    // 市 
    private String cityId;
    // 区 
    private String areaId;
    //起始日期（显示用）
    private String beginDate;
    //结束日期（显示用）
    private String endDate;
    //
    private String goodsState;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getSpecificationsName() {
        return specificationsName;
    }
    public void setSpecificationsName(String specificationsName) {
        this.specificationsName = specificationsName;
    }


    public Integer getInventory() {
        return inventory;
    }
    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }


    public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public BusinessInformation getBusinessInformation() {
		return businessInformation;
	}
	public void setBusinessInformation(BusinessInformation businessInformation) {
		this.businessInformation = businessInformation;
	}
	public GoodsClassification getGoodsClassification() {
		return goodsClassification;
	}
	public void setGoodsClassification(GoodsClassification goodsClassification) {
		this.goodsClassification = goodsClassification;
	}
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
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
	public String getGoodsState() {
		return goodsState;
	}
	public void setGoodsState(String goodsState) {
		this.goodsState = goodsState;
	}
    

}
