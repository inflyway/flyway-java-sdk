package com.flyway.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

import java.math.BigDecimal;
import java.util.List;

/**
 * VA进件开户请求参数
 */
public class VaAccountOpenRequest extends CommonRequest {

    /**
     * 请求流水号
     */
    @JsonProperty("requestNo")
    private String requestNo;

    /**
     * 开放平台账户ID
     */
    @JsonProperty("openID")
    private String openID;

    /**
     * 银行卡号
     */
    @JsonProperty("bankCardNo")
    private String bankCardNo;

    /**
     * 银行账户名称
     */
    @JsonProperty("bankAccountName")
    private String bankAccountName;

    /**
     * 回调通知地址
     */
    @JsonProperty("noticeUrl")
    private String noticeUrl;

    /**
     * 补充信息
     */
    @JsonProperty("supplementInformation")
    private SupplementInformation supplementInformation;

    public VaAccountOpenRequest() {
    }

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

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }

    public SupplementInformation getSupplementInformation() {
        return supplementInformation;
    }

    public void setSupplementInformation(SupplementInformation supplementInformation) {
        this.supplementInformation = supplementInformation;
    }

    public static class SupplementInformation {
        /**
         * 主营业务类型
         */
        @JsonProperty("mainBusinessType")
        private List<String> mainBusinessType;

        /**
         * 主要销售地区
         */
        @JsonProperty("mainSalesRegion")
        private List<String> mainSalesRegion;

        /**
         * 行业
         */
        @JsonProperty("industry")
        private String industry;

        /**
         * 主要销售区域
         */
        @JsonProperty("majorSalesRegion")
        private String majorSalesRegion;

        /**
         * 年汇款金额
         */
        @JsonProperty("annualRemittanceAmount")
        private BigDecimal annualRemittanceAmount;

        /**
         * 平均汇款金额
         */
        @JsonProperty("averageRemittanceAmount")
        private BigDecimal averageRemittanceAmount;

        public SupplementInformation() {
        }

        public List<String> getMainBusinessType() {
            return mainBusinessType;
        }

        public void setMainBusinessType(List<String> mainBusinessType) {
            this.mainBusinessType = mainBusinessType;
        }

        public List<String> getMainSalesRegion() {
            return mainSalesRegion;
        }

        public void setMainSalesRegion(List<String> mainSalesRegion) {
            this.mainSalesRegion = mainSalesRegion;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getMajorSalesRegion() {
            return majorSalesRegion;
        }

        public void setMajorSalesRegion(String majorSalesRegion) {
            this.majorSalesRegion = majorSalesRegion;
        }

        public BigDecimal getAnnualRemittanceAmount() {
            return annualRemittanceAmount;
        }

        public void setAnnualRemittanceAmount(BigDecimal annualRemittanceAmount) {
            this.annualRemittanceAmount = annualRemittanceAmount;
        }

        public BigDecimal getAverageRemittanceAmount() {
            return averageRemittanceAmount;
        }

        public void setAverageRemittanceAmount(BigDecimal averageRemittanceAmount) {
            this.averageRemittanceAmount = averageRemittanceAmount;
        }
    }

    @Override
    public String toString() {
        return "VaAccountOpenRequest{" +
                "requestNo='" + requestNo + '\'' +
                ", openID='" + openID + '\'' +
                ", bankCardNo='" + bankCardNo + '\'' +
                ", bankAccountName='" + bankAccountName + '\'' +
                ", noticeUrl='" + noticeUrl + '\'' +
                ", supplementInformation=" + supplementInformation +
                '}';
    }
}