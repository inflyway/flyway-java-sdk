package com.flyway.example.file;

import com.alibaba.fastjson.JSON;
import com.flyway.common.FlywayConfig;
import com.flyway.common.TokenApi;
import com.flyway.common.model.CommonResponse;
import com.flyway.common.model.FileUploadRequest;
import com.flyway.exception.FlywayApiException;
import com.flyway.file.OpenFileApi;
import com.flyway.file.model.FileUrlInfo;

import java.io.File;

public class FileApiExample {



    public static void main(String[] args) {
       example();
    }

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
            flywayConfig.setServerUrl("https://open-test.inflyway.com");
            flywayConfig.setFileServerUrl("https://file-test.inflyway.com");
            flywayConfig.setClientId(clientId);
            flywayConfig.setClientSecret(clientSecret);
            flywayConfig.setAesKey(aesKey);
            flywayConfig.setRsaPrivateKey(rsaPrivateKey);
            flywayConfig.setDebug(true);


            // 3： 获取token（有效期2小时）
            TokenApi tokenApi = new TokenApi(flywayConfig);
            String token = tokenApi.getToken();

            // 4： 声明api对象并调用api
            OpenFileApi openFileApi = new OpenFileApi(flywayConfig);
            FileUploadRequest request = new FileUploadRequest();
            request.setToken(token);
            request.setFile(new File("\\Desktop\\123.jpg"));
            request.setRequestNo("");
            request.setOpenID("");
            request.setBiz("KYC");
            CommonResponse<FileUrlInfo> res = openFileApi.uploadFile(request);
            System.out.println("返回结果: "+JSON.toJSONString(res.getData()));

        } catch ( FlywayApiException e) {
            System.out.println(e.getMessage());
        }
    }





}
