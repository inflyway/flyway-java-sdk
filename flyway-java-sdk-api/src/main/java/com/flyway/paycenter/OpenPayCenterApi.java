package com.flyway.paycenter;


import com.flyway.common.FlywayConfig;
import com.flyway.common.FlywayUrlConstants;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;
import com.flyway.http.AbstractApi;
import com.flyway.http.HttpClientUtil;
import com.flyway.paycenter.model.request.FundflowQryRequest;
import com.flyway.paycenter.model.response.FundflowQryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author youjunbin
 * @since 2025-12-12 11:39:10
 */
public class OpenPayCenterApi extends AbstractApi {

    private static final Logger logger = LoggerFactory.getLogger(OpenPayCenterApi.class);

    private final String fundflowQueryPath;


    /**
     * 使用指定配置构造客户端
     */
    public OpenPayCenterApi(FlywayConfig config) {
        super(config);
        this.config = config;
        this.httpClient = new HttpClientUtil(config);
        this.fundflowQueryPath = FlywayUrlConstants.PAYCENTER_TT_FUNDFLOW_QUERY;
    }

    /**
     * 资金到账查询
     *
     * @param request 查询请求参数
     * @return 查询结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<FundflowQryResponse> fundflowQuery(FundflowQryRequest request) throws FlywayApiException {
        return execute(request, this.fundflowQueryPath, CommonResponse.class);
    }
}
