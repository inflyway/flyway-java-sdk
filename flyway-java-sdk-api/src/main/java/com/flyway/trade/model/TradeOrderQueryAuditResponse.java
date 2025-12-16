package com.flyway.trade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonResponse;
import com.flyway.util.JsonUtil;

/**
 * 交易订单审核查询响应
 */
public class TradeOrderQueryAuditResponse extends CommonResponse {

    @JsonProperty("data")
    private TradeOrderQueryAuditInfo data;

    public TradeOrderQueryAuditResponse() {
        super();
    }

    public TradeOrderQueryAuditInfo getData() {
        return data;
    }

    public void setData(TradeOrderQueryAuditInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtil.toPrettyJsonString(this);
    }
}
