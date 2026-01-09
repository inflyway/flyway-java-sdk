package com.flyway.trade.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 交易订单审核查询响应
 */
public class TradeBackgroundAuditInfo {

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectedDesc() {
        return rejectedDesc;
    }

    public void setRejectedDesc(String rejectedDesc) {
        this.rejectedDesc = rejectedDesc;
    }

    /**
     * 业务单号
     */
    @JsonProperty("requestNo")
    private String requestNo;

    /**
     * 审核状态
     *  pending 审核中
     *  approved 审核通过
     *  rejected 审核拒绝
     */
    @JsonProperty("status")
    private String status;

    /**
     * 拒绝原因
     */
    @JsonProperty("rejectedDesc")
    private String rejectedDesc;


}
