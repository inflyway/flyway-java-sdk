package com.flyway.withdraw.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

/**
 * @Description
 * @Date 2025/12/12 16:51
 * @Author lut
 * @Version 1.0.0
 */
public class WithdrawAmountRequest extends CommonRequest {

    /**
     * OpenID
     */
    @JsonProperty("openID")
    private String openID;

    /**
     * 提现币种
     */
    @JsonProperty("withdrawCurrency")
    private String withdrawCurrency;

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getWithdrawCurrency() {
        return withdrawCurrency;
    }

    public void setWithdrawCurrency(String withdrawCurrency) {
        this.withdrawCurrency = withdrawCurrency;
    }
}
