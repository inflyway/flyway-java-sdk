package com.flyway.fx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

import java.math.BigDecimal;

/**
 * FX汇率查询请求参数
 */
public class FxRateInquiryRequest extends CommonRequest {

    /**
     * 开放平台账户ID
     */
    @JsonProperty("openID")
    private String openID;

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
     * 交易金额
     */
    @JsonProperty("amount")
    private BigDecimal amount;

    /**
     * 请求流水号
     */
    @JsonProperty("requestNo")
    private String requestNo;

    public FxRateInquiryRequest() {
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    @Override
    public String toString() {
        return "FxRateInquiryRequest{" +
                "openID='" + openID + '\'' +
                ", sellCurrency='" + sellCurrency + '\'' +
                ", buyCurrency='" + buyCurrency + '\'' +
                ", amount=" + amount +
                ", requestNo='" + requestNo + '\'' +
                '}';
    }
}