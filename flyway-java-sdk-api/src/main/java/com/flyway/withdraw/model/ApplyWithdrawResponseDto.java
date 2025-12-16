package com.flyway.withdraw.model;

/**
 * 提现申请响应数据
 */
public class ApplyWithdrawResponseDto {

    /**
     * 提现编号
     */
    private String withdrawNo;

    public String getWithdrawNo() {
        return withdrawNo;
    }

    public void setWithdrawNo(String withdrawNo) {
        this.withdrawNo = withdrawNo;
    }
}