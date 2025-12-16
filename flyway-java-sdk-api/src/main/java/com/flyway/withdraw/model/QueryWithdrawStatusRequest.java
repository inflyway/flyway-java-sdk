package com.flyway.withdraw.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

/**
 * 提现状态查询请求参数
 */
public class QueryWithdrawStatusRequest extends CommonRequest {

    /**
     * 请求编号
     */
    @JsonProperty("requestNo")
    private String requestNo;

    /**
     * OpenID
     */
    @JsonProperty("openID")
    private String openID;

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }
}