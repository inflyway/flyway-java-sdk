package com.flyway.example.fx;

import com.flyway.common.FlywayConfig;
import com.flyway.common.TokenApi;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;
import com.flyway.fx.OpenFxApi;
import com.flyway.fx.model.FxCurrencyPairRequest;
import com.flyway.fx.model.FxExchangeDetailRequest;
import com.flyway.fx.model.FxExchangeDetailResponse;
import com.flyway.fx.model.FxRateInquiryRequest;
import com.flyway.fx.model.FxRateInquiryResponse;
import com.flyway.fx.model.FxRateLockRequest;
import com.flyway.fx.model.FxRateLockResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * FX外汇交易API调用示例
 * <p>
 * 使用前请先配置OAuth2认证信息：
 * <p>
 * 方式1：修改配置文件 src/main/resources/inflyway-sdk.properties
 * inflyway.oauth2.client.id=您的实际client_id
 * inflyway.oauth2.client.secret=您的实际client_secret
 * <p>
 * 方式2：直接在代码中配置（参见 example 方法）
 * <p>
 * 注意：如果没有正确配置认证信息，会出现401错误："未携带token"
 */
public class OpenFxApiExample {

    static FlywayConfig buildFlywayConfig() {
        // 1： 飞来汇openApi配置信息
        String clientId = "";
        String clientSecret = "";
        String aesKey = ""; // 16位AES密钥
        String rsaPrivateKey = "";
        // 2： 设置配置
        FlywayConfig flywayConfig = new FlywayConfig();
        flywayConfig.setServerUrl("https://open-test.inflyway.com");//测试环境地址
        flywayConfig.setClientId(clientId);
        flywayConfig.setClientSecret(clientSecret);
        flywayConfig.setAesKey(aesKey);
        flywayConfig.setRsaPrivateKey(rsaPrivateKey);
        flywayConfig.setDebug(true);
        return flywayConfig;
    }

