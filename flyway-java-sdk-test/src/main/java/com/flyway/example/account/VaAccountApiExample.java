package com.flyway.example.account;


import com.flyway.account.OpenVaAccountApi;
import com.flyway.account.model.VaAccountInfoDto;
import com.flyway.account.model.VaAccountOpenRequest;
import com.flyway.account.model.VaAccountQueryRequest;
import com.flyway.common.FlywayConfig;
import com.flyway.common.TokenApi;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;

import java.math.BigDecimal;
import java.util.Collections;

/**
 * Inflyway API调用示例
 * <p>
 * 使用前请先配置OAuth2认证信息：
 * <p>
 * 方式1：修改配置文件 src/main/resources/inflyway-sdk.properties
 * inflyway.oauth2.client.id=您的实际client_id
 * inflyway.oauth2.client.secret=您的实际client_secret
 * <p>
 * 方式：直接在代码中配置（参见 example 方法）
 * <p>
 * 注意：如果没有正确配置认证信息，会出现401错误："未携带token"
 */
public class VaAccountApiExample {

    public static void main(String[] args) {
        openVaAccountExample();
//        queryVaAccountResultExample();
    }

    /**
     * va开户申请示例
     */
    public static void openVaAccountExample() {
        try {
            System.out.println(">> 直接在代码中配置认证信息（带加密和签名）：");

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

            // 4： 声明api对象并调用api
            OpenVaAccountApi vaAccountApi = new OpenVaAccountApi(flywayConfig);
            
            VaAccountOpenRequest request = new VaAccountOpenRequest();
            request.setRequestNo("");
            request.setOpenID("");
            request.setBankCardNo("");
            request.setNoticeUrl("https://crooked-babushka.info/");

            //bankCardNo in (10000000000016,10000000000017,10000000000018,10000000000011，10000000000012,10000000000014)
            VaAccountOpenRequest.SupplementInformation supplementInfo = new VaAccountOpenRequest.SupplementInformation();
            supplementInfo.setMainSalesRegion(Collections.singletonList(
                    "CHN"
            ));

            //bankCardNo in (10000000000011，10000000000012,10000000000014)
            supplementInfo.setIndustry("OVERSEASSHOP");
            supplementInfo.setAnnualRemittanceAmount(new BigDecimal("50000.09"));
            supplementInfo.setAverageRemittanceAmount(new BigDecimal("100.83"));
            
            request.setSupplementInformation(supplementInfo);
            request.setToken(token);

            CommonResponse<Void> response = vaAccountApi.openAccount(request);

            // 5： 输出返回结果
            System.out.println("开户返回结果: " + response);


        } catch (FlywayApiException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 查询va开户结果示例
     */
    public static void queryVaAccountResultExample() {
        try {
            System.out.println(">> 查询VA开户结果：");

            // 1： 飞来汇openApi配置信息（同上）
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
            OpenVaAccountApi vaAccountApi = new OpenVaAccountApi(flywayConfig);
            
            VaAccountQueryRequest request = new VaAccountQueryRequest();
            request.setRequestNo("");
            request.setBankCardNo("");
            request.setOpenID("");
            request.setToken(token);

            CommonResponse<VaAccountInfoDto> response = vaAccountApi.queryAccount(request);

            // 5： 输出返回结果
            System.out.println("查询返回结果: " + response);


        } catch (FlywayApiException e) {
            throw new RuntimeException(e);
        }
    }
}