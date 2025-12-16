package com.flyway.withdraw.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

import java.math.BigDecimal;

/**
 * 提现到账金额试算请求参数
 */
public class TrailAmountRequest extends CommonRequest {

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

    /**
     * 场景
     */
    @JsonProperty("scene")
    private Integer scene;

    /**
     * 提现币种
     */
    @JsonProperty("withdrawCurrency")
    private String withdrawCurrency;

    /**
     * 试算金额
     */
    @JsonProperty("trailAmount")
    private BigDecimal trailAmount;

    /**
     * 收款银行所在国家
     */
    @JsonProperty("payeeBankCountry")
    private String payeeBankCountry;

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

    public Integer getScene() {
        return scene;
    }

    public void setScene(Integer scene) {
        this.scene = scene;
    }

    public String getWithdrawCurrency() {
        return withdrawCurrency;
    }

    public void setWithdrawCurrency(String withdrawCurrency) {
        this.withdrawCurrency = withdrawCurrency;
    }

    public BigDecimal getTrailAmount() {
        return trailAmount;
    }

    public void setTrailAmount(BigDecimal trailAmount) {
        this.trailAmount = trailAmount;
    }

    public String getPayeeBankCountry() {
        return payeeBankCountry;
    }

    public void setPayeeBankCountry(String payeeBankCountry) {
        this.payeeBankCountry = payeeBankCountry;
    }
}