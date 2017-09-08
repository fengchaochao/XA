package com.prj.biz.bean.agentwithdrawalfee;

import com.prj.biz.bean._base.BaseEntity;

/** 
 * 描述: 代理商提现手续费 实体类<br>
 * @author Liang
 * @date 2017-07-11
 * 
 */
public class AgentWithdrawalFee extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    //  
    private String id;
    // 手续费 
    private String poundage;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getPoundage() {
        return poundage;
    }
    public void setPoundage(String poundage) {
        this.poundage = poundage;
    }

}
