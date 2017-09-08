package com.prj.biz.bean.sharerecord;

import com.prj.biz.bean._base.BaseEntity;

/** 
 * 描述: 商品分享记录表 实体类<br>
 * @author Liang
 * @date 2017-08-26
 * 
 */
public class ShareRecord extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    //  
    private String id;
    // 用户id  
    private String userId;
    // 商品id  
    private String goodsId;
    // 分享佣金 
    private String shareFee;
    // 创建时间 
    private String createDate;
    
    //商品名称（显示）
    private String  goodsName;
    
    private String  startTime;
    
    private String  endTime;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }


    public String getShareFee() {
        return shareFee;
    }
    public void setShareFee(String shareFee) {
        this.shareFee = shareFee;
    }


    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	

}