    public static void main(String[] args) {
        try {
            System.out.println("=== 开始执行FX API示例 ===");
            // 【支持即时汇兑的货币对查询】示例
//            fxCurrencyPairExample();

            System.out.println("\n=== 分隔线 ===\n");
            //【即时换汇汇率查询】示例
//            fxRateInquiryExample();
            System.out.println("\n=== 分隔线 ===\n");

            // 【即时换汇申请】示例 注意：由于询汇编号有时效性，所以该示例先模拟询汇，拿到询汇编号后再执行换汇申请
             fxRateLockExample();
            System.out.println("\n=== 分隔线 ===\n");

            //【即时换汇申请详情查询】 示例
//            queryExchangeDetailExample();

            System.out.println("=== FX API示例执行完成 ===");
        } catch (Exception e) {
            System.err.println("执行示例时发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * FX汇率查询示例
     */
    public static void fxRateInquiryExample() {
        try {
            System.out.println(">> 直接在代码中配置认证信息（带加密和签名）：");

            FlywayConfig flywayConfig = buildFlywayConfig();

            // 3： 获取token（有效期2小时）,获取token 后建议缓存，不要频繁调用
            TokenApi tokenApi = new TokenApi(flywayConfig);
            String token = tokenApi.getToken();

            // 4： 声明api对象并调用api
            OpenFxApi fxApi = new OpenFxApi(flywayConfig);
            
            FxRateInquiryRequest request = new FxRateInquiryRequest();
            request.setOpenID("");
            request.setSellCurrency("USD");
            request.setBuyCurrency("CNH");
            request.setAmount(new BigDecimal("1000.01"));
            request.setRequestNo("FX_INQUIRY_" + UUID.randomUUID().toString());
            request.setToken(token);

            CommonResponse<FxRateInquiryResponse> response = fxApi.inquiryRate(request);

            // 5： 输出返回结果
            System.out.println("汇率查询返回结果: " + response);

        } catch (FlywayApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * FX汇兑详情查询示例
     */
    public static void queryExchangeDetailExample() {
        try {
            System.out.println(">> FX汇兑详情查询：");

            FlywayConfig flywayConfig = buildFlywayConfig();

            // 3： 获取token（有效期2小时）
            TokenApi tokenApi = new TokenApi(flywayConfig);
            String token = tokenApi.getToken();

            // 4： 声明api对象并调用api
            OpenFxApi fxApi = new OpenFxApi(flywayConfig);

            FxExchangeDetailRequest request = new FxExchangeDetailRequest();
            request.setOpenID("");
            request.setSerialNumber("EX1999412999501287426");
//            request.setRequestNo(1748707200000L);
            request.setToken(token);

            System.out.println("发送汇兑详情查询请求: " + request);
            CommonResponse<FxExchangeDetailResponse> response = fxApi.queryExchangeDetail(request);

            // 5： 输出返回结果
            System.out.println("汇兑详情查询返回结果: " + response);
            System.out.println("response.getData()类型: " + (response.getData() != null ? response.getData().getClass() : "null"));

            if (response != null && response.getData() != null) {
                FxExchangeDetailResponse data = response.getData();
                System.out.println("查询成功!");
                System.out.println("- 订单ID: " + data.getId());
                System.out.println("- 流水号: " + data.getSerialNumber());
                System.out.println("- 业务编号: " + data.getBizNo());
                System.out.println("- 卖出币种: " + data.getSellCurrency());
                System.out.println("- 买入币种: " + data.getBuyCurrency());
                System.out.println("- 卖出金额: " + data.getSellAmount());
                System.out.println("- 买入金额: " + data.getBuyAmount());
                System.out.println("- 订单状态: " + data.getStatus());
                System.out.println("- 汇率: " + data.getRate());
                System.out.println("- 交易时间: " + data.getExchangeTime());
                System.out.println("- 计划结算日期: " + data.getPlanSettleDate());
                if (data.getReason() != null) {
                    System.out.println("- 失败原因: " + data.getReason());
                }
            }

        } catch (FlywayApiException e) {
            System.err.println("API调用异常: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("其他异常: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * FX货币对查询示例
     */
    public static void fxCurrencyPairExample() {
        try {
            System.out.println(">> FX货币对查询：");

            // 1： 飞来汇openApi配置信息
            String clientId = "";
            String clientSecret = "";
            String aesKey = ""; // 16位AES密钥
            String rsaPrivateKey = "";

            // 2： 设置配置
            FlywayConfig flywayConfig = new FlywayConfig();
            flywayConfig.setServerUrl("https://open-test.inflyway.com");//测试环境地址
            flywayConfig.setClientId(clientId);
            flywayConfig.setClientSecret(clientSecret);
            flywayConfig.setAesKey(aesKey);
            flywayConfig.setRsaPrivateKey(rsaPrivateKey);
            flywayConfig.setDebug(true);

            // 3： 获取token（有效期2小时）
            TokenApi tokenApi = new TokenApi(flywayConfig);
            String token = tokenApi.getToken();

            // 4： 声明api对象并调用api
            OpenFxApi fxApi = new OpenFxApi(flywayConfig);

            FxCurrencyPairRequest request = new FxCurrencyPairRequest();
            request.setToken(token);

            CommonResponse<List<String>> response = fxApi.currencyPair(request);
            // 5： 输出返回结果
            System.out.println("货币对查询返回结果: " + response);

            if (response != null && response.getData() != null) {
                System.out.println("支持的货币对数量: " + response.getData().size());
                System.out.println("货币对列表: " + response.getData());
            }

        } catch (FlywayApiException e) {
            System.err.println("API调用异常: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("其他异常: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * FX汇率锁汇示例
     */
    public static void fxRateLockExample() {
        try {
            System.out.println(">> FX汇率锁汇：");

            FlywayConfig flywayConfig = buildFlywayConfig();

            // 3： 获取token（有效期2小时）
            TokenApi tokenApi = new TokenApi(flywayConfig);
            String token = tokenApi.getToken();

            // 4： 声明api对象并调用api
            OpenFxApi fxApi = new OpenFxApi(flywayConfig);

            String openId = "";
            FxRateInquiryRequest request = new FxRateInquiryRequest();
            request.setOpenID(openId);
            request.setSellCurrency("USD");
            request.setBuyCurrency("CNH");
            request.setAmount(new BigDecimal("40.01"));
            request.setRequestNo("FX_INQUIRY_"+  UUID.randomUUID().toString());
            request.setToken(token);
            CommonResponse<FxRateInquiryResponse> response = fxApi.inquiryRate(request);

            // 5： 输出返回结果
            System.out.println("汇率查询返回结果: " + response);
            System.out.println("response.getData()类型: " + response.getData().getClass());

            FxRateInquiryResponse data1 = response.getData();
            String inquiryNo = data1.getInquiryNo();
            System.out.println("获取到的询汇编号: " + inquiryNo);

            FxRateLockRequest lockrequest = new FxRateLockRequest();
            lockrequest.setRequestNo("FX_LOCK_" + UUID.randomUUID().toString());
            lockrequest.setOpenID(openId);
            lockrequest.setInquiryNo(inquiryNo);
            lockrequest.setSellAmount("40.01");
            lockrequest.setCallback("https://www.baodu.com");
            lockrequest.setToken(token);

            CommonResponse<FxRateLockResponse> lockresponse = fxApi.submitRate(lockrequest);

            if (lockresponse != null && lockresponse.getData() != null) {
                FxRateLockResponse data = lockresponse.getData();
                System.out.println("查询成功!");
                System.out.println("- 订单ID: " + data.getId());
                System.out.println("- 流水号: " + data.getSerialNumber());
                System.out.println("- 卖出币种: " + data.getSellCurrency());
                System.out.println("- 买入币种: " + data.getBuyCurrency());
                System.out.println("- 卖出金额: " + data.getSellAmount());
                System.out.println("- 买入金额: " + data.getBuyAmount());
                System.out.println("- 订单状态: " + data.getStatus());
                System.out.println("- 汇率: " + data.getRate());
                System.out.println("- 交易时间: " + data.getExchangeTime());
                System.out.println("- 计划结算日期: " + data.getPlanSettleDate());
                if (data.getReason() != null) {
                    System.out.println("- 失败原因: " + data.getReason());
                }
            }

            // 5： 输出返回结果
            System.out.println("锁汇返回结果: " + lockresponse);

        } catch (FlywayApiException e) {
            throw new RuntimeException(e);
        }
    }
}
