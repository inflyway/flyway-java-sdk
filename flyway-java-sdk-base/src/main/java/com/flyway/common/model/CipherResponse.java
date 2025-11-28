package com.flyway.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 查询FLH余额响应结果
 */
public class CipherResponse extends CommonResponse {



    /**
     * 余额数据列表
     */
    @JsonProperty("data")
    private CommonResponseData data;

    public CommonResponseData getData() {
        return data;
    }

    public void setData(CommonResponseData data) {
        this.data = data;
    }
}