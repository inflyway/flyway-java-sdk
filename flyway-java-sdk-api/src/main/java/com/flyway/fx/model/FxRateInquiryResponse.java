package com.flyway.fx.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * FX汇率查询响应数据
 */
public class FxRateInquiryResponse {

    /**
     * 汇率
     */
    @JsonProperty("rate")
    private String rate;

    /**
     * 询汇编号
     */
    @JsonProperty("inquiryNo")
    private String inquiryNo;

    /**
     * 请求流水号
     */
    @JsonProperty("requestNo")
    private String requestNo;

    /**
     * 请求流水号
     */
    @JsonProperty("validTime")
    private Long validTime;

    public FxRateInquiryResponse() {
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getInquiryNo() {
        return inquiryNo;
    }

    public void setInquiryNo(String inquiryNo) {
        this.inquiryNo = inquiryNo;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public Long getValidTime() {
        return validTime;
    }

    public void setValidTime(Long validTime) {
        this.validTime = validTime;
    }

    @Override
    public String toString() {
        return "FxRateInquiryResponse{" +
                "rate='" + rate + '\'' +
                ", inquiryNo='" + inquiryNo + '\'' +
                ", requestNo='" + requestNo + '\'' +
                ", validTime=" + validTime +
                '}';
    }
}