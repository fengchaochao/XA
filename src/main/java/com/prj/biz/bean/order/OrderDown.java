package com.prj.biz.bean.order;


/** 
 * 描述: 订单表 实体类<br>(导出数据用)
 * @author Liang
 * @date 2017-08-01
 * 
 */
public class OrderDown 
{


    // 订单编号 
    private String orderNumber;
    // 消费者账户 (可以是支付宝或微信账户，也可以是余额付款，则给消费者id（显示需要消费者昵称）)
    private String consumerAccount;
    // 交易金额 
    private String money;
    // 是否抽成 1:是 0：否 
    private String isBonus;
    // 抽成金额 
    private String bonus;
    
    // 交易方式 1：扫码支付 2：在线支付 
    private String transactionMode;
    // 订单状态 1待配送、2待收货、3已完成、4:待付款 (扫码支付的订单状态只有：已完成    在线支付的订单状态有：待配送、待收货、已完成)
    private String status;
    // 订单创建时间 
    private String createtime;
    
    /**
     * 卖家编号(显示用)
     */
    private String storeNumber;
    
    /**
     * 卖家名称 (显示用)
     */
    private String storeName;
    
    
	public String getStoreNumber() {
		return storeNumber;
	}
	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    public String getConsumerAccount() {
        return consumerAccount;
    }
    public void setConsumerAccount(String consumerAccount) {
        this.consumerAccount = consumerAccount;
    }


    public String getMoney() {
        return money;
    }
    public void setMoney(String money) {
        this.money = money;
    }


    public String getIsBonus() {
        return isBonus;
    }
    public void setIsBonus(String isBonus) {
        this.isBonus = isBonus;
    }


    public String getBonus() {
        return bonus;
    }
    public void setBonus(String bonus) {
        this.bonus = bonus;
    }


    public String getTransactionMode() {
        return transactionMode;
    }
    public void setTransactionMode(String transactionMode) {
        this.transactionMode = transactionMode;
    }


    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


    public String getCreatetime() {
        return createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }


    public OrderDown(String orderNumber,String consumerAccount,String money,
    		String isBonus,String bonus,String transactionMode,String status,
    		String createtime,String storeNumber,String storeName){
    	super();
    	this.orderNumber = orderNumber;
    	this.consumerAccount = consumerAccount;
    	this.money = money;
    	this.isBonus = isBonus;
    	this.bonus = bonus;
    	this.transactionMode = transactionMode;
    	this.status = status;
    	this.createtime = createtime;
    	this.storeName = storeName;
    	this.storeNumber = storeNumber;
    	
    }

}
