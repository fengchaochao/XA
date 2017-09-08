package com.prj.biz.bean.redenveloperecord;

import com.prj.biz.bean._base.BaseEntity;

/** 
 * 描述: 红包记录表 实体类<br>
 * @author Liang
 * @date 2017-07-26
 * 
 */
public class RedEnvelopeRecord extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    //  
    private String id;
    // 编号 
    private String redEnvelopeNo;
    // 金额 
    private String price;
    // 领取人-用户ID
    private String receiver;
    // 领取人角色 0-消费者1-消费商 2-商家 
    private String receiverType;
    // 领取时间 
    private String receiveDate;
    // 红包ID 
    private String redEnvelopeId;
    /**************查询，显示用*************/
    //领取人昵称
    private String receiverName;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getRedEnvelopeNo() {
        return redEnvelopeNo;
    }
    public void setRedEnvelopeNo(String redEnvelopeNo) {
        this.redEnvelopeNo = redEnvelopeNo;
    }


    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }


    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }


    public String getReceiverType() {
        return receiverType;
    }
    public void setReceiverType(String receiverType) {
        this.receiverType = receiverType;
    }


    public String getReceiveDate() {
        return receiveDate;
    }
    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }


    public String getRedEnvelopeId() {
        return redEnvelopeId;
    }
    public void setRedEnvelopeId(String redEnvelopeId) {
        this.redEnvelopeId = redEnvelopeId;
    }
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

}
