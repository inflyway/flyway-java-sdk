package com.flyway.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

/**
 * VA开户结果查询请求参数
 */
public class VaAccountQueryRequest extends CommonRequest {

    /**
     * 请求流水号
     */
    @JsonProperty("requestNo")
    private String requestNo;

    /**
     * 银行卡号
     */
    @JsonProperty("bankCardNo")
    private String bankCardNo;

    /**
     * 开放平台账户ID
     */
    @JsonProperty("openID")
    private String openID;

    public VaAccountQueryRequest() {
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    @Override
    public String toString() {
        return "VaAccountQueryRequest{" +
                "requestNo='" + requestNo + '\'' +
                ", bankCardNo='" + bankCardNo + '\'' +
                ", openID='" + openID + '\'' +
                '}';
    }
}