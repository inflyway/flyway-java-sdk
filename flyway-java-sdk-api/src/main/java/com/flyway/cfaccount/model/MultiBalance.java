package com.flyway.cfaccount.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 多币种余额信息
 */
public class MultiBalance {
    
    /**
     * 锁定保证金
     */
    @JsonProperty("sdbzj")
    private String lockingMargin;
    
    /**
     * 待出账
     */
    @JsonProperty("dcz")
    private String pendingOutbound;
    
    /**
     * 余额
     */
    @JsonProperty("ye")
    private String balance;
    
    /**
     * 待入账
     */
    @JsonProperty("drz")
    private String pendingInbound;
    
    /**
     * 分销
     */
    @JsonProperty("fx")
    private String distribution;
    
    /**
     * 非法款
     */
    @JsonProperty("ffk")
    private String illegalFunds;
    
    public MultiBalance() {
    }
    
    public String getLockingMargin() {
        return lockingMargin;
    }
    
    public void setLockingMargin(String lockingMargin) {
        this.lockingMargin = lockingMargin;
    }
    
    public String getPendingOutbound() {
        return pendingOutbound;
    }
    
    public void setPendingOutbound(String pendingOutbound) {
        this.pendingOutbound = pendingOutbound;
    }
    
    public String getBalance() {
        return balance;
    }
    
    public void setBalance(String balance) {
        this.balance = balance;
    }
    
    public String getPendingInbound() {
        return pendingInbound;
    }
    
    public void setPendingInbound(String pendingInbound) {
        this.pendingInbound = pendingInbound;
    }
    
    public String getDistribution() {
        return distribution;
    }
    
    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }
    
    public String getIllegalFunds() {
        return illegalFunds;
    }
    
    public void setIllegalFunds(String illegalFunds) {
        this.illegalFunds = illegalFunds;
    }
    
    @Override
    public String toString() {
        return "MultiBalance{" +
                "lockingMargin='" + lockingMargin + '\'' +
                ", pendingOutbound='" + pendingOutbound + '\'' +
                ", balance='" + balance + '\'' +
                ", pendingInbound='" + pendingInbound + '\'' +
                ", distribution='" + distribution + '\'' +
                ", illegalFunds='" + illegalFunds + '\'' +
                '}';
    }
}