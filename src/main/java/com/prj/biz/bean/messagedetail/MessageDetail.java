package com.prj.biz.bean.messagedetail;

import com.prj.biz.bean._base.BaseEntity;
import java.lang.Integer;

/** 
 * 描述: 消息明细 实体类<br>
 * @author Jiang
 * @date 2017-07-25
 * 
 */
public class MessageDetail extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    // uuid 
    private String id;
    // 用户编码 
    private String userCode;
    // 消息表id 
    private String messageCode;
    // 0商家 2消费者 1消费商
    private Integer userType;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }


    public String getMessageCode() {
        return messageCode;
    }
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }


    public Integer getUserType() {
        return userType;
    }
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

}
