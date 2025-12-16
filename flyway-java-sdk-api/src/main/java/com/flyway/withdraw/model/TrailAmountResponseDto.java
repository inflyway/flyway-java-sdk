package com.flyway.withdraw.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 提现到账金额试算响应数据
 */
public class TrailAmountResponseDto {

    /**
     * 试算ID
     */
    private String trailId;

    /**
     * 提现手续费
     */
    private BigDecimal withdrawFee;

    /**
     * 业务费率
     */
    private BigDecimal businessRate;

    /**
     * 单笔手续费
     */
    private BigDecimal perTransactionFee;

    /**
     * 最高手续费
     */
    private BigDecimal maxTransactionFee;

    /**
     * 最低手续费
     */
    private BigDecimal minTransactionFee;

    /**
     * 到账金额
     */
    private BigDecimal arriveAmt;

    /**
     * 账户扣款金额
     */
    private BigDecimal accountDeductAmt;

    /**
     * 预计付款时间
     */
    private LocalDateTime estPaymentTime;

    public String getTrailId() {
        return trailId;
    }

    public void setTrailId(String trailId) {
        this.trailId = trailId;
    }

    public BigDecimal getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(BigDecimal withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    public BigDecimal getBusinessRate() {
        return businessRate;
    }

    public void setBusinessRate(BigDecimal businessRate) {
        this.businessRate = businessRate;
    }

    public BigDecimal getPerTransactionFee() {
        return perTransactionFee;
    }

    public void setPerTransactionFee(BigDecimal perTransactionFee) {
        this.perTransactionFee = perTransactionFee;
    }

    public BigDecimal getMaxTransactionFee() {
        return maxTransactionFee;
    }

    public void setMaxTransactionFee(BigDecimal maxTransactionFee) {
        this.maxTransactionFee = maxTransactionFee;
    }

    public BigDecimal getMinTransactionFee() {
        return minTransactionFee;
    }

    public void setMinTransactionFee(BigDecimal minTransactionFee) {
        this.minTransactionFee = minTransactionFee;
    }

    public BigDecimal getArriveAmt() {
        return arriveAmt;
    }

    public void setArriveAmt(BigDecimal arriveAmt) {
        this.arriveAmt = arriveAmt;
    }

    public BigDecimal getAccountDeductAmt() {
        return accountDeductAmt;
    }

    public void setAccountDeductAmt(BigDecimal accountDeductAmt) {
        this.accountDeductAmt = accountDeductAmt;
    }

    public LocalDateTime getEstPaymentTime() {
        return estPaymentTime;
    }

    public void setEstPaymentTime(LocalDateTime estPaymentTime) {
        this.estPaymentTime = estPaymentTime;
    }
}