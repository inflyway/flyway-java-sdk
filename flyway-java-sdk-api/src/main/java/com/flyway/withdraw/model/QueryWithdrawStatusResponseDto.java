package com.flyway.withdraw.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * 提现状态查询响应数据
 */
public class QueryWithdrawStatusResponseDto {

    /**
     * 状态
     */
    @JsonProperty("status")
    private String status;

    /**
     * 状态描述
     */
    @JsonProperty("statusDesc")
    private String statusDesc;

    /**
     * 预计付款时间
     */
    @JsonProperty("estPaymentTime")
    private Long estPaymentTime;

    /**
     * 失败原因
     */
    @JsonProperty("failReason")
    private String failReason;

    /**
     * 退回币种
     */
    @JsonProperty("returnCurrency")
    private String returnCurrency;

    /**
     * 退回金额
     */
    @JsonProperty("returnAmt")
    private BigDecimal returnAmt;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Long getEstPaymentTime() {
        return estPaymentTime;
    }

    public void setEstPaymentTime(Long estPaymentTime) {
        this.estPaymentTime = estPaymentTime;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getReturnCurrency() {
        return returnCurrency;
    }

    public void setReturnCurrency(String returnCurrency) {
        this.returnCurrency = returnCurrency;
    }

    public BigDecimal getReturnAmt() {
        return returnAmt;
    }

    public void setReturnAmt(BigDecimal returnAmt) {
        this.returnAmt = returnAmt;
    }
}