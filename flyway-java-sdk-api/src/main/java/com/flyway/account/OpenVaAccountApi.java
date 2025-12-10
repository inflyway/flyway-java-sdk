package com.flyway.account;

import com.flyway.account.model.VaAccountInfoDto;
import com.flyway.account.model.VaAccountOpenRequest;
import com.flyway.account.model.VaAccountQueryRequest;
import com.flyway.common.FlywayConfig;
import com.flyway.common.FlywayUrlConstants;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;
import com.flyway.http.AbstractApi;
import com.flyway.http.HttpClientUtil;

/**
 * VA进件开户API
 */
public class OpenVaAccountApi extends AbstractApi {

    private String vaAccountOpenPath;
    private String vaAccountQueryPath;

    /**
     * 使用指定配置构造客户端
     */
    public OpenVaAccountApi(FlywayConfig config) {
        super(config);
        this.config = config;
        this.httpClient = new HttpClientUtil(config);
        this.vaAccountOpenPath = FlywayUrlConstants.ACCOUNT_VA_OPEN;
        this.vaAccountQueryPath = FlywayUrlConstants.ACCOUNT_VA_QUERY;
    }

    /**
     * 账户开通申请
     *
     * @param request 开户请求参数
     * @return 开户结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse openAccount(VaAccountOpenRequest request) throws FlywayApiException {
        return execute(request, this.vaAccountOpenPath, CommonResponse.class);
    }

    /**
     * 账户信息查询
     *
     * @param request 查询请求参数
     * @return 查询结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<VaAccountInfoDto> queryAccount(VaAccountQueryRequest request) throws FlywayApiException {
        return execute(request, this.vaAccountQueryPath, CommonResponse.class);
    }
}