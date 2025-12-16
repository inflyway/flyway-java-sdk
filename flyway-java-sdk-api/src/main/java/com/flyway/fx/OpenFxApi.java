package com.flyway.fx;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.LinkedHashMap;
import java.util.List;

/**
 * FX外汇交易API
 */
public class OpenFxApi extends AbstractApi {

    private String fxRateInquiryPath;
    private String fxRateLockPath;
    private String fxCurrencyPairPath;
    private String fxExchangeDetailPath;
    private ObjectMapper objectMapper;

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
        this.objectMapper = new ObjectMapper();
    }

    /**
     * FX汇率查询
     *
     * @param request 汇率查询请求参数
     * @return 汇率查询结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<FxRateInquiryResponse> inquiryRate(FxRateInquiryRequest request) throws FlywayApiException {
        CommonResponse<?> response = execute(request, this.fxRateInquiryPath, CommonResponse.class);
        return convertResponse(response, FxRateInquiryResponse.class);
    }

    /**
     * FX汇率锁汇
     *
     * @param request 汇率锁汇请求参数
     * @return 汇率锁汇结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<FxRateLockResponse> submitRate(FxRateLockRequest request) throws FlywayApiException {
        CommonResponse<?> response = execute(request, this.fxRateLockPath, CommonResponse.class);
        return convertResponse(response, FxRateLockResponse.class);
    }

    /**
     * FX货币对查询
     *
     * @param request 货币对查询请求参数
     * @return 货币对查询结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<List<String>> currencyPair(FxCurrencyPairRequest request) throws FlywayApiException {
        CommonResponse<?> response = executeGet(request, this.fxCurrencyPairPath, CommonResponse.class);
        return convertListResponse(response, String.class);
    }

    /**
     * FX汇兑详情查询
     *
     * @param request 汇兑详情查询请求参数
     * @return 汇兑详情查询结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<FxExchangeDetailResponse> queryExchangeDetail(FxExchangeDetailRequest request) throws FlywayApiException {
        CommonResponse<?> response = execute(request, this.fxExchangeDetailPath, CommonResponse.class);
        return convertResponse(response, FxExchangeDetailResponse.class);
    }
    /**
     * 转换响应对象，解决泛型擦除问题
     */
    @SuppressWarnings("unchecked")
    private <T> CommonResponse<T> convertResponse(CommonResponse<?> response, Class<T> dataType) {
        System.out.println("convertResponse被调用，目标类型: " + dataType.getSimpleName());

        CommonResponse<T> typedResponse = new CommonResponse<>();
        typedResponse.setCode(response.getCode());
        typedResponse.setMessage(response.getMessage());

        Object data = response.getData();
        System.out.println("原始data类型: " + (data != null ? data.getClass() : "null"));

        if (data != null) {
            if (dataType.isInstance(data)) {
                System.out.println("直接转换");
                typedResponse.setData((T) data);
            } else if (data instanceof LinkedHashMap) {
                System.out.println("从LinkedHashMap转换");
                T convertedData = objectMapper.convertValue(data, dataType);
                    typedResponse.setData(convertedData);
            } else {
                System.out.println("通过JSON转换");
                try {
                    String json = objectMapper.writeValueAsString(data);
                    T convertedData = objectMapper.readValue(json, dataType);
                    typedResponse.setData(convertedData);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to convert response data to " + dataType.getSimpleName(), e);
        }
    }
}

        return typedResponse;
    }

    /**
     * 转换List类型响应对象，解决泛型擦除问题
     */
    @SuppressWarnings("unchecked")
    private <T> CommonResponse<List<T>> convertListResponse(CommonResponse<?> response, Class<T> elementType) {
        System.out.println("convertListResponse被调用，元素类型: " + elementType.getSimpleName());

        CommonResponse<List<T>> typedResponse = new CommonResponse<>();
        typedResponse.setCode(response.getCode());
        typedResponse.setMessage(response.getMessage());

        Object data = response.getData();
        System.out.println("原始data类型: " + (data != null ? data.getClass() : "null"));

        if (data != null) {
            if (data instanceof List) {
                System.out.println("直接转换为List");
                typedResponse.setData((List<T>) data);
            } else {
                System.out.println("通过JSON转换为List");
                try {
                    String json = objectMapper.writeValueAsString(data);
                    List<T> convertedData = objectMapper.readValue(json,
                            objectMapper.getTypeFactory().constructCollectionType(List.class, elementType));
                    typedResponse.setData(convertedData);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to convert response data to List<" + elementType.getSimpleName() + ">", e);
                }
            }
        }

        return typedResponse;
    }
}
