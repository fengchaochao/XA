package com.prj.biz.bean.distribution;

import com.prj.biz.bean._base.BaseEntity;

/** 
 * 描述: 抽成分配 实体类<br>
 * @author Liang
 * @date 2017-07-11
 * 
 */
public class Distribution extends BaseEntity  
{

    private static final long	serialVersionUID	= 1L;

    //  
    private String id;
    // 平台分成 
    private String platform;
    // 代理商分成 
    private String agent;
    // 推荐人 
    private String referees;
    // 锁定人 
    private String lockingPeople;
    // 其他 
    private String other;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getAgent() {
        return agent;
    }
    public void setAgent(String agent) {
        this.agent = agent;
    }


    public String getReferees() {
        return referees;
    }
    public void setReferees(String referees) {
        this.referees = referees;
    }


    public String getLockingPeople() {
        return lockingPeople;
    }
    public void setLockingPeople(String lockingPeople) {
        this.lockingPeople = lockingPeople;
    }


    public String getOther() {
        return other;
    }
    public void setOther(String other) {
        this.other = other;
    }

}
