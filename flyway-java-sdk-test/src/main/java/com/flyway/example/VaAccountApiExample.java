package com.flyway.example;


import com.flyway.account.OpenVaAccountApi;
import com.flyway.account.model.VaAccountOpenRequest;
import com.flyway.account.model.VaAccountOpenResponse;
import com.flyway.account.model.VaAccountQueryRequest;
import com.flyway.account.model.VaAccountQueryResponse;
import com.flyway.common.FlywayConfig;
import com.flyway.common.TokenApi;
import com.flyway.exception.FlywayApiException;

import java.math.BigDecimal;
import java.util.Arrays;

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
        example();
//        queryExample();
    }

    /**
     * 示例
     */
    public static void example() {
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


            // 3： 获取token（有效期2小时）
            TokenApi tokenApi = new TokenApi(flywayConfig);
            String token = tokenApi.getToken();

            // 4： 声明api对象并调用api
            OpenVaAccountApi vaAccountApi = new OpenVaAccountApi(flywayConfig);
            
            VaAccountOpenRequest request = new VaAccountOpenRequest();
            request.setRequestNo("20251127000001");
            request.setOpenID("223344556677");
            request.setBankCardNo("10000000000006");
            request.setBankAccountName("Visionceler Limited");
            request.setNoticeUrl("https://crooked-babushka.info/");
            
            VaAccountOpenRequest.SupplementInformation supplementInfo = new VaAccountOpenRequest.SupplementInformation();
            supplementInfo.setMainBusinessType(Arrays.asList(
                "ggqk1z_tt6@qq.com",
                "pybsbt_od078@qq.com",
                "k7nggq.sld7@qq.com"
            ));
            supplementInfo.setMainSalesRegion(Arrays.asList(
                "v9blnf.mtl@qq.com",
                "jzzgm3_sp2@qq.com",
                "m8eiuk21@qq.com"
            ));
            supplementInfo.setIndustry("aliqua minim magna irure");
            supplementInfo.setMajorSalesRegion("consectetur magna non tempor");
            supplementInfo.setAnnualRemittanceAmount(new BigDecimal("583.09"));
            supplementInfo.setAverageRemittanceAmount(new BigDecimal("840.83"));
            
//            request.setSupplementInformation(supplementInfo);
            request.setToken(token);
            
            VaAccountOpenResponse response = vaAccountApi.openAccount(request);

            // 5： 输出返回结果
            System.out.println("开户返回结果: " + response);


        } catch (FlywayApiException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 查询示例
     */
    public static void queryExample() {
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
            request.setRequestNo("20251127000001");
            request.setBankCardNo("10000000000006");
            request.setOpenID("223344556677");
            request.setToken(token);
            
            VaAccountQueryResponse response = vaAccountApi.queryAccount(request);

            // 5： 输出返回结果
            System.out.println("查询返回结果: " + response);


        } catch (FlywayApiException e) {
            throw new RuntimeException(e);
        }
    }
}