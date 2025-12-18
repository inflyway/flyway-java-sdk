package com.flyway.trade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

public class TradeOrderQueryAuditRequest extends CommonRequest {

    /**
     * 开放平台账户ID
     */
    @JsonProperty("openID")
    private String openID;

    /**
     * 审核ID
     */
    @JsonProperty("requestNo")
    private String requestNo;

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }
}
