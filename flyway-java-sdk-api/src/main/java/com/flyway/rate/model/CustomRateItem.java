package com.flyway.rate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 自定义费率项实体类
 * 对应customRateList数组中的元素结构
 */

public class CustomRateItem {
    /**
     * 业务编码
     */
    @JsonProperty("bizCode")
    private String bizCode;

    /**
     * 参数1编码
     */
    @JsonProperty("parameter1Code")
    private String parameter1Code;

    /**
     * 参数2编码
     */
    @JsonProperty("parameter2Code")
    private String parameter2Code;

    /**
     * 参数3编码
     */
    @JsonProperty("parameter3Code")
    private String parameter3Code;

    /**
     * 参数4编码
     */
    @JsonProperty("parameter4Code")
    private String parameter4Code;

    /**
     * 参数5编码
     */
    @JsonProperty("parameter5Code")
    private String parameter5Code;

    /**
     * 费率等级
     */
    @JsonProperty("rateLevel")
    private String rateLevel;

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getParameter1Code() {
        return parameter1Code;
    }

    public void setParameter1Code(String parameter1Code) {
        this.parameter1Code = parameter1Code;
    }

    public String getParameter2Code() {
        return parameter2Code;
    }

    public void setParameter2Code(String parameter2Code) {
        this.parameter2Code = parameter2Code;
    }

    public String getParameter3Code() {
        return parameter3Code;
    }

    public void setParameter3Code(String parameter3Code) {
        this.parameter3Code = parameter3Code;
    }

    public String getParameter4Code() {
        return parameter4Code;
    }

    public void setParameter4Code(String parameter4Code) {
        this.parameter4Code = parameter4Code;
    }

    public String getParameter5Code() {
        return parameter5Code;
    }

    public void setParameter5Code(String parameter5Code) {
        this.parameter5Code = parameter5Code;
    }

    public String getRateLevel() {
        return rateLevel;
    }

    public void setRateLevel(String rateLevel) {
        this.rateLevel = rateLevel;
    }
}