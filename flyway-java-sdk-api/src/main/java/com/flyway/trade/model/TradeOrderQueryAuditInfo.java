package com.flyway.trade.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 交易订单审核查询响应
 */
public class TradeOrderQueryAuditInfo {

    /**
     * 审核状态
     */
    @JsonProperty("auditStatus")
    private String auditStatus;

    /**
     * 入账状态
     */
    @JsonProperty("inacctStatus")
    private String inacctStatus;

    /**
     * 备注
     */
    @JsonProperty("remark")
    private String remark;

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getInacctStatus() {
        return inacctStatus;
    }

    public void setInacctStatus(String inacctStatus) {
        this.inacctStatus = inacctStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
