package com.prj.biz.bean.showamount;

import com.prj.biz.bean._base.BaseEntity;

/** 
 * 描述: 是否显示商家实收款 实体类<br>
 * @author Liang
 * @date 2017-08-04
 * 
 */
public class ShowAmount extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    //  
    private String id;
    // 是否显示实收额 0-显示 1-不显示 
    private String isShowAmount;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getIsShowAmount() {
        return isShowAmount;
    }
    public void setIsShowAmount(String isShowAmount) {
        this.isShowAmount = isShowAmount;
    }

}
