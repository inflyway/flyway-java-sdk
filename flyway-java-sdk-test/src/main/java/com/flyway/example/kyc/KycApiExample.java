package com.flyway.example.kyc;


import com.alibaba.fastjson.JSON;
import com.flyway.common.FlywayConfig;
import com.flyway.common.TokenApi;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;
import com.flyway.kyc.OpenKYCApi;
import com.flyway.kyc.model.CompanyBeneficiary;
import com.flyway.kyc.model.CompanyDirector;
import com.flyway.kyc.model.KycCo;
import com.flyway.kyc.model.KycRequest;
import com.flyway.kyc.model.KycResultInfoCo;
import com.flyway.kyc.model.QueryKycRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            //大陆企业， MAINLAND, HKG
            Kyc("MAINLAND");
            //查询结果
            queryKyc();
        } catch (FlywayApiException e) {
            System.err.println("API 调用失败: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("测试执行失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void Kyc(String kycType) throws FlywayApiException {
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
        OpenKYCApi kycApi = new OpenKYCApi(flywayConfig);

        if (kycType.equalsIgnoreCase("MAINLAND")) {
            //完成大陆企业kyc
            KycRequest chinakycRequest = initChinaKycRequest();
            testKycSdk(token, kycApi, chinakycRequest);
        } else if (kycType.equalsIgnoreCase("HKG")) {
            //完成香港企业kyc
            KycRequest hkkycRequest = initHkKycRequest();
            testKycSdk(token, kycApi, hkkycRequest);
        } else {
            System.out.println("请输入正确的kyc类型");
        }
    }

    private static void queryKyc() throws FlywayApiException {
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
        OpenKYCApi kycApi = new OpenKYCApi(flywayConfig);
        QueryKycRequest queryRequest = new QueryKycRequest();
        queryRequest.setToken(token);
        queryRequest.setRequestNo("1122322230");
        CommonResponse<KycResultInfoCo> queryResponse = kycApi.queryKyc(queryRequest);
        System.out.println("KYC 查询响应: " + JSON.toJSONString(queryResponse));
    }

    private static void testKycSdk(String token, OpenKYCApi kycApi, KycRequest kycRequest) throws FlywayApiException {
        // 设置必要的请求参数
        kycRequest.setToken(token);
        CommonResponse<KycCo> kycResponse = kycApi.enterprise(kycRequest);
        System.out.println("KYC 认证响应: " + JSON.toJSONString(kycResponse));
    }

    private static KycRequest initChinaKycRequest() {
        KycRequest kycRequest = new KycRequest();

        // 设置基本信息
        kycRequest.setRequestNo("1122322230");
        kycRequest.setApplicantPhone("136****7235");
        kycRequest.setApplicantEmail("139@XXXXXXXX.COM");
        kycRequest.setCompanyCode("9133****** 5G89");
        kycRequest.setIdType("1");
        kycRequest.setBusinessLicense("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        kycRequest.setCompanyEnName("******THINOW PTE. LTD.");
        kycRequest.setCompanyName("******饰品有限公司");
        kycRequest.setSubjectType(1);
        kycRequest.setRegisterAt("2022-10-08");
        kycRequest.setCountry("CHN");
        kycRequest.setBusinessAddress("DDDDDD");
        kycRequest.setBusinessEnAddress("315 OUTRAM ****** BUILDING SINGAPORE (169074)");
        kycRequest.setNoticeUrl("WWWW");

        // 设置销售区域和产品类别
        Map<String, String> salesRegion = new HashMap<>();
        salesRegion.put("405", "其他");
        kycRequest.setMainSalesRegion(salesRegion);

        Map<String, String> productClass = new HashMap<>();
        productClass.put("320", "其他产品");
        kycRequest.setMainProductClass(productClass);

        // 设置董事信息
        List<CompanyDirector> directorList = new ArrayList<>();
        CompanyDirector director = new CompanyDirector();
        director.setKycType(2);
        director.setDirectorIdNo("441******3814");
        director.setDirectorName("**庆");
        director.setDirectorNameEn("*** *** Qing");
        director.setGender(1);
        director.setCountry("CHN");
        director.setNation("汉");
        director.setAddress("*********家园6号楼1009");
        director.setBirthday("1979-12-19");
        director.setGrantingOffice("UTC PERAK");
        director.setEnAddress("Building 6, Lishui Jiayuan, Liulitun, Chaoyang District, Beijing 1009");
        director.setIdStartDate("2022-10-08");
        director.setIdEndDate("2028-04-08");
        director.setHandheldPhoto("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        director.setDirectorIdType("1");
        director.setDirectorIdBack("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        director.setDirectorIdFront("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        director.setLegalMobile("139****7525");

        List<String> facePhotos = Arrays.asList(
                "company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG",
                "company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG",
                "company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG",
                "company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG",
                "company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG",
                "company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG"
        );
        director.setFacePhoto(facePhotos);

        directorList.add(director);
        kycRequest.setCompanyDirectorDos(directorList);

        // 设置受益人信息
        List<CompanyBeneficiary> beneficiaryList = new ArrayList<>();

        // 第一个受益人
        CompanyBeneficiary beneficiary1 = new CompanyBeneficiary();
        beneficiary1.setKycType(4);
        beneficiary1.setGender(1);
        beneficiary1.setNation("汉");
        beneficiary1.setAddress("*********家园6号楼1009");
        beneficiary1.setBirthday("1979-12-19");
        beneficiary1.setCountry("CHN");

        beneficiary1.setGrantingOffice("UTC PERAK");
        beneficiary1.setEnAddress("Building 6, Lishui Jiayuan, Liulitun, Chaoyang District, Beijing 1009");
        beneficiary1.setIdStartDate("2022-10-08");
        beneficiary1.setIdEndDate("2028-04-08");
        beneficiary1.setBeneficiaryIdBack("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        beneficiary1.setBeneficiaryIdFront("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        beneficiary1.setBeneficiaryIdType("1");
        beneficiary1.setBeneficiaryName("**庆");
        beneficiary1.setBeneficiaryNameEn("*** *** Qing");
        beneficiary1.setBeneficiaryIdNo("441******3814");
        beneficiary1.setRatio("50");

        // 第二个受益人
        CompanyBeneficiary beneficiary2 = new CompanyBeneficiary();
        beneficiary2.setKycType(4);
        beneficiary2.setGender(1);
        beneficiary2.setNation("汉");
        beneficiary2.setAddress("*********家园6号楼1009");
        beneficiary2.setBirthday("1979-12-19");
        beneficiary2.setCountry("CHN");

        beneficiary2.setGrantingOffice("UTC PERAK");
        beneficiary2.setEnAddress("Building 6, Lishui Jiayuan, Liulitun, Chaoyang District, Beijing 1009");
        beneficiary2.setIdStartDate("2022-10-08");
        beneficiary2.setIdEndDate("2028-04-08");
        beneficiary2.setBeneficiaryIdBack("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        beneficiary2.setBeneficiaryIdFront("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        beneficiary2.setBeneficiaryIdType("1");
        beneficiary2.setBeneficiaryName("**庆");
        beneficiary2.setBeneficiaryNameEn("*** *** Qing");
        beneficiary2.setBeneficiaryIdNo("4417*** ***3814");
        beneficiary2.setRatio("50");

        beneficiaryList.add(beneficiary1);
        beneficiaryList.add(beneficiary2);
        kycRequest.setCompanyBeneficiaryDos(beneficiaryList);

        return kycRequest;
    }

    private static KycRequest initHkKycRequest() {

        KycRequest kycRequest = new KycRequest();

        // 设置基本信息
        kycRequest.setRequestNo("15534122");
        kycRequest.setApplicantPhone("138****0214");
        kycRequest.setApplicantEmail("139@XXXXXXXX.COM");
        kycRequest.setCompanyCode("29***69");
        kycRequest.setIdType("2");
        kycRequest.setBusinessLicense("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        kycRequest.setCompanyEnName("*** *** BUSINSS CO., LIMITED");
        kycRequest.setCompanyName("*** ***有限公司");
        kycRequest.setSubjectType(3);
        kycRequest.setRegisterAt("2022-10-08");
        kycRequest.setCountry("HKG");
        kycRequest.setBusinessAddress("DDDDDD");
        kycRequest.setBusinessEnAddress("315 *** *** SINGAPORE (***)");
        kycRequest.setAnnualAuditTable("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        kycRequest.setBusinessRegiCert("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        kycRequest.setBusinessRegistrationNumber("29***69");
        kycRequest.setNoticeUrl("WWWW");

        // 设置销售区域和产品类别
        Map<String, String> salesRegion = new HashMap<>();
        salesRegion.put("405", "其他");
        kycRequest.setMainSalesRegion(salesRegion);

        Map<String, String> productClass = new HashMap<>();
        productClass.put("320", "其他产品");
        kycRequest.setMainProductClass(productClass);

        // 设置董事信息
        List<CompanyDirector> directorList = new ArrayList<>();
        CompanyDirector director = new CompanyDirector();
        director.setKycType(1);
        director.setDirectorIdNo("3503*** ***0341X");
        director.setDirectorName("**杰");
        director.setDirectorNameEn("*** *** Jie");
        director.setGender(1);
        director.setCountry("CHN");
        director.setNation("汉");
        director.setAddress("*********家园6号楼1009");
        director.setBirthday("1979-12-19");
        director.setGrantingOffice("UTC PERAK");
        director.setEnAddress("Building 6, Lishui Jiayuan, Liulitun, Chaoyang District, Beijing 1009");
        director.setIdStartDate("2022-10-08");
        director.setIdEndDate("2028-04-08");
        director.setHandheldPhoto("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        director.setDirectorIdType("1");
        director.setDirectorIdBack("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        director.setDirectorIdFront("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        director.setLegalMobile("13922817525");

        List<String> facePhotos = Arrays.asList(
                "company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG",
                "company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG",
                "company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG",
                "company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG",
                "company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG",
                "company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG"
        );
        director.setFacePhoto(facePhotos);

        directorList.add(director);
        kycRequest.setCompanyDirectorDos(directorList);

        // 设置受益人信息
        List<CompanyBeneficiary> beneficiaryList = new ArrayList<>();
        CompanyBeneficiary beneficiary = new CompanyBeneficiary();
        beneficiary.setKycType(1);
        beneficiary.setGender(1);
        beneficiary.setNation("汉");
        beneficiary.setAddress("*********家园6号楼1009");
        beneficiary.setBirthday("1979-12-19");
        beneficiary.setCountry("CHN");

        beneficiary.setGrantingOffice("UTC PERAK");
        beneficiary.setEnAddress("Building 6, ******, Chaoyang District, Beijing 1009");
        beneficiary.setIdStartDate("2022-10-08");
        beneficiary.setIdEndDate("2028-04-08");
        beneficiary.setBeneficiaryIdBack("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        beneficiary.setBeneficiaryIdFront("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
        beneficiary.setBeneficiaryIdType("1");
        beneficiary.setBeneficiaryName("***杰");
        beneficiary.setBeneficiaryNameEn("*** *** Jie");
        beneficiary.setBeneficiaryIdNo("3503*** ***0341X");
        beneficiary.setRatio("100");

        beneficiaryList.add(beneficiary);
        kycRequest.setCompanyBeneficiaryDos(beneficiaryList);
        return kycRequest;
    }
}