package com.flyway.kyc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flyway.common.FlywayConfig;
import com.flyway.common.FlywayUrlConstants;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;
import com.flyway.http.AbstractApi;
import com.flyway.http.HttpClientUtil;
import com.flyway.kyc.model.KycCo;
import com.flyway.kyc.model.KycRequest;
import com.flyway.kyc.model.KycResultInfoCo;
import com.flyway.kyc.model.QueryKycRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Inflyway SDK客户端
 */ 
public class OpenKYCApi extends AbstractApi {

    private static final Logger logger = LoggerFactory.getLogger(OpenKYCApi.class);
 
    private String enterpriseKycPath ;
    private  String kycQueryPahth ;

    /**
     * 使用指定配置构造客户端
     */
    public OpenKYCApi(FlywayConfig config) {
        super(config);
        this.config = config;
        this.httpClient = new HttpClientUtil(config);
        this.enterpriseKycPath = FlywayUrlConstants.ENTERPRISE_KYC;
        this.kycQueryPahth = FlywayUrlConstants.ENTERPRISE_KYC_QUERY;
    }

    /**
     * 企业实名认证
     *
     * @param request 查询请求参数
     * @return 查询结果
     * @throws FlywayApiException API调用异常
     */ 
    public CommonResponse<KycCo> enterprise(KycRequest request) throws FlywayApiException {
        return executeWithTypeRef(request, this.enterpriseKycPath, new TypeReference<CommonResponse<KycCo>>() {
        });
    }
    
    /**
     * 企业实名查询
     *
     * @param request 查询请求参数
     * @return 查询结果
     * @throws FlywayApiException API调用异常
     */ 
    public CommonResponse<KycResultInfoCo> queryKyc(QueryKycRequest request) throws FlywayApiException {
        return executeWithTypeRef(request, this.kycQueryPahth, new TypeReference<CommonResponse<KycResultInfoCo>>() {
        });
    }
}