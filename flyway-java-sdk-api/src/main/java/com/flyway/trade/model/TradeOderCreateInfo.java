package com.flyway.trade.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 交易订单创建请求
 */
public class TradeOderCreateInfo {

    /**
     * 交易单号
     */
    @JsonProperty("tradeOrderId")
    private String tradeOrderId;

    public String getTradeOrderId() {
        return tradeOrderId;
    }

    public void setTradeOrderId(String tradeOrderId) {
        this.tradeOrderId = tradeOrderId;
    }

}
