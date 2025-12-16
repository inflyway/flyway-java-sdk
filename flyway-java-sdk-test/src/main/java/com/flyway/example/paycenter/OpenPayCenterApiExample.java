package com.flyway.example.paycenter;


import com.flyway.common.FlywayConfig;
import com.flyway.common.TokenApi;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;
import com.flyway.paycenter.OpenPayCenterApi;
import com.flyway.paycenter.model.request.FundflowQryRequest;
import com.flyway.paycenter.model.response.FundflowQryResponse;

/**
 *
 *
 * @author youjunbin
 * @since 2025-12-12 12:56:04
 */
public class OpenPayCenterApiExample {

    public static void main(String[] args) {
        try {
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

            // 3： 获取token（有效期2小时）,获取token 后建议缓存，不要频繁调用
            TokenApi tokenApi = new TokenApi(flywayConfig);
            String token = tokenApi.getToken();
            System.out.println("获取到的访问令牌: " + token);

            // 4： 声明api对象并调用api
            OpenPayCenterApi payCenterApi = new OpenPayCenterApi(flywayConfig);

            FundflowQryRequest request = new FundflowQryRequest();
            request.setFlowId("");
            request.setOpenId("");
            request.setToken(token);
            CommonResponse<FundflowQryResponse> response = payCenterApi.fundflowQuery(request);
            System.out.println("资金到账查询响应: " + response.toString());

        } catch (FlywayApiException e) {
            System.err.println("API 调用失败: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("测试执行失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}