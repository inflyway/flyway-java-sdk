package com.flyway.fx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

/**
 * FX汇率锁汇请求参数
 */
public class FxRateLockRequest extends CommonRequest {

    /**
     * 请求流水号
     */
    @JsonProperty("requestNo")
    private String requestNo;

    /**
     * 开放平台账户ID
     */
    @JsonProperty("openID")
    private String openID;

    /**
     * 询汇编号
     */
    @JsonProperty("inquiryNo")
    private String inquiryNo;

    /**
     * 卖出金额
     */
    @JsonProperty("sellAmount")
    private String sellAmount;

    /**
     * 询汇编号
     */
    @JsonProperty("callback")
    private String callback;

    public FxRateLockRequest() {
    }

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

    public String getInquiryNo() {
        return inquiryNo;
    }

    public void setInquiryNo(String inquiryNo) {
        this.inquiryNo = inquiryNo;
    }

    public String getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(String sellAmount) {
        this.sellAmount = sellAmount;
    }

    @Override
    public String toString() {
        return "FxRateLockRequest{" +
                "requestNo='" + requestNo + '\'' +
                ", openID='" + openID + '\'' +
                ", inquiryNo='" + inquiryNo + '\'' +
                ", sellAmount='" + sellAmount + '\'' +
                ", callback='" + callback + '\'' +
                '}';
    }

    public String getCallback() {
        return this.callback == null ? null : this.callback.trim().replace("\n", "");
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
