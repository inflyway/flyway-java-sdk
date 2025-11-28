package com.flyway.kyc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 企业实名响应结果
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class KycResponse extends CommonResponse {

    /**
     * 余额数据列表
     */
    @JsonProperty("data")
    private List<KycCo> data;

    public KycResponse() {
        super();
    }
 
     
}