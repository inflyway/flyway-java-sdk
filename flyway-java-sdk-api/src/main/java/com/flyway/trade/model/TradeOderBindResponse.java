package com.flyway.trade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonResponse;
import com.flyway.util.JsonUtil;

/**
 * 交易订单创建请求
 */
public class TradeOderBindResponse extends CommonResponse {

    /**
     * 入账审核id
     */
    @JsonProperty("data")
    private TradeOderBindInfo data;

    public TradeOderBindResponse() {
        super();
    }

    public TradeOderBindInfo getData() {
        return data;
    }

    public void setData(TradeOderBindInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtil.toPrettyJsonString(this);
    }
}
