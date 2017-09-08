package com.prj.biz.bean.withdrawrecord;

import com.prj.biz.bean._base.BaseEntity;
import com.prj.biz.bean.bankwithdrawal.BankWithdrawal;

/** 
 * 描述: 提现记录表 实体类<br>
 * @author Liang
 * @date 2017-08-07
 * 
 */
public class WithdrawRecord extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    //  
    private String id;
    // 提现金额 
    private int withdrawPrice;
    // 提现时间 
    private String withdrawDate;
    // 受理时间 
    private String processingTime;
    // 0-未处理 1-已受理  2-驳回
    private String applyState;
    // 处理结果 0-已打款 1-打款失败 
    private String result;
    // 失败 原因
    private String causeFailure;
    // 银行卡及待提现金额表ID 
    private String bankId;
    // 申请单号 
    private String applyNumber;

    //显示用
    private BankWithdrawal bankWithdrawal;
    //查询用
    private String  userState;
    //查询用
    private String  userId;
    
    //起始日期（显示用）
    private String beginDate;
    //结束日期（显示用）
    private String endDate;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public float getWithdrawPrice() {
		return withdrawPrice;
	}
	public String getWithdrawDate() {
        return withdrawDate;
    }
    public void setWithdrawDate(String withdrawDate) {
        this.withdrawDate = withdrawDate;
    }


    public String getApplyState() {
        return applyState;
    }
    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }


    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }


    public String getBankId() {
        return bankId;
    }
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }


    public String getApplyNumber() {
        return applyNumber;
    }
    public void setApplyNumber(String applyNumber) {
        this.applyNumber = applyNumber;
    }
	public BankWithdrawal getBankWithdrawal() {
		return bankWithdrawal;
	}
	public void setBankWithdrawal(BankWithdrawal bankWithdrawal) {
		this.bankWithdrawal = bankWithdrawal;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setWithdrawPrice(int withdrawPrice) {
		this.withdrawPrice = withdrawPrice;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
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
	public String getProcessingTime() {
		return processingTime;
	}
	public void setProcessingTime(String processingTime) {
		this.processingTime = processingTime;
	}
	public String getCauseFailure() {
		return causeFailure;
	}
	public void setCauseFailure(String causeFailure) {
		this.causeFailure = causeFailure;
	}

}
