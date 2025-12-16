package com.flyway.rate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FeeTable {
    /**
     * 业务名称
     */
    @JsonProperty("bizName")
    private String bizName;

    /**
     * 业务编码
     */
    @JsonProperty("bizCode")
    private String bizCode;

    /**
     * 费率列表
     */
    @JsonProperty("rateList")
    private List<OpenRateInfo> rateList;

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public List<OpenRateInfo> getRateList() {
        return rateList;
    }

    public void setRateList(List<OpenRateInfo> rateList) {
        this.rateList = rateList;
    }
}
