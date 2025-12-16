package com.flyway.paycenter.model.request;


import com.flyway.common.model.CommonRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 资金到账查询查询入参
 *
 * @author youjunbin
 * @since 2025-12-05 17:48:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundflowQryRequest extends CommonRequest {

    /**
     * 入账流水号
     */
    private String flowId;

    /**
     * openId
     */
    private String openId;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
