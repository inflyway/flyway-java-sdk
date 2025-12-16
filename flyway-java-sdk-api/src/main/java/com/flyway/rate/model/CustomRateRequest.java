package com.flyway.rate.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

import java.util.List;

/**
 * 自定义费率请求实体类
 * 对应JSON根节点结构
 */

public class CustomRateRequest extends CommonRequest {

    /**
     * openID
     */
    @JsonProperty("openID")
    private String openID;

    /**
     * 请求编号
     */
    @JsonProperty("requestNo")
    private String requestNo;

    /**
     * 基础费率等级
     */
    @JsonProperty("flyLevel")
    private String flyLevel;

    /**
     * 业务线编码
     */
    @JsonProperty("bizCode")
    private String bizCode;
    /**
     * 自定义费率列表
     */
    @JsonProperty("customRateList")
    private List<CustomRateItem> customRateList;




    public List<CustomRateItem> getCustomRateList() {
        return customRateList;
    }

    public void setCustomRateList(List<CustomRateItem> customRateList) {
        this.customRateList = customRateList;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }


    public String getFlyLevel() {
        return flyLevel;
    }

    public void setFlyLevel(String flyLevel) {
        this.flyLevel = flyLevel;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

}