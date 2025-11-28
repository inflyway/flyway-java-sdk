package com.flyway.kyc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flyway.common.FlywayConfig;
import com.flyway.common.FlywayUrlConstants;
import com.flyway.exception.FlywayApiException;
import com.flyway.http.AbstractApi;
import com.flyway.http.HttpClientUtil;
import com.flyway.kyc.model.KycRequest;
import com.flyway.kyc.model.KycResponse;
import com.flyway.kyc.model.QueryKycRequest;
import com.flyway.kyc.model.QueryKycResponse;

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
    public KycResponse enterprise(KycRequest request) throws FlywayApiException {
        return execute(request, this.enterpriseKycPath, KycResponse.class);
    }
    
    /**
     * 企业实名查询
     *
     * @param request 查询请求参数
     * @return 查询结果
     * @throws FlywayApiException API调用异常
     */ 
    public QueryKycResponse	queryKyc(QueryKycRequest request) throws FlywayApiException {
        return execute(request, this.kycQueryPahth, QueryKycResponse.class);
    }
}
