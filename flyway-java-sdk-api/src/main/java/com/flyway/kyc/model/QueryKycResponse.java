package com.flyway.kyc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询企业实名结果
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class QueryKycResponse extends CommonResponse {

   
    @JsonProperty("data")
    private KycResultInfoCo data;

    public QueryKycResponse() {
        super();
    }
 
     
}