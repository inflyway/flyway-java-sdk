package com.flyway.rate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonResponse;

public class OpenRateRespose extends CommonResponse {

    /**
     * 余额数据列表
     */
    @JsonProperty("data")
    private CustomRateConfig data;

    public OpenRateRespose() {
        super();
    }

    public CustomRateConfig getData() {
        return data;
    }

    public void setData(CustomRateConfig data) {
        this.data = data;
    }
}
