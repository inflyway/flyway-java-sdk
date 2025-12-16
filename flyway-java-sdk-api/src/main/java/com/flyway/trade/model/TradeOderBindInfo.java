package com.flyway.trade.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 交易订单创建请求
 */
public class TradeOderBindInfo {

    /**
     * 入账审核id
     */
    @JsonProperty("auditId")
    private String auditId;

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

}
