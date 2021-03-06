package com.prj.biz.bean.goodsclassification;

import com.prj.biz.bean._base.BaseEntity;

/** 
 * 描述: 商品分类 实体类<br>
 * @author Liang
 * @date 2017-07-11
 * 
 */
public class GoodsClassification extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    //  
    private String id;
    // 商品分类 
    private String categoryName;
    // 商品编号 
    private Integer goodsNumber;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
	public Integer getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}



}
