package com.flyway.cfaccount;

import com.flyway.cfaccount.model.QueryBalanceRequest;
import com.flyway.cfaccount.model.QueryBalanceResponse;
import com.flyway.common.FlywayConfig;
import com.flyway.common.FlywayUrlConstants;
import com.flyway.exception.FlywayApiException;
import com.flyway.http.AbstractApi;
import com.flyway.http.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Inflyway SDK客户端
 */
public class OpenCfaccountApi extends AbstractApi {

    private static final Logger logger = LoggerFactory.getLogger(OpenCfaccountApi.class);

    private String queryBalancePath;


    /**
     * 使用指定配置构造客户端
     */
    public OpenCfaccountApi(FlywayConfig config) {
        super(config);
        this.config = config;
        this.httpClient = new HttpClientUtil(config);
        this.queryBalancePath = FlywayUrlConstants.CFACCOUNT_BALANCE_QUERY;
    }

    /**
     * 查询飞来汇余额
     *
     * @param request 查询请求参数
     * @return 查询结果
     * @throws FlywayApiException API调用异常
     */
    public QueryBalanceResponse queryBalance(QueryBalanceRequest request) throws FlywayApiException {
        return execute(request, this.queryBalancePath, QueryBalanceResponse.class);
    }
}
