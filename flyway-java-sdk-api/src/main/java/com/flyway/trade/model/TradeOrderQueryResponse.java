package com.flyway.trade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonResponse;
import com.flyway.util.JsonUtil;


public class TradeOrderQueryResponse extends CommonResponse {

    @JsonProperty("data")
    private TradeOrderQueryInfo data;

    public TradeOrderQueryResponse() {
        super();
    }

    public TradeOrderQueryInfo getData() {
        return data;
    }

    public void setData(TradeOrderQueryInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtil.toPrettyJsonString(this);
    }
}
