package com.flyway.common;

/**
 * URL常量定义
 */
public interface FlywayUrlConstants {

    // ==================== 虚拟账户相关 (/openAccount/**) ====================
    /** VA账户开户 */
    String ACCOUNT_VA_OPEN = "/openAccount/finance/openAccount";
    /** VA账户查询 */
    String ACCOUNT_VA_QUERY = "/openAccount/finance/queryAccount";

    // ==================== 资金账户相关 (/openCfaccount/**) ====================
    /** 资金账户余额查询 */
    String CFACCOUNT_BALANCE_QUERY = "/openCfaccount/queryBalance";

    // ==================== 企业认证相关 (/openMember/registration/**) ====================
    /** 企业KYC认证 */
    String ENTERPRISE_KYC = "/openMember/registration/enterprise";
    /** 企业KYC查询 */
    String ENTERPRISE_KYC_QUERY = "/openMember/registration/query";

    // ==================== 交易相关 (/openTrade/**) ====================
    /** 创建交易 */
    String TRADE_CREATE = "/openTrade/create";
    /** 查询交易详情 */
    String TRADE_QUERY = "/openTrade/order/queryDetail";
    /** 绑定并申请 */
    String TRADE_BIND_APPLY = "/openTrade/bindAndApply";
    /** 查询审核状态 */
    String TRADE_QUERY_AUDIT = "/openTrade/queryFlowIDStatus";

    // ==================== 外汇相关 (/openFx/**) ====================
    /** 汇率查询 */
    String FX_RATE_INQUIRY = "/openFx/app/fly-exchange/rate/inquiry";
    /** 汇率提交 */
    String FX_RATE_SUBMIT = "/openFx/app/fly-exchange/rate/submit";
    /** 货币对查询 */
    String FX_CURRENCY_PAIR = "/openFx/app/fly-exchange/rate/currencyPair";
    /** 外汇详情查询 */
    String FX_EXCHANGE_DETAIL = "/openFx/app/fly-exchange/detail";

    // ==================== 文件相关 (/business/file/**) ====================
    /** 私有文件上传 */
    String FILE_UPLOAD_PRIVATE = "/openFile/file/upload";

    // ==================== 支付链接相关 (/openPaylink/**) ====================
    /** 修改费率 */
    String MODIFY_RATE = "/openPaylink/modify/rate";
    /** 查询自定义费率列表 */
    String QUERY_CUSTOM_RATE_LIST = "/openPaylink/query/customRateList";

    // ==================== 支付中心相关 (/paycenter-tt/**) ====================
    /** TT资金流水查询 */
    String PAYCENTER_TT_FUNDFLOW_QUERY = "/paycenter-tt/open/fundflow/detail";

    // ==================== 提现相关 (/collection/external/withdraw/**) ====================
    /** 查询提现金额 */
    String WITHDRAW_AMT_QRY_PATH = "/collection/external/withdraw/queryWithdrawAmount";
    /** 试算金额 */
    String TRAIL_AMT_QRY_PATH = "/collection/external/withdraw/trailAmount";
    /** 申请提现 */
    String APPLY_WITHDRAW_PATH = "/collection/external/withdraw/applyWithdraw";
    /** 查询提现状态 */
    String WITHDRAW_STATUS_QRY_PATH = "/collection/external/withdraw/queryWithdrawStatus";
    /** 更新卡片还款信息 */
    String UPDATE_CARD_REPAYMENT_PATH = "/collection/external/withdraw/updateCardRepayment";
}