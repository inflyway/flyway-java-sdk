package com.flyway.withdraw.model;

import java.math.BigDecimal;

/**
 * @Description
 * @Date 2025/12/12 17:35
 * @Author lut
 * @Version 1.0.0
 */
public class WithdrawAmountDto {

    /**
     * 可提现金额
     */
    private BigDecimal withdrawAbleAmt;

    public BigDecimal getWithdrawAbleAmt() {
        return withdrawAbleAmt;
    }

    public void setWithdrawAbleAmt(BigDecimal withdrawAbleAmt) {
        this.withdrawAbleAmt = withdrawAbleAmt;
    }
}
