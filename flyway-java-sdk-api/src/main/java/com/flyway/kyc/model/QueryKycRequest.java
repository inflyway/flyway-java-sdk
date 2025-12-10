package com.flyway.kyc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

import lombok.Data;

/**
 * 查询kyc结果请求参数
 */
@Data
public class QueryKycRequest extends CommonRequest {
    
    /**
     * 开放平台账户ID
     */
    @JsonProperty("requestNo")
     private String requestNo;;
    
    public QueryKycRequest() {
    }
    
}