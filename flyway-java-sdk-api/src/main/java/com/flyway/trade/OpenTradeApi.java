package com.flyway.trade;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flyway.common.FlywayConfig;
import com.flyway.common.FlywayUrlConstants;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;
import com.flyway.http.AbstractApi;
import com.flyway.http.HttpClientUtil;
import com.flyway.trade.model.TradeOderBindInfo;
import com.flyway.trade.model.TradeOderCreateInfo;
import com.flyway.trade.model.TradeOderCreateRequest;
import com.flyway.trade.model.TradeOrderBindRequest;
import com.flyway.trade.model.TradeOrderQueryAuditInfo;
import com.flyway.trade.model.TradeOrderQueryAuditRequest;
import com.flyway.trade.model.TradeOrderQueryInfo;
import com.flyway.trade.model.TradeOrderQueryRequest;

public class OpenTradeApi extends AbstractApi {

    private String tradeOpenCreatePath;
    private String tradeQueryPath;
    private String tradeBindPath;
    private String tradeQueryAuditPath;

    /**
     * 使用指定配置构造客户端
     *
     * @param config
     */
    public OpenTradeApi(FlywayConfig config) {
        super(config);
        this.config = config;
        this.httpClient = new HttpClientUtil(config);
        this.tradeOpenCreatePath = FlywayUrlConstants.TRADE_CREATE;
        this.tradeQueryPath = FlywayUrlConstants.TRADE_QUERY;
        this.tradeBindPath = FlywayUrlConstants.TRADE_BIND_APPLY;
        this.tradeQueryAuditPath = FlywayUrlConstants.TRADE_QUERY_AUDIT;
    }


    /**
     * 创建交易单
     * @param request 创建交易单请求
     * @return 创建交易单响应
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<TradeOderCreateInfo> createTrade(TradeOderCreateRequest request) throws FlywayApiException {
        return executeWithTypeRef(request, this.tradeOpenCreatePath, new TypeReference<CommonResponse<TradeOderCreateInfo>>() {
        });
    }

    /**
     * 查询入账交易单详情
     * @param request 查询入账交易单详情请求
     * @return 查询入账交易单详情响应
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<TradeOrderQueryInfo> queryTrade(TradeOrderQueryRequest request) throws FlywayApiException {
        return executeWithTypeRef(request, this.tradeQueryPath, new TypeReference<CommonResponse<TradeOrderQueryInfo>>() {
        });
    }

    /**
     * 关联入账流水并发起入账申请
     * @param request 关联入账流水并发起入账申请请求
     * @return 关联入账流水并发起入账申请响应
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<TradeOderBindInfo> bindOpenTrade(TradeOrderBindRequest request) throws FlywayApiException {
        return executeWithTypeRef(request, this.tradeBindPath, new TypeReference<CommonResponse<TradeOderBindInfo>>() {
        });
    }

    /**
     * 入账申请审核结果查询
     * @param request 入账申请审核结果查询请求
     * @return 入账申请审核结果查询响应
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<TradeOrderQueryAuditInfo> queryAuditStatus(TradeOrderQueryAuditRequest request) throws FlywayApiException {
        return executeWithTypeRef(request, this.tradeQueryAuditPath, new TypeReference<CommonResponse<TradeOrderQueryAuditInfo>>() {
        });
    }

}