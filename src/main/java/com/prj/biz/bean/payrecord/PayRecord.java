package com.prj.biz.bean.payrecord;

import com.prj.biz.bean._base.BaseEntity;

/** 
 * 描述: 线下支付记录 实体类<br>
 * @author Liang
 * @date 2017-08-23
 * 
 */
public class PayRecord extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    //  
    private String businessid;
    // 商家类型 1-商家 0-消费商
    private String businessType;
    // 支付账号 
    private String account;
    //  
    private String id;
    // 订单号 
    private String orderno;


    public String getBusinessid() {
        return businessid;
    }
    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }


    public String getBusinessType() {
        return businessType;
    }
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }


    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getOrderno() {
        return orderno;
    }
    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

}
