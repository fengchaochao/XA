package com.prj.biz.bean._base;

import java.io.Serializable;

public class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	public Integer limit;
	
	public Integer offset;

    private String queryBeginDate;
    
    private String queryEndDate;
    //add by Feng 2016年12月9日 10:22:57  For  排序方式
    private String order;
    
    private String orderName;
    
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	
	public String getQueryBeginDate() {
		return queryBeginDate;
	}

	public void setQueryBeginDate(String queryBeginDate) {
		this.queryBeginDate = queryBeginDate;
	}

	public String getQueryEndDate() {
		return queryEndDate;
	}

	public void setQueryEndDate(String queryEndDate) {
		this.queryEndDate = queryEndDate;
	}

	@Override
	public String toString() {
		return "BaseEntity [limit=" + limit + ", offset=" + offset + ", queryBeginDate=" + queryBeginDate
				+ ", queryEndDate=" + queryEndDate + "]";
	}
	
	
	
}
