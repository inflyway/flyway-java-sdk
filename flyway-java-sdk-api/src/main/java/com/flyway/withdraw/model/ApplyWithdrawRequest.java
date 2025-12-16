package com.flyway.withdraw.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

import java.math.BigDecimal;
import java.util.List;

/**
 * 提现申请请求参数
 */
public class ApplyWithdrawRequest extends CommonRequest {

    /**
     * 请求编号
     */
    @JsonProperty("requestNo")
    private String requestNo;

    /**
     * OpenID
     */
    @JsonProperty("openID")
    private String openID;

    /**
     * 试算ID
     */
    @JsonProperty("trailId")
    private String trailId;

    /**
     * 场景
     */
    @JsonProperty("scene")
    private Integer scene;

    /**
     * 提现币种
     */
    @JsonProperty("withdrawCurrency")
    private String withdrawCurrency;

    /**
     * 账户扣款金额
     */
    @JsonProperty("accountDeductionAmt")
    private BigDecimal accountDeductionAmt;

    /**
     * 提现手续费
     */
    @JsonProperty("withdrawFee")
    private BigDecimal withdrawFee;

    /**
     * 到账金额
     */
    @JsonProperty("arriveAmt")
    private BigDecimal arriveAmt;

    /**
     * 回调地址
     */
    @JsonProperty("callBackUrl")
    private String callBackUrl;

    /**
     * 是否为POBO模式
     */
    @JsonProperty("isPobo")
    private Boolean isPobo;

    /**
     * 付款用途
     */
    @JsonProperty("paymentPurpose")
    private String paymentPurpose;

    /**
     * 付款附件列表
     */
    @JsonProperty("paymentAttachments")
    private List<PaymentAttachment> paymentAttachments;

    /**
     * 收款人信息
     */
    @JsonProperty("payeeInfo")
    private PayeeInfo payeeInfo;

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getTrailId() {
        return trailId;
    }

    public void setTrailId(String trailId) {
        this.trailId = trailId;
    }

    public Integer getScene() {
        return scene;
    }

    public void setScene(Integer scene) {
        this.scene = scene;
    }

    public String getWithdrawCurrency() {
        return withdrawCurrency;
    }

    public void setWithdrawCurrency(String withdrawCurrency) {
        this.withdrawCurrency = withdrawCurrency;
    }

    public BigDecimal getAccountDeductionAmt() {
        return accountDeductionAmt;
    }

    public void setAccountDeductionAmt(BigDecimal accountDeductionAmt) {
        this.accountDeductionAmt = accountDeductionAmt;
    }

