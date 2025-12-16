package com.flyway.example.withdraw;

import com.flyway.common.FlywayConfig;
import com.flyway.common.TokenApi;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;
import com.flyway.withdraw.OpenWithdrawApi;
import com.flyway.withdraw.model.ApplyWithdrawRequest;
import com.flyway.withdraw.model.ApplyWithdrawResponseDto;
import com.flyway.withdraw.model.QueryWithdrawStatusRequest;
import com.flyway.withdraw.model.QueryWithdrawStatusResponseDto;
import com.flyway.withdraw.model.TrailAmountRequest;
import com.flyway.withdraw.model.TrailAmountResponseDto;
import com.flyway.withdraw.model.UpdateCardRepaymentRequest;
import com.flyway.withdraw.model.WithdrawAmountDto;
import com.flyway.withdraw.model.WithdrawAmountRequest;

import java.math.BigDecimal;

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
public class WithdrawApiExample {

    public static void main(String[] args) {
       //queryWithdrawAmountExample();
       // trailAmountExample();
         // applyWithdrawExample();
        //  queryWithdrawStatusExample();
        updateCardRepaymentExample();
    }

    /**
     * 查询可提现金额示例
     */
    public static void queryWithdrawAmountExample() {
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
            OpenWithdrawApi withdrawApi = new OpenWithdrawApi(flywayConfig);

            WithdrawAmountRequest request = new WithdrawAmountRequest();
            request.setOpenID("");
            request.setWithdrawCurrency("USD");
            request.setToken(token);

            CommonResponse<WithdrawAmountDto> response = withdrawApi.queryWithdrawAmt(request);

            // 5： 输出返回结果
            System.out.println("查询可提现金额返回结果: " + response);

        } catch (FlywayApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提现到账金额试算示例
     */
    public static void trailAmountExample() {
        try {
            System.out.println(">> 提现到账金额试算示例：");

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
            OpenWithdrawApi withdrawApi = new OpenWithdrawApi(flywayConfig);

            TrailAmountRequest request = new TrailAmountRequest();
            request.setRequestNo("20251212000001");
            request.setOpenID("");
            request.setScene(9);
            request.setWithdrawCurrency("USD");
            request.setTrailAmount(new BigDecimal("30"));
            request.setPayeeBankCountry("USA");
            request.setToken(token);

            CommonResponse<TrailAmountResponseDto> response = withdrawApi.trailAmount(request);

            // 5： 输出返回结果
            System.out.println("提现到账金额试算返回结果: " + response);

        } catch (FlywayApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提现申请示例
     */
    public static void applyWithdrawExample() {
        try {
            System.out.println(">> 提现申请示例：");

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
            OpenWithdrawApi withdrawApi = new OpenWithdrawApi(flywayConfig);

            ApplyWithdrawRequest request = new ApplyWithdrawRequest();
            request.setRequestNo("SDKtest-sct000002");
            request.setOpenID("");
            request.setTrailId("FK20251215105627000007");
            request.setScene(9);
            request.setWithdrawCurrency("USD");
            request.setAccountDeductionAmt(new BigDecimal("30"));
            request.setWithdrawFee(new BigDecimal("12"));
            request.setArriveAmt(new BigDecimal("18"));
            request.setCallBackUrl("https://callback.example.com/");
            request.setIsPobo(false);
            //request.setPaymentPurpose("PAYMENT_COMMISSION");

            // 设置收款人信息
            ApplyWithdrawRequest.PayeeInfo payeeInfo = new ApplyWithdrawRequest.PayeeInfo();
            payeeInfo.setPayeeName("Lu Tong");
            payeeInfo.setPayeeAccountNo("4444455555666611");
            payeeInfo.setPayeeAccountType(1);
            payeeInfo.setPayeeBankCardCurrency("USD");
            payeeInfo.setPayeeOpenBank("BANK");
            payeeInfo.setPayeeBankCountry("USA");
            payeeInfo.setPayeeBankNumber("60");
            /*payeeInfo.setPayeeBankProvince("甘肃省");
            payeeInfo.setPayeeBankCity("Los Angeles");
            payeeInfo.setPayeeBankSubBranchName("Downtown Branch");*/
            payeeInfo.setPayeeMobile("15865406082");
            //payeeInfo.setPayeeIdNo("ID123456789");
            payeeInfo.setPayeeAddress("ABCAACCVV");
            payeeInfo.setPayeeBankAddress("456 Bank St, Los Angeles, CA");
            payeeInfo.setSwiftCode("CONNUS31XXX");
//            payeeInfo.setRoutingNumber("464042293");
//            payeeInfo.setPayeeProvinceId("CA");
//            payeeInfo.setPayeeProvince("California");
//            payeeInfo.setPayeeCity("Los Angeles");
//            payeeInfo.setPostCode("90001");
//            payeeInfo.setBranchNumber("BR123456");
            request.setPayeeInfo(payeeInfo);

            request.setToken(token);

            CommonResponse<ApplyWithdrawResponseDto> response = withdrawApi.applyWithdraw(request);

            // 5： 输出返回结果
            System.out.println("提现申请返回结果: " + response);

        } catch (FlywayApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提现状态查询示例
     */
    public static void queryWithdrawStatusExample() {
        try {
            System.out.println(">> 提现状态查询示例：");

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
            OpenWithdrawApi withdrawApi = new OpenWithdrawApi(flywayConfig);

            QueryWithdrawStatusRequest request = new QueryWithdrawStatusRequest();
            request.setRequestNo("SDKtest-sct000002");
            request.setOpenID("");
            request.setToken(token);

            CommonResponse<QueryWithdrawStatusResponseDto> response = withdrawApi.queryWithdrawStatus(request);

            // 5： 输出返回结果
            System.out.println("提现状态查询返回结果: " + response);

        } catch (FlywayApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提现CNY失败换卡重发示例
     */
    public static void updateCardRepaymentExample() {
        try {
            System.out.println(">> 提现CNY失败换卡重发示例：");

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
            OpenWithdrawApi withdrawApi = new OpenWithdrawApi(flywayConfig);

            UpdateCardRepaymentRequest request = new UpdateCardRepaymentRequest();
            request.setRequestNo("SCTtest000006");
            request.setOpenID("");

            // 设置收款人信息
            ApplyWithdrawRequest.PayeeInfo payeeInfo = new ApplyWithdrawRequest.PayeeInfo();
            payeeInfo.setPayeeName("鲁通");
            payeeInfo.setPayeeAccountNo("6214831059819083");
            payeeInfo.setPayeeAccountType(1);
            payeeInfo.setPayeeBankCardCurrency("CNY");
            payeeInfo.setPayeeOpenBank("Bank of China");
            payeeInfo.setPayeeBankCountry("CHN");
            payeeInfo.setPayeeBankNumber("60");
            payeeInfo.setPayeeBankProvince("北京市");
            payeeInfo.setPayeeBankCity("朝阳区");
            payeeInfo.setPayeeBankSubBranchName("朝阳区");
            payeeInfo.setPayeeMobile("13520115975");
            payeeInfo.setPayeeIdNo("420923199508144375");
            payeeInfo.setPayeeAddress("ABCAACCVV");
            payeeInfo.setPayeeBankAddress("789 Bank St, Beijing");
            payeeInfo.setSwiftCode("CONNUS31XXX");
            payeeInfo.setRoutingNumber("987654321");
            payeeInfo.setPayeeProvinceId("BJ");
            payeeInfo.setPayeeProvince("Beijing");
            payeeInfo.setPayeeCity("Beijing");
            payeeInfo.setPostCode("100000");
            payeeInfo.setBranchNumber("BR987654");
            request.setPayeeInfo(payeeInfo);

            request.setToken(token);

            CommonResponse<Void> response = withdrawApi.updateCardRepayment(request);

            // 5： 输出返回结果
            System.out.println("提现CNY失败换卡重发返回结果: " + response);

        } catch (FlywayApiException e) {
            throw new RuntimeException(e);
        }
    }
}