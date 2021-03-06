package com.prj.biz.bean.message;

import com.prj.biz.bean._base.BaseEntity;
import java.lang.Integer;

/** 
 * 描述: 消息 实体类<br>
 * @author Jiang
 * @date 2017-07-25
 * 
 */
public class Message extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    // uuid 
    private String id;
    // 消息标题 
    private String messageTitle;
    // 消息内容 
    private String messageTest;
    // 发送时间 
    private String messageDate;
    // 发送人数 
    private Integer messageNumber;

    //起始日期（显示用）
    private String beginDate;
    //结束日期（显示用）
    private String endDate;
    
    // 发送者id（查询用）
    private String sellerId;
    

    public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getMessageTitle() {
        return messageTitle;
    }
    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }


    public String getMessageTest() {
        return messageTest;
    }
    public void setMessageTest(String messageTest) {
        this.messageTest = messageTest;
    }


    public String getMessageDate() {
        return messageDate;
    }
    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }


    public Integer getMessageNumber() {
        return messageNumber;
    }
    public void setMessageNumber(Integer messageNumber) {
        this.messageNumber = messageNumber;
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
