package com.flyway.trade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

import java.math.BigDecimal;
import java.util.List;

/**
 * 交易订单绑定请求
 */
public class TradeOrderBindRequest extends CommonRequest {

    /**
     * 请求流水号
     */
    @JsonProperty("requestNo")
    private String requestNo;

    /**
     * 开放平台账户ID
     */
    @JsonProperty("openID")
    private String openID;

    /**
     * 流程ID
     */
    @JsonProperty("flowId")
    private String flowId;

    /**
     * 交易订单列表
     */
    @JsonProperty("tradeOrderList")
    private List<TradeOrder> tradeOrderList;

    /**
     * 回调地址
     */
    @JsonProperty("callbackUrl")
    private String callbackUrl;

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public List<TradeOrder> getTradeOrderList() {
        return tradeOrderList;
    }

    public void setTradeOrderList(List<TradeOrder> tradeOrderList) {
        this.tradeOrderList = tradeOrderList;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public static class TradeOrder {
        /**
         * 交易订单号
         */
        @JsonProperty("tradeOrderId")
        private String tradeOrderId;

        /**
         * 匹配金额
         */
        @JsonProperty("matchAmount")
        private BigDecimal matchAmount;

        public String getTradeOrderId() {
            return tradeOrderId;
        }

        public void setTradeOrderId(String tradeOrderId) {
            this.tradeOrderId = tradeOrderId;
        }

        public BigDecimal getMatchAmount() {
            return matchAmount;
        }

        public void setMatchAmount(BigDecimal matchAmount) {
            this.matchAmount = matchAmount;
        }
    }
}
