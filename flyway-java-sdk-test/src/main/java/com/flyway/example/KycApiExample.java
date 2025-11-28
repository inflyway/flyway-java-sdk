package com.flyway.example;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.flyway.common.FlywayConfig;
import com.flyway.common.TokenApi;
import com.flyway.exception.FlywayApiException;
import com.flyway.kyc.OpenKYCApi;
import com.flyway.kyc.model.KycRequest;
import com.flyway.kyc.model.KycResponse;
import com.flyway.kyc.model.QueryKycRequest;
import com.flyway.kyc.model.QueryKycResponse;

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
public class KycApiExample {

	 public static void main(String[] args) {
	        try {
	            // 1. 配置 Flyway SDK
	            FlywayConfig config = new FlywayConfig();
	            config.setServerUrl("https://open-test.inflyway.com"); // 测试环境地址
	            config.setClientId("c2X0Are0xNvbjDjC");           // 替换为实际的 client_id
	            config.setClientSecret("ycE92NevW6J0cQThLNsb");   // 替换为实际的 client_secret
	            config.setAesKey("914y7EaGyJwlC27M");             // 替换为16位 AES 密钥
	            config.setRsaPrivateKey("MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAI7vV39viNlmD1dX/9NhzePIJFcbzeMFyrZSt0xwMUYyjhiFJPnoGIVyG0pssXiMbzA8FBZuiKMeiZjc8SjrWAMg2gHDjIWzhUuo3puMqZR21nC+2KA/b8j0HsQ9PTDbHdcKyHYIYNAbp/SRRPIZOvVqt4+Z4COrfv4JccosIWkxAgMBAAECgYEAiUVbJswbBY561UtyKbQYY9Xm8LGHPaxmTkuKNiLZb61Fwk68gDVit2Yqx4Mzva5BanWIZTKqjt3ZD7HA+adr0cWDQiwzBnmZys33uN6jm8kLtU1KFLblIIx10X1mppcZPQRDRbBy7tVCOoa/dEzynH0Rt1djgZszDBJhIA3MaAECQQDIHHjuRq4peWz4KaSp16Pg4ONWD2GTi74FLXwflC5JSljN6axbzON1aqD0CAv29KtcwvBR55+MOT+vvcuSLMlRAkEAttriygaXd7F9ylIuUp8zpVX8+zKQro0uq8Gar8JoHdNYoghZLAOVrAses7KtbuRTMwIoHn0CtC8l8tOQTH6p4QJBAIP34vigvDK11VtDe0hW4choBwS2WA9J1SLtADKDMpM66J3DQNu5nzfL/iFxPRK8AFbIaFxbeCitiIaJkDryNkECQQCnc2ucdsze37vCO+AP6ZryHfy+TWAReVj0ESgHLJEMPy87s0l19RJrqwNCrK4GjzFh1OfIg9KmD0dBSF0ssBIBAkBhG+N0rbS6CyCjaOa5DncsLgcppAaOKr6L9lyruwvtBaT1CiJY4w/z6zxJ/Qy+Nx8ObVbL3t94DondW+8c5IWl");       // 替换为 RSA 私钥
	            config.setDebug(true);

	            // 2. 获取访问令牌
	            TokenApi tokenApi = new TokenApi(config);
	            String token = tokenApi.getToken();
	            System.out.println("获取到的访问令牌: " + token);

	            // 3. 初始化 KYC API
	            OpenKYCApi kycApi = new OpenKYCApi(config);

	            
	            String ss="{\r\n" + 
	            		"    \"requestNo\": \"123430\",\r\n" + 
	            		"    \"applicantPhone\": \"13922417235\",\r\n" + 
	            		"    \"applicantEmail\": \"139@XXXXXXXX.COM\",\r\n" + 
	            		"    \"mainSalesRegion\": {\r\n" + 
	            		"        \"405\": \"其他\"\r\n" + 
	            		"    },\r\n" + 
	            		"    \"mainProductClass\": {\r\n" + 
	            		"        \"320\": \"其他\"\r\n" + 
	            		"    },\r\n" + 
	            		"    \"businessLicense\": \"1689058088715_9b00c48f345a3b13af60ce1322fa94df.pdf\",\r\n" + 
	            		"    \"companyEnName\": \"22GEDHEALTHINOW PTE. LTD.\",\r\n" + 
	            		"    \"companyName\": \"22GEDHEALTHINOW PTE. LTD.\",\r\n" + 
	            		"    \"businessEnAddress\": \"315 OUTRAM ROAD #15-08 TAN BOON LIAT BUILDING SINGAPORE (169074)\",\r\n" + 
	            		"    \"registerAt\": 1683302400000,\r\n" + 
	            		"    \"companyCode\": \"302317548D\",\r\n" + 
	            		"    \"idType\": \"1\",\r\n" + 
	            		"    \"businessAddress\": \"DDDDDD\",\r\n" + 
	            		"    \"country\": \"CHN\",\r\n" + 
	            		"    \"subjectType\": \"1\",\r\n" + 
	            		"    \"noticeUrl\": \"WWWW\",\r\n" + 
	            		"    \"companyDirectorDos\": [\r\n" + 
	            		"        {\r\n" + 
	            		"            \"directorIdNo\": \"A57249601\",\r\n" + 
	            		"            \"directorName\": \"张锦泰\",\r\n" + 
	            		"            \"directorNameEn\": \"Zhang Jing Tai\",\r\n" + 
	            		"            \"gender\": 1,\r\n" + 
	            		"            \"nation\": \"汉\",\r\n" + 
	            		"            \"address\": \"北京市朝阳区六里屯丽水家园6号楼1009\",\r\n" + 
	            		"            \"birthday\": \"1979-12-19\",\r\n" + 
	            		"            \"grantingOffice\": \"UTC PERAK\",\r\n" + 
	            		"            \"nameEn\": \"CHONG KAM THAI\",\r\n" + 
	            		"            \"enAddress\": \"Building 6, Lishui Jiayuan, Liulitun, Chaoyang District, Beijing 1009\",\r\n" + 
	            		"            \"idStartDate\": \"2022-10-08\",\r\n" + 
	            		"            \"idEndDate\": \"2028-04-08\",\r\n" + 
	            		"            \"handheldPhoto\": \"/common/1680861599593_default.jpg\",\r\n" + 
	            		"            \"directorIdType\": 1,\r\n" + 
	            		"            \"directorIdBack\": \"ocr/1689057754288_8a85c78d0f2c9511732b351823f155ae.jpg\",\r\n" + 
	            		"            \"directorIdFront\": \"ocr/1689057826489_1477efe2d4efc8e134c11bace81e8ac7.jpg\",\r\n" + 
	            		"            \"legalMobile\": \"String\"\r\n" + 
	            		"        }\r\n" + 
	            		"    ],\r\n" + 
	            		"    \"companyBeneficiaryDos\": [\r\n" + 
	            		"        {\r\n" + 
	            		"            \"gender\": 1,\r\n" + 
	            		"            \"nation\": \"汉\",\r\n" + 
	            		"            \"address\": \"北京市朝阳区六里屯丽水家园6号楼1009\",\r\n" + 
	            		"            \"birthday\": \"1979-12-19\",\r\n" + 
	            		"            \"frontImageKey\": \"ocr/1684137322185_dfac77e1af95ebee24348546b98c6c94.jpg\",\r\n" + 
	            		"            \"grantingOffice\": \"UTC PERAK\",\r\n" + 
	            		"            \"enAddress\": \"Building 6, Lishui Jiayuan, Liulitun, Chaoyang District, Beijing 1009\",\r\n" + 
	            		"            \"idStartDate\": \"2022-10-08\",\r\n" + 
	            		"            \"idEndDate\": \"2028-04-08\",\r\n" + 
	            		"            \"beneficiaryIdBack\": \"ocr/1689057754288_8a85c78d0f2c9511732b351823f155ae.jpg\",\r\n" + 
	            		"            \"beneficiaryIdFront\": \"ocr/1689057826489_1477efe2d4efc8e134c11bace81e8ac7.jpg\",\r\n" + 
	            		"            \"beneficiaryIdType\": 1,\r\n" + 
	            		"            \"beneficiaryName\": \"张锦泰\",\r\n" + 
	            		"            \"beneficiaryNameEn\": \"CHONG KAM THAI\",\r\n" + 
	            		"            \"beneficiaryIdNo\": \"A57249601\",\r\n" + 
	            		"            \"ratio\": \"100\"\r\n" + 
	            		"        }\r\n" + 
	            		"    ]\r\n" + 
	            		"}";
	            // 添加其他必需的参数...
	         // 4. 执行企业 KYC 认证
	            KycRequest kycRequest = JSON.parseObject(ss,KycRequest.class);
	            // 设置必要的请求参数
	            kycRequest.setToken(token);
	            KycResponse kycResponse = kycApi.enterprise(kycRequest);
	            System.out.println("KYC 认证响应: " + kycResponse);

	            // 5. 查询 KYC 结果
	            QueryKycRequest queryRequest = new QueryKycRequest();
	            // 设置查询条件
	            queryRequest.setToken(token);
	            // 添加查询参数...
	            queryRequest.setRequsestNo("123430");
	            QueryKycResponse queryResponse = kycApi.queryKyc(queryRequest);
	            System.out.println("KYC 查询响应: " + queryResponse);

	        } catch (FlywayApiException e) {
	            System.err.println("API 调用失败: " + e.getMessage());
	            e.printStackTrace();
	        } catch (Exception e) {
	            System.err.println("测试执行失败: " + e.getMessage());
	            e.printStackTrace();
	        }
	    } 
}