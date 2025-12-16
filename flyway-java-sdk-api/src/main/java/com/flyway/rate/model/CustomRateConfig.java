package com.flyway.rate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CustomRateConfig {
    /**
     * 企业名称
     */
    @JsonProperty("customName")
    private String customName;

    /**
     * 航班等级（通用）
     */
    @JsonProperty("flyLevel")
    private String flyLevel;

    /**
     * 费用明细表
     */
    @JsonProperty("feeTable")
    private List<FeeTable> feeTable;

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getFlyLevel() {
        return flyLevel;
    }

    public void setFlyLevel(String flyLevel) {
        this.flyLevel = flyLevel;
    }

    public List<FeeTable> getFeeTable() {
        return feeTable;
    }

    public void setFeeTable(List<FeeTable> feeTable) {
        this.feeTable = feeTable;
    }
}