    public BigDecimal getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(BigDecimal withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    public BigDecimal getArriveAmt() {
        return arriveAmt;
    }

    public void setArriveAmt(BigDecimal arriveAmt) {
        this.arriveAmt = arriveAmt;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    public Boolean getIsPobo() {
        return isPobo;
    }

    public void setIsPobo(Boolean isPobo) {
        this.isPobo = isPobo;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    public List<PaymentAttachment> getPaymentAttachments() {
        return paymentAttachments;
    }

    public void setPaymentAttachments(List<PaymentAttachment> paymentAttachments) {
        this.paymentAttachments = paymentAttachments;
    }

    public PayeeInfo getPayeeInfo() {
        return payeeInfo;
    }

    public void setPayeeInfo(PayeeInfo payeeInfo) {
        this.payeeInfo = payeeInfo;
    }

    /**
     * 付款附件信息
     */
    public static class PaymentAttachment {
        /**
         * 文件名
         */
        @JsonProperty("fileName")
        private String fileName;

        /**
         * 文件URL
         */
        @JsonProperty("fileUrl")
        private String fileUrl;

        /**
         * 证书类型
         */
        @JsonProperty("certType")
        private String certType;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String getCertType() {
            return certType;
        }

        public void setCertType(String certType) {
            this.certType = certType;
        }
    }

    /**
     * 收款人信息
     */
    public static class PayeeInfo {
        /**
         * 收款人姓名
         */
        @JsonProperty("payeeName")
        private String payeeName;

        /**
         * 收款账号
         */
        @JsonProperty("payeeAccountNo")
        private String payeeAccountNo;

        /**
         * 收款账户类型
         */
        @JsonProperty("payeeAccountType")
        private Integer payeeAccountType;

        /**
         * 收款银行卡币种
         */
        @JsonProperty("payeeBankCardCurrency")
        private String payeeBankCardCurrency;

        /**
         * 收款开户银行
         */
        @JsonProperty("payeeOpenBank")
        private String payeeOpenBank;

        /**
         * 收款银行国家
         */
        @JsonProperty("payeeBankCountry")
        private String payeeBankCountry;

        /**
         * 收款银行行号
         */
        @JsonProperty("payeeBankNumber")
        private String payeeBankNumber;

        /**
         * 汇兑后收款人银行账号
         * scene=10 必填
         */
        @JsonProperty("exReAccountNo")
        private String exReAccountNo;

        /**
         * 收款银行省份
         */
        @JsonProperty("payeeBankProvince")
        private String payeeBankProvince;

        /**
         * 收款银行城市
         */
        @JsonProperty("payeeBankCity")
        private String payeeBankCity;

        /**
         * 收款银行支行名称
         */
        @JsonProperty("payeeBankSubBranchName")
        private String payeeBankSubBranchName;

        /**
         * 收款人手机号
         */
        @JsonProperty("payeeMobile")
        private String payeeMobile;

        /**
         * 收款人身份证号
         */
        @JsonProperty("payeeIdNo")
        private String payeeIdNo;

        /**
         * 收款人地址
         */
        @JsonProperty("payeeAddress")
        private String payeeAddress;

        /**
         * 收款银行地址
         */
        @JsonProperty("payeeBankAddress")
        private String payeeBankAddress;

        /**
         * Swift代码
         */
        @JsonProperty("swiftCode")
        private String swiftCode;

        /**
         * 路由号码
         */
        @JsonProperty("routingNumber")
        private String routingNumber;

        /**
         * 收款省份ID
         */
        @JsonProperty("payeeProvinceId")
        private String payeeProvinceId;

        /**
         * 收款省份
         */
        @JsonProperty("payeeProvince")
        private String payeeProvince;

        /**
         * 收款城市
         */
        @JsonProperty("payeeCity")
        private String payeeCity;

        /**
         * 邮政编码
         */
        @JsonProperty("postCode")
        private String postCode;

        /**
         * 分行编号
         */
        @JsonProperty("branchNumber")
        private String branchNumber;

        public String getExReAccountNo() {
            return exReAccountNo;
        }

        public void setExReAccountNo(String exReAccountNo) {
            this.exReAccountNo = exReAccountNo;
        }

        public String getPayeeName() {
            return payeeName;
        }

        public void setPayeeName(String payeeName) {
            this.payeeName = payeeName;
        }

        public String getPayeeAccountNo() {
            return payeeAccountNo;
        }

        public void setPayeeAccountNo(String payeeAccountNo) {
            this.payeeAccountNo = payeeAccountNo;
        }

        public Integer getPayeeAccountType() {
            return payeeAccountType;
        }

        public void setPayeeAccountType(Integer payeeAccountType) {
            this.payeeAccountType = payeeAccountType;
        }

        public String getPayeeBankCardCurrency() {
            return payeeBankCardCurrency;
        }

        public void setPayeeBankCardCurrency(String payeeBankCardCurrency) {
            this.payeeBankCardCurrency = payeeBankCardCurrency;
        }

        public String getPayeeOpenBank() {
            return payeeOpenBank;
        }

        public void setPayeeOpenBank(String payeeOpenBank) {
            this.payeeOpenBank = payeeOpenBank;
        }

        public String getPayeeBankCountry() {
            return payeeBankCountry;
        }

        public void setPayeeBankCountry(String payeeBankCountry) {
            this.payeeBankCountry = payeeBankCountry;
        }

        public String getPayeeBankNumber() {
            return payeeBankNumber;
        }

        public void setPayeeBankNumber(String payeeBankNumber) {
            this.payeeBankNumber = payeeBankNumber;
        }

        public String getPayeeBankProvince() {
            return payeeBankProvince;
        }

        public void setPayeeBankProvince(String payeeBankProvince) {
            this.payeeBankProvince = payeeBankProvince;
        }

        public String getPayeeBankCity() {
            return payeeBankCity;
        }

        public void setPayeeBankCity(String payeeBankCity) {
            this.payeeBankCity = payeeBankCity;
        }

        public String getPayeeBankSubBranchName() {
            return payeeBankSubBranchName;
        }

        public void setPayeeBankSubBranchName(String payeeBankSubBranchName) {
            this.payeeBankSubBranchName = payeeBankSubBranchName;
        }

        public String getPayeeMobile() {
            return payeeMobile;
        }

        public void setPayeeMobile(String payeeMobile) {
            this.payeeMobile = payeeMobile;
        }

        public String getPayeeIdNo() {
            return payeeIdNo;
        }

        public void setPayeeIdNo(String payeeIdNo) {
            this.payeeIdNo = payeeIdNo;
        }

        public String getPayeeAddress() {
            return payeeAddress;
        }

        public void setPayeeAddress(String payeeAddress) {
            this.payeeAddress = payeeAddress;
        }

        public String getPayeeBankAddress() {
            return payeeBankAddress;
        }

        public void setPayeeBankAddress(String payeeBankAddress) {
            this.payeeBankAddress = payeeBankAddress;
        }

        public String getSwiftCode() {
            return swiftCode;
        }

        public void setSwiftCode(String swiftCode) {
            this.swiftCode = swiftCode;
        }

        public String getRoutingNumber() {
            return routingNumber;
        }

        public void setRoutingNumber(String routingNumber) {
            this.routingNumber = routingNumber;
        }

        public String getPayeeProvinceId() {
            return payeeProvinceId;
        }

        public void setPayeeProvinceId(String payeeProvinceId) {
            this.payeeProvinceId = payeeProvinceId;
        }

        public String getPayeeProvince() {
            return payeeProvince;
        }

        public void setPayeeProvince(String payeeProvince) {
            this.payeeProvince = payeeProvince;
        }

        public String getPayeeCity() {
            return payeeCity;
        }

        public void setPayeeCity(String payeeCity) {
            this.payeeCity = payeeCity;
        }

        public String getPostCode() {
            return postCode;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public String getBranchNumber() {
            return branchNumber;
        }

        public void setBranchNumber(String branchNumber) {
            this.branchNumber = branchNumber;
        }
    }
}