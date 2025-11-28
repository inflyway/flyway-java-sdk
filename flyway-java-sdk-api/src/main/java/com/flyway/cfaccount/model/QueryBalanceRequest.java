package com.flyway.cfaccount.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

/**
 * 查询FLH余额请求参数
 */
public class QueryBalanceRequest extends CommonRequest {
    
    /**
     * 开放平台账户ID
     */
    @JsonProperty("openId")
    private String openId;
    
    public QueryBalanceRequest() {
    }
    
    public QueryBalanceRequest(String openId) {
        this.openId = openId;
    }
    
    public String getOpenId() {
        return openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
    @Override
    public String toString() {
        return "QueryFLHBalanceRequest{" +
                "openId='" + openId + '\'' +
                '}';
    }
}