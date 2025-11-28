package com.flyway.kyc.model;

import java.util.List;

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

    /**
     * 余额数据列表
     */
    @JsonProperty("data")
    private List<KycResultInfoCo> data;

    public QueryKycResponse() {
        super();
    }
 
     
}