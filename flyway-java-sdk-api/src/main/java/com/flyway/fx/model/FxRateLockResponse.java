package com.flyway.fx.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * FX汇率锁汇响应数据
 */
public class FxRateLockResponse {

    /**
     * 订单ID
     */
    @JsonProperty("id")
    private String id;

    /**
     * 流水号
     */
    @JsonProperty("serialNumber")
    private String serialNumber;

    /**
     * 卖出币种
     */
    @JsonProperty("sellCurrency")
    private String sellCurrency;

    /**
     * 买入币种
     */
    @JsonProperty("buyCurrency")
    private String buyCurrency;

    /**
     * 卖出金额
     */
    @JsonProperty("sellAmount")
    private String sellAmount;

    /**
     * 买入金额
     */
    @JsonProperty("buyAmount")
    private String buyAmount;

    /**
     * 订单状态
     */
    @JsonProperty("status")
    private Integer status;

    /**
     * 交易时间
     */
    @JsonProperty("exchangeTime")
    private Long exchangeTime;

    /**
     * 汇率
     */
    @JsonProperty("rate")
    private String rate;

    /**
     * 失败原因
     */
    @JsonProperty("reason")
    private String reason;

    /**
     * 计划结算日期
     */
    @JsonProperty("planSettleDate")
    private String planSettleDate;

    public FxRateLockResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSellCurrency() {
        return sellCurrency;
    }

    public void setSellCurrency(String sellCurrency) {
        this.sellCurrency = sellCurrency;
    }

    public String getBuyCurrency() {
        return buyCurrency;
    }

    public void setBuyCurrency(String buyCurrency) {
        this.buyCurrency = buyCurrency;
    }

    public String getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(String sellAmount) {
        this.sellAmount = sellAmount;
    }

    public String getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(String buyAmount) {
        this.buyAmount = buyAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Long exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPlanSettleDate() {
        return planSettleDate;
    }

    public void setPlanSettleDate(String planSettleDate) {
        this.planSettleDate = planSettleDate;
    }

    @Override
    public String toString() {
        return "FxRateLockResponse{" +
                "id='" + id + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", sellCurrency='" + sellCurrency + '\'' +
                ", buyCurrency='" + buyCurrency + '\'' +
                ", sellAmount='" + sellAmount + '\'' +
                ", buyAmount='" + buyAmount + '\'' +
                ", status=" + status +
                ", exchangeTime=" + exchangeTime +
                ", rate='" + rate + '\'' +
                ", reason='" + reason + '\'' +
                ", planSettleDate=" + planSettleDate +
                '}';
    }
}