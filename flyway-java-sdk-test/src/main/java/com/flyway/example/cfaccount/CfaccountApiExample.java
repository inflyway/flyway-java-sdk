package com.flyway.example.cfaccount;


import com.flyway.cfaccount.OpenCfaccountApi;
import com.flyway.cfaccount.model.CurrencyBalance;
import com.flyway.cfaccount.model.QueryBalanceRequest;
import com.flyway.common.FlywayConfig;
import com.flyway.common.TokenApi;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;

import java.util.List;

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
public class CfaccountApiExample {

    public static void main(String[] args) {
        example();
    }

    /**
     * 示例
     */
    public static void example() {
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
//
        try {
            // 3： 获取token（有效期2小时） 请使用缓存进行管理，不要频繁调用
            TokenApi tokenApi = new TokenApi(flywayConfig);
            String token = tokenApi.getToken();

            // 4： 声明api对象并调用api
            OpenCfaccountApi cfaccountApi = new OpenCfaccountApi(flywayConfig);
            QueryBalanceRequest queryBalanceRequest = new QueryBalanceRequest();
            queryBalanceRequest.setOpenId("");
            queryBalanceRequest.setToken(token);

            CommonResponse<List<CurrencyBalance>> queryBalanceResponse = cfaccountApi.queryBalance(queryBalanceRequest);
            if (queryBalanceResponse.getCode() == 200) {
                // http == 200 业务code == 200
                // 查询成功：{"code":200,"message":"请求成功","data":[{"currency":"HKD","multiBalance":{"sdbzj":"0","dcz":"0","ye":"0","drz":"0","fx":"0","ffk":null}},{"currency":"EUR","multiBalance":{"sdbzj":"0","dcz":"0","ye":"0","drz":"0","fx":"0","ffk":null}},{"currency":"USD","multiBalance":{"sdbzj":"0","dcz":"0","ye":"0","drz":"0","fx":"0","ffk":"0"}},{"currency":"CNH","multiBalance":{"sdbzj":"0","dcz":"0","ye":"0","drz":"0","fx":"0","ffk":null}},{"currency":"CNY","multiBalance":{"sdbzj":"0","dcz":"0","ye":"0","drz":"0","fx":null,"ffk":null}}]}
                System.out.println("查询成功：" + queryBalanceResponse);
            } else {
                // http == 200   业务code != 200
                // 查询失败：{"code":500,"message":"Id不能为空","data":null}
                System.out.println("查询失败：" + queryBalanceResponse);
            }
        } catch (FlywayApiException e) {
            // http != 200
            System.out.println("查询失败：" + e.getErrorCode() + ":" + e.getMessage());
        }

    }
}