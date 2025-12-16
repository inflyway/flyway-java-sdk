package com.flyway.trade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

public class TradeOrderQueryRequest extends CommonRequest {

    /**
     * 交易订单号
     */
    @JsonProperty("tradeOrderId")
    private String tradeOrderId;

    /**
     * 开放平台账户ID
     */
    @JsonProperty("openID")
    private String openID;

    public TradeOrderQueryRequest() {
    }

    public String getTradeOrderId() {
        return tradeOrderId;
    }

    public void setTradeOrderId(String tradeOrderId) {
        this.tradeOrderId = tradeOrderId;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    @Override
    public String toString() {
        return "TradeOrderRequest{" +
                "tradeOrderId='" + tradeOrderId + '\'' +
                ", openID='" + openID + '\'' +
                '}';
    }
}
