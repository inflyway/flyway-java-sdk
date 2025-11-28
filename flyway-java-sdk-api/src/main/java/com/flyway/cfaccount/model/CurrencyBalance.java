
package com.flyway.cfaccount.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 币种余额信息
 */
public class CurrencyBalance {
    
    /**
     * 币种代码
     */
    @JsonProperty("currency")
    private String currency;
    
    /**
     * 多币种余额详情
     */
    @JsonProperty("multiBalance")
    private MultiBalance multiBalance;
    
    public CurrencyBalance() {
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public MultiBalance getMultiBalance() {
        return multiBalance;
    }
    
    public void setMultiBalance(MultiBalance multiBalance) {
        this.multiBalance = multiBalance;
    }
    
    @Override
    public String toString() {
        return "CurrencyBalance{" +
                "currency='" + currency + '\'' +
                ", multiBalance=" + multiBalance +
                '}';
    }
}