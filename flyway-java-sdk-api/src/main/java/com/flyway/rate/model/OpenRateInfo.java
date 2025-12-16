package com.flyway.rate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OpenRateInfo {

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
     * 参数1名称
     */
    @JsonProperty("parameter1Name")
    private String parameter1Name;

    /**
     * 参数1编码
     */
    @JsonProperty("parameter1Code")
    private String parameter1Code;

    /**
     * 参数2名称
     */
    @JsonProperty("parameter2Name")
    private String parameter2Name;

    /**
     * 参数2编码
     */
    @JsonProperty("parameter2Code")
    private String parameter2Code;

    /**
     * 参数3名称
     */
    @JsonProperty("parameter3Name")
    private String parameter3Name;

    /**
     * 参数3编码
     */
    @JsonProperty("parameter3Code")
    private String parameter3Code;

    /**
     * 参数4名称
     */
    @JsonProperty("parameter4Name")
    private String parameter4Name;

    /**
     * 参数4编码
     */
    @JsonProperty("parameter4Code")
    private String parameter4Code;

    /**
     * 参数5名称
     */
    @JsonProperty("parameter5Name")
    private String parameter5Name;

    /**
     * 参数5编码
     */
    @JsonProperty("parameter5Code")
    private String parameter5Code;

    /**
     * 该费率对应的航班等级
     */
    @JsonProperty("flyLevel")
    private String flyLevel;
    /**
     * 配置的币种
     */
    @JsonProperty("currency")
    private String currency;
    /**
     * 业务费率（如0.002）
     */
    @JsonProperty("businessRate")
    private BigDecimal businessRate;

    /**
     * 每笔交易固定费用
     */
    @JsonProperty("perTransactionFee")
    private BigDecimal perTransactionFee;

    /**
     * 单笔交易最低费用
     */
    @JsonProperty("minTransactionFee")
    private BigDecimal minTransactionFee;

    /**
     * 单笔交易最高费用
     */
    @JsonProperty("maxTransactionFee")
    private BigDecimal maxTransactionFee;

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

    public String getParameter1Name() {
        return parameter1Name;
    }

    public void setParameter1Name(String parameter1Name) {
        this.parameter1Name = parameter1Name;
    }

    public String getParameter1Code() {
        return parameter1Code;
    }

    public void setParameter1Code(String parameter1Code) {
        this.parameter1Code = parameter1Code;
    }

    public String getParameter2Name() {
        return parameter2Name;
    }

    public void setParameter2Name(String parameter2Name) {
        this.parameter2Name = parameter2Name;
    }

    public String getParameter2Code() {
        return parameter2Code;
    }

    public void setParameter2Code(String parameter2Code) {
        this.parameter2Code = parameter2Code;
    }

    public String getParameter3Name() {
        return parameter3Name;
    }

    public void setParameter3Name(String parameter3Name) {
        this.parameter3Name = parameter3Name;
    }

    public String getParameter3Code() {
        return parameter3Code;
    }

    public void setParameter3Code(String parameter3Code) {
        this.parameter3Code = parameter3Code;
    }

    public String getParameter4Name() {
        return parameter4Name;
    }

    public void setParameter4Name(String parameter4Name) {
        this.parameter4Name = parameter4Name;
    }

    public String getParameter4Code() {
        return parameter4Code;
    }

    public void setParameter4Code(String parameter4Code) {
        this.parameter4Code = parameter4Code;
    }

    public String getParameter5Name() {
        return parameter5Name;
    }

    public void setParameter5Name(String parameter5Name) {
        this.parameter5Name = parameter5Name;
    }

    public String getParameter5Code() {
        return parameter5Code;
    }

    public void setParameter5Code(String parameter5Code) {
        this.parameter5Code = parameter5Code;
    }

    public String getFlyLevel() {
        return flyLevel;
    }

    public void setFlyLevel(String flyLevel) {
        this.flyLevel = flyLevel;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public BigDecimal getMinTransactionFee() {
        return minTransactionFee;
    }

    public void setMinTransactionFee(BigDecimal minTransactionFee) {
        this.minTransactionFee = minTransactionFee;
    }

    public BigDecimal getMaxTransactionFee() {
        return maxTransactionFee;
    }

    public void setMaxTransactionFee(BigDecimal maxTransactionFee) {
        this.maxTransactionFee = maxTransactionFee;
    }
}

