package com.flyway.withdraw.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;
import com.flyway.withdraw.model.ApplyWithdrawRequest.PayeeInfo;

/**
 * 提现CNY失败换卡重发请求参数
 */
public class UpdateCardRepaymentRequest extends CommonRequest {

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
     * 收款人信息
     */
    @JsonProperty("payeeInfo")
    private PayeeInfo payeeInfo;

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

    public PayeeInfo getPayeeInfo() {
        return payeeInfo;
    }

    public void setPayeeInfo(PayeeInfo payeeInfo) {
        this.payeeInfo = payeeInfo;
    }
}