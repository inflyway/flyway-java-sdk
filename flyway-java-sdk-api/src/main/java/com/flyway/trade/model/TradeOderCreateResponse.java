package com.flyway.trade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonResponse;
import com.flyway.util.JsonUtil;

/**
 * 交易订单创建请求
 */
public class TradeOderCreateResponse extends CommonResponse {

    /**
     * 交易单号
     */
    @JsonProperty("data")
    private TradeOderCreateInfo data;


    public TradeOderCreateResponse() {
        super();
    }

    public TradeOderCreateInfo getData() {
        return data;
    }

    public void setData(TradeOderCreateInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtil.toPrettyJsonString(this);
    }
}
