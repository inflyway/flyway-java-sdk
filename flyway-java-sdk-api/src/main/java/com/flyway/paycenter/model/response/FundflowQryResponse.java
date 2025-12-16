package com.flyway.paycenter.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 资金到账查询出参
 *
 * @author youjunbin
 * @since 2025-12-04 20:32:31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundflowQryResponse {

    /**
     * 收款流水号
     */
    private String flowId;

    /**
     * 银行流水号
     */
    private String tradeNo;

    /**
     * 收款方账号
     */
    private String payeeAccountNo;

    /**
     * 收款方姓名
     */
    private String payeeName;

    /**
     * 付款方账号
     */
    private String payerAccountNo;

    /**
     * 付款人姓名
     */
    private String payerName;

    /**
     * 付款方 swift
     */
    private String payerSwiftCode;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 币种
     */
    private String currency;

    /**
     * 手续费
     */
    private BigDecimal fee;

    /**
     * 手续费币种
     */
    private String feeCurrency;

    /**
     * 入账成功时间
     */
    private LocalDateTime accountingTime;

    /**
     * 付款附言
     */
    private String postscript;

    /**
     * 到账资金类型:1-附件信息 选填\n" +
     * "2-PICI/合同文件附件 必填\n" +
     * "3-PICI/合同文件/发货凭证/报关单附件 必填\n" +
     * "4-PICI/合同文件/商品采购明细/进口清关文件/清关产品图片附件 必填
     */
    private String flowType;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
