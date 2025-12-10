package com.flyway.example;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.flyway.common.FlywayConfig;
import com.flyway.common.TokenApi;
import com.flyway.exception.FlywayApiException;
import com.flyway.kyc.OpenKYCApi;
import com.flyway.kyc.model.CompanyBeneficiary;
import com.flyway.kyc.model.CompanyDirector;
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
	           Kyc();
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

	 private static void Kyc() throws FlywayApiException {

		 // 1. 配置 Flyway SDK
         FlywayConfig config = new FlywayConfig();
         config.setServerUrl("https://open-test.inflyway.com"); // 测试环境地址
         config.setClientId("");           // 替换为实际的 client_id
         config.setClientSecret("");   // 替换为实际的 client_secret
         config.setAesKey("");             // 替换为16位 AES 密钥
         config.setRsaPrivateKey("");       // 替换为 RSA 私钥
         config.setDebug(true);

         // 2. 获取访问令牌
         TokenApi tokenApi = new TokenApi(config);
         String token = tokenApi.getToken();
         System.out.println("获取到的访问令牌: " + token);

         // 3. 初始化 KYC API
         OpenKYCApi kycApi = new OpenKYCApi(config);


         //香港的
//         KycRequest hkkycRequest = initHkKycRequest();
//         testKycSdk(token, kycApi, hkkycRequest);
         //大陆的
         KycRequest chinakycRequest = initChinaKycRequest();
         testKycSdk(token, kycApi, chinakycRequest);
		}
	 private static void queryKyc( ) throws FlywayApiException {
			 
		 // 1. 配置 Flyway SDK
         FlywayConfig config = new FlywayConfig();
         config.setServerUrl("https://open-test.inflyway.com"); // 测试环境地址
         config.setClientId("");           // 替换为实际的 client_id
         config.setClientSecret("");   // 替换为实际的 client_secret
         config.setAesKey("");             // 替换为16位 AES 密钥
         config.setRsaPrivateKey("");       // 替换为 RSA 私钥
         config.setDebug(true);

         // 2. 获取访问令牌
         TokenApi tokenApi = new TokenApi(config);
         String token = tokenApi.getToken();
         System.out.println("获取到的访问令牌: " + token);
         // 3. 初始化 KYC API
         OpenKYCApi kycApi = new OpenKYCApi(config);
			// 5. 查询 KYC 结果
			QueryKycRequest queryRequest = new QueryKycRequest();
			// 设置查询条件
			queryRequest.setToken(token);
			// 添加查询参数...
			queryRequest.setRequestNo("1122322230");
			QueryKycResponse queryResponse = kycApi.queryKyc(queryRequest);
			System.out.println("KYC 查询响应: " +JSON.toJSONString(queryResponse) );
		} 
	private static void testKycSdk(String token, OpenKYCApi kycApi, KycRequest kycRequest) throws FlywayApiException {
		// 设置必要的请求参数
		kycRequest.setToken(token);
		KycResponse kycResponse = kycApi.enterprise(kycRequest);
		System.out.println("KYC 认证响应: " + JSON.toJSONString(kycResponse));

//		// 5. 查询 KYC 结果
//		QueryKycRequest queryRequest = new QueryKycRequest();
//		// 设置查询条件
//		queryRequest.setToken(token);
//		// 添加查询参数...
//		queryRequest.setRequestNo(kycRequest.getRequestNo());
//		QueryKycResponse queryResponse = kycApi.queryKyc(queryRequest);
//		System.out.println("KYC 查询响应: " +JSON.toJSONString(queryResponse) );
	}
	 private static KycRequest initChinaKycRequest() {
		    KycRequest kycRequest = new KycRequest();

		    // 设置基本信息
		    kycRequest.setRequestNo("1122322230");
		    kycRequest.setApplicantPhone("13613517235");
		    kycRequest.setApplicantEmail("139@XXXXXXXX.COM");
		    kycRequest.setCompanyCode("91330782MADAJ85G89");
		    kycRequest.setIdType("1");
		    kycRequest.setBusinessLicense("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
		    kycRequest.setCompanyEnName("22GEDHEALTHINOW PTE. LTD.");
		    kycRequest.setCompanyName("义乌市基智饰品有限公司");
		    kycRequest.setSubjectType(1);
		    kycRequest.setRegisterAt("2022-10-08");
		    kycRequest.setCountry("CHN");
		    kycRequest.setBusinessAddress("DDDDDD");
		    kycRequest.setBusinessEnAddress("315 OUTRAM ROAD #15-08 TAN BOON LIAT BUILDING SINGAPORE (169074)");
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
		    director.setDirectorIdNo("441781200311303814");
		    director.setDirectorName("覃国庆");
		    director.setDirectorNameEn("Zhang Jing Tai");
		    director.setGender(1);
		    director.setCountry("CHN");
		    director.setNation("汉");
		    director.setAddress("北京市朝阳区六里屯丽水家园6号楼1009");
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
		    
		    // 第一个受益人
		    CompanyBeneficiary beneficiary1 = new CompanyBeneficiary();
		    beneficiary1.setKycType(4);
		    beneficiary1.setGender(1);
		    beneficiary1.setNation("汉");
		    beneficiary1.setAddress("北京市朝阳区六里屯丽水家园6号楼1009");
		    beneficiary1.setBirthday("1979-12-19");
		    beneficiary1.setCountry("CHN");
		    
		    beneficiary1.setGrantingOffice("UTC PERAK");
		    beneficiary1.setEnAddress("Building 6, Lishui Jiayuan, Liulitun, Chaoyang District, Beijing 1009");
		    beneficiary1.setIdStartDate("2022-10-08");
		    beneficiary1.setIdEndDate("2028-04-08");
		    beneficiary1.setBeneficiaryIdBack("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
		    beneficiary1.setBeneficiaryIdFront("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
		    beneficiary1.setBeneficiaryIdType("1");
		    beneficiary1.setBeneficiaryName("覃国庆");
		    beneficiary1.setBeneficiaryNameEn("CHONG KAM THAI");
		    beneficiary1.setBeneficiaryIdNo("441781200311303814");
		    beneficiary1.setRatio("50");

		    // 第二个受益人
		    CompanyBeneficiary beneficiary2 = new CompanyBeneficiary();
		    beneficiary2.setKycType(4);
		    beneficiary2.setGender(1);
		    beneficiary2.setNation("汉");
		    beneficiary2.setAddress("北京市朝阳区六里屯丽水家园6号楼1009");
		    beneficiary2.setBirthday("1979-12-19");
		    beneficiary2.setCountry("CHN");
		   
		    beneficiary2.setGrantingOffice("UTC PERAK");
		    beneficiary2.setEnAddress("Building 6, Lishui Jiayuan, Liulitun, Chaoyang District, Beijing 1009");
		    beneficiary2.setIdStartDate("2022-10-08");
		    beneficiary2.setIdEndDate("2028-04-08");
		    beneficiary2.setBeneficiaryIdBack("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
		    beneficiary2.setBeneficiaryIdFront("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
		    beneficiary2.setBeneficiaryIdType("1");
		    beneficiary2.setBeneficiaryName("覃国庆1");
		    beneficiary2.setBeneficiaryNameEn("CHONG KAM THAI");
		    beneficiary2.setBeneficiaryIdNo("441781200311303815");
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
		kycRequest.setApplicantPhone("13821410214");
		kycRequest.setApplicantEmail("139@XXXXXXXX.COM");
		kycRequest.setCompanyCode("2919469");
		kycRequest.setIdType("2");
		kycRequest.setBusinessLicense("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
		kycRequest.setCompanyEnName("XAD (HK) BUSINSS CO., LIMITED");
		kycRequest.setCompanyName("鑫安達（香港）國際商務有限公司");
		kycRequest.setSubjectType(3);
		kycRequest.setRegisterAt("2022-10-08");
		kycRequest.setCountry("HKG");
		kycRequest.setBusinessAddress("DDDDDD");
		kycRequest.setBusinessEnAddress("315 OUTRAMROAD #15-08 TAN BOON LIAT BUILDING SINGAPORE (169074)");
		kycRequest.setAnnualAuditTable("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
		kycRequest.setBusinessRegiCert("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
		kycRequest.setBusinessRegistrationNumber("2919469");
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
		director.setDirectorIdNo("35030520030510341X");
		director.setDirectorName("林志杰");
		director.setDirectorNameEn("Zhang Jing Tai");
		director.setGender(1);
		director.setCountry("CHN");
		director.setNation("汉");
		director.setAddress("北京市朝阳区六里屯丽水家园6号楼1009");
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
		beneficiary.setAddress("北京市朝阳区六里屯丽水家园6号楼1009");
		beneficiary.setBirthday("1979-12-19");
		beneficiary.setCountry("CHN");
		
		beneficiary.setGrantingOffice("UTC PERAK");
		beneficiary.setEnAddress("Building 6, Lishui Jiayuan, Liulitun, Chaoyang District, Beijing 1009");
		beneficiary.setIdStartDate("2022-10-08");
		beneficiary.setIdEndDate("2028-04-08");
		beneficiary.setBeneficiaryIdBack("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
		beneficiary.setBeneficiaryIdFront("company/file/local/1681962610202_26a2132dc504e5a4a79f61eb5e285570.JPG");
		beneficiary.setBeneficiaryIdType("1");
		beneficiary.setBeneficiaryName("林志杰");
		beneficiary.setBeneficiaryNameEn("CHONG KAM THAI");
		beneficiary.setBeneficiaryIdNo("35030520030510341X");
		beneficiary.setRatio("100");

		beneficiaryList.add(beneficiary);
		kycRequest.setCompanyBeneficiaryDos(beneficiaryList);
		return kycRequest;
	} 
}