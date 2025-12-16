package com.flyway.example.rate;

import com.alibaba.fastjson.JSON;
import com.flyway.common.FlywayConfig;
import com.flyway.common.TokenApi;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;
import com.flyway.rate.OpenRateApi;
import com.flyway.rate.model.CustomRateItem;
import com.flyway.rate.model.CustomRateRequest;
import com.flyway.rate.model.OpenRateRespose;

import java.util.ArrayList;
import java.util.List;

public class RateApiExample {



    public static void main(String[] args) {

         modify();
        //queryRateList();
    }



    public static void modify() {
        try {
            System.out.println(">> 直接在代码中配置认证信息（带加密和签名）：");

            // 1： 飞来汇openApi配置信息
            String clientId = "";
            String clientSecret = "";
            String aesKey = ""; // 16位AES密钥
            String rsaPrivateKey = "";
            // 2： 设置配置
            FlywayConfig flywayConfig = new FlywayConfig();
            flywayConfig.setServerUrl("https://open-test.inflyway.com");
            flywayConfig.setClientId(clientId);
            flywayConfig.setClientSecret(clientSecret);
            flywayConfig.setAesKey(aesKey);
            flywayConfig.setRsaPrivateKey(rsaPrivateKey);
            flywayConfig.setDebug(true);


            // 3： 获取token（有效期2小时）
            TokenApi tokenApi = new TokenApi(flywayConfig);
            String token = tokenApi.getToken();

            // 4： 声明api对象并调用api
            OpenRateApi openRateApi = new OpenRateApi(flywayConfig);
            CustomRateRequest request = new CustomRateRequest();
            List<CustomRateItem> rateList=new ArrayList<CustomRateItem>();
            CustomRateItem rate1 = new CustomRateItem();
            rate1.setBizCode("FT");
            rate1.setParameter1Code("LP");
            rate1.setParameter2Code("PHL");
            rate1.setParameter3Code("PH_AUB");
            rate1.setParameter4Code("PHP");
            rate1.setParameter5Code("ALL");
            rate1.setRateLevel("V5");
            rateList.add(rate1);
            CustomRateItem rate2 = new CustomRateItem();
            rate2.setBizCode("FT");
            rate2.setParameter1Code("LP");
            rate2.setParameter2Code("IDL");
            rate2.setParameter3Code("ID_PERMATA");
            rate2.setParameter4Code("IDR");
            rate2.setParameter5Code("ALL");
            rate2.setRateLevel("V2");
            rateList.add(rate2);
//            request.setCustomRateList(rateList);
            request.setToken(token);
            request.setFlyLevel("V1");
            request.setRequestNo("14124312");

            request.setOpenID("");
            CommonResponse res = openRateApi.modify(request);
            System.out.println("返回结果: "+JSON.toJSONString(res));

        } catch ( FlywayApiException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void queryRateList() {
        try {
            System.out.println(">> 直接在代码中配置认证信息（带加密和签名）：");

            // 1： 飞来汇openApi配置信息
            String clientId = "";
            String clientSecret = "";
            String aesKey = ""; // 16位AES密钥
            String rsaPrivateKey = "";
            // 2： 设置配置
            FlywayConfig flywayConfig = new FlywayConfig();
            flywayConfig.setServerUrl("https://open-test.inflyway.com");
            flywayConfig.setClientId(clientId);
            flywayConfig.setClientSecret(clientSecret);
            flywayConfig.setAesKey(aesKey);
            flywayConfig.setRsaPrivateKey(rsaPrivateKey);
            flywayConfig.setDebug(true);


            // 3： 获取token（有效期2小时）
            TokenApi tokenApi = new TokenApi(flywayConfig);
            String token = tokenApi.getToken();

            // 4： 声明api对象并调用api
            OpenRateApi openRateApi = new OpenRateApi(flywayConfig);
            CustomRateRequest request = new CustomRateRequest();
            request.setBizCode("FX");
            request.setToken(token);
            request.setRequestNo("14124312");
            request.setOpenID("");
            CommonResponse<OpenRateRespose> res = openRateApi.customRateList(request);
            System.out.println("返回结果: "+JSON.toJSONString(res));

        } catch ( FlywayApiException e) {
            System.out.println(e.getMessage());
        }


    }



}
