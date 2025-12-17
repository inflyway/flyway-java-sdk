package com.flyway.fx;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flyway.common.FlywayConfig;
import com.flyway.common.FlywayUrlConstants;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;
import com.flyway.fx.model.FxCurrencyPairRequest;
import com.flyway.fx.model.FxExchangeDetailRequest;
import com.flyway.fx.model.FxExchangeDetailResponse;
import com.flyway.fx.model.FxRateInquiryRequest;
import com.flyway.fx.model.FxRateInquiryResponse;
import com.flyway.fx.model.FxRateLockRequest;
import com.flyway.fx.model.FxRateLockResponse;
import com.flyway.http.AbstractApi;
import com.flyway.http.HttpClientUtil;

import java.util.List;

/**
 * FX外汇交易API
 */
public class OpenFxApi extends AbstractApi {

    private String fxRateInquiryPath;
    private String fxRateLockPath;
    private String fxCurrencyPairPath;
    private String fxExchangeDetailPath;

    /**
     * 使用指定配置构造客户端
     */
    public OpenFxApi(FlywayConfig config) {
        super(config);
        this.config = config;
        this.httpClient = new HttpClientUtil(config);
        this.fxRateInquiryPath = FlywayUrlConstants.FX_RATE_INQUIRY;
        this.fxRateLockPath = FlywayUrlConstants.FX_RATE_SUBMIT;
        this.fxCurrencyPairPath = FlywayUrlConstants.FX_CURRENCY_PAIR;
        this.fxExchangeDetailPath = FlywayUrlConstants.FX_EXCHANGE_DETAIL;
    }

    /**
     * FX汇率查询
     *
     * @param request 汇率查询请求参数
     * @return 汇率查询结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<FxRateInquiryResponse> inquiryRate(FxRateInquiryRequest request) throws FlywayApiException {
        return executeWithTypeRef(request, this.fxRateInquiryPath, new TypeReference<CommonResponse<FxRateInquiryResponse>>() {
        });
    }

    /**
     * FX汇率锁汇
     *
     * @param request 汇率锁汇请求参数
     * @return 汇率锁汇结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<FxRateLockResponse> submitRate(FxRateLockRequest request) throws FlywayApiException {
        return executeWithTypeRef(request, this.fxRateLockPath, new TypeReference<CommonResponse<FxRateLockResponse>>() {
        });
    }

    /**
     * FX货币对查询
     *
     * @param request 货币对查询请求参数
     * @return 货币对查询结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<List<String>> currencyPair(FxCurrencyPairRequest request) throws FlywayApiException {
        return executeGetWithTypeRef(request, this.fxCurrencyPairPath, new TypeReference<CommonResponse<List<String>>>() {
        });
    }

    /**
     * FX汇兑详情查询
     *
     * @param request 汇兑详情查询请求参数
     * @return 汇兑详情查询结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<FxExchangeDetailResponse> queryExchangeDetail(FxExchangeDetailRequest request) throws FlywayApiException {
        return executeWithTypeRef(request, this.fxExchangeDetailPath, new TypeReference<CommonResponse<FxExchangeDetailResponse>>() {
        });
    }
}
