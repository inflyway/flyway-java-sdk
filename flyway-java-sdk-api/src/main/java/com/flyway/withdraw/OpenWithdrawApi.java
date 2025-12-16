package com.flyway.withdraw;

import com.flyway.common.FlywayConfig;
import com.flyway.common.FlywayUrlConstants;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;
import com.flyway.http.AbstractApi;
import com.flyway.http.HttpClientUtil;
import com.flyway.withdraw.model.ApplyWithdrawRequest;
import com.flyway.withdraw.model.ApplyWithdrawResponseDto;
import com.flyway.withdraw.model.QueryWithdrawStatusRequest;
import com.flyway.withdraw.model.QueryWithdrawStatusResponseDto;
import com.flyway.withdraw.model.TrailAmountRequest;
import com.flyway.withdraw.model.TrailAmountResponseDto;
import com.flyway.withdraw.model.UpdateCardRepaymentRequest;
import com.flyway.withdraw.model.WithdrawAmountDto;
import com.flyway.withdraw.model.WithdrawAmountRequest;

/**
 * @Description
 * @Date 2025/12/12 16:41
 * @Author lut
 * @Version 1.0.0
 */
public class OpenWithdrawApi extends AbstractApi {

    private String withdrawAmtQryPath;
    private String trailAmtQryPath;
    private String applyWithdrawPath;
    private String withdrawStatusQryPath;
    private String updateCardRepaymentPath;

    /**
     * 使用指定配置构造客户端
     *
     * @param config
     */
    public OpenWithdrawApi(FlywayConfig config) {
        super(config);
        this.config = config;
        this.httpClient = new HttpClientUtil(config);
        this.withdrawAmtQryPath = FlywayUrlConstants.WITHDRAW_AMT_QRY_PATH;
        this.trailAmtQryPath = FlywayUrlConstants.TRAIL_AMT_QRY_PATH;
        this.applyWithdrawPath = FlywayUrlConstants.APPLY_WITHDRAW_PATH;
        this.withdrawStatusQryPath = FlywayUrlConstants.WITHDRAW_STATUS_QRY_PATH;
        this.updateCardRepaymentPath = FlywayUrlConstants.UPDATE_CARD_REPAYMENT_PATH;
    }

    /**
     * 查询可提现金额
     *
     * @param request 请求参数
     * @return 查询结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<WithdrawAmountDto> queryWithdrawAmt(WithdrawAmountRequest request) throws FlywayApiException {
        return execute(request, this.withdrawAmtQryPath, CommonResponse.class);
    }

    /**
     * 提现到账金额试算
     *
     * @param request 请求参数
     * @return 试算结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<TrailAmountResponseDto> trailAmount(TrailAmountRequest request) throws FlywayApiException {
        return execute(request, this.trailAmtQryPath, CommonResponse.class);
    }

    /**
     * 提现申请
     *
     * @param request 请求参数
     * @return 提现申请结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<ApplyWithdrawResponseDto> applyWithdraw(ApplyWithdrawRequest request) throws FlywayApiException {
        return execute(request, this.applyWithdrawPath, CommonResponse.class);
    }

    /**
     * 提现状态查询
     *
     * @param request 请求参数
     * @return 提现状态查询结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<QueryWithdrawStatusResponseDto> queryWithdrawStatus(QueryWithdrawStatusRequest request) throws FlywayApiException {
        return execute(request, this.withdrawStatusQryPath, CommonResponse.class);
    }

    /**
     * 提现CNY失败换卡重发
     *
     * @param request 请求参数
     * @return 操作结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<Void> updateCardRepayment(UpdateCardRepaymentRequest request) throws FlywayApiException {
        return execute(request, this.updateCardRepaymentPath, CommonResponse.class);
    }
}
