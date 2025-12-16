package com.flyway.fx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

/**
 * FX汇兑详情查询请求参数
 */
public class FxExchangeDetailRequest extends CommonRequest {

    /**
     * 开放平台账户ID
     */
    @JsonProperty("openID")
    private String openID;

    /**
     * 流水号
     */
    @JsonProperty("serialNumber")
    private String serialNumber;

    /**
     * 请求流水号
     */
    @JsonProperty("requestNo")
    private String requestNo;

    public FxExchangeDetailRequest() {
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    @Override
    public String toString() {
        return "FxExchangeDetailRequest{" +
                "openID='" + openID + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", requestNo=" + requestNo +
                '}';
    }
}