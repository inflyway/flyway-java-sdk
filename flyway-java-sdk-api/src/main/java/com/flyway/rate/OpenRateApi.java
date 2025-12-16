package com.flyway.rate;

import com.flyway.common.FlywayConfig;
import com.flyway.common.FlywayUrlConstants;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;
import com.flyway.http.AbstractApi;
import com.flyway.http.HttpClientUtil;
import com.flyway.rate.model.CustomRateRequest;
import com.flyway.rate.model.OpenRateRespose;

/**
 * 费率配置相关接口
 */
public class OpenRateApi extends AbstractApi {

    private String modifyRateOpenPath;
    private String queryCustomRatePath;

    /**
     * 使用指定配置构造客户端
     *
     * @param config
     */
    public OpenRateApi(FlywayConfig config) {
        super(config);
        this.config = config;
        this.httpClient = new HttpClientUtil(config);
        this.modifyRateOpenPath = FlywayUrlConstants.MODIFY_RATE;
        this.queryCustomRatePath = FlywayUrlConstants.QUERY_CUSTOM_RATE_LIST;
    }


    /**
     * 修改费率配置
     * @param request 修改请求参数
     * @return 响应结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse modify(CustomRateRequest request) throws FlywayApiException {
        return execute(request, this.modifyRateOpenPath, CommonResponse.class);
    }

    /**
     * 查询费率配置
     * @param request 查询请求参数
     * @return 响应结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<OpenRateRespose> customRateList(CustomRateRequest request) throws FlywayApiException {
        return execute(request, this.queryCustomRatePath, OpenRateRespose.class);
    }

}
