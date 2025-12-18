package com.flyway.trade.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 交易订单创建请求
 */
public class TradeOderBindInfo {

    /**
     * 入账审核id
     */
    @JsonProperty("requestNo")
    private String requestNo;

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

}
