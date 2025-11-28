package com.flyway.cfaccount.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonResponse;
import com.flyway.util.JsonUtil;

import java.util.List;

/**
 * 查询FLH余额响应结果
 */
public class QueryBalanceResponse extends CommonResponse {

    /**
     * 余额数据列表
     */
    @JsonProperty("data")
    private List<CurrencyBalance> data;

    public QueryBalanceResponse() {
        super();
    }


    public List<CurrencyBalance> getData() {
        return data;
    }

    public void setData(List<CurrencyBalance> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return JsonUtil.toPrettyJsonString(this);
    }
}