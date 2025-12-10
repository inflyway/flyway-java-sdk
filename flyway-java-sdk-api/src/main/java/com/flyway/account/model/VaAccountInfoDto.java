package com.flyway.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * VA开户结果查询返回参数
 */
public class VaAccountInfoDto {

    /**
     * 银行卡号
     */
    @JsonProperty("bankCardNo")
    private String bankCardNo;

    /**
     * 账户状态
     */
    @JsonProperty("accountStatus")
    private String accountStatus;

    /**
     * 备注
     */
    @JsonProperty("remark")
    private String remark;

    /**
     * 请求号
     */
    @JsonProperty("requestNo")
    private String requestNo;

    /**
     * 账户信息
     */
    @JsonProperty("accountInfo")
    private AccountInfo accountInfo;

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    @Override
    public String toString() {
        return "VaAccountData{" +
                "bankCardNo='" + bankCardNo + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", remark='" + remark + '\'' +
                ", accountInfo=" + accountInfo +
                '}';
    }

    public static class AccountInfo {
        /**
         * 账户号
         */
        @JsonProperty("accountNo")
        private String accountNo;

        /**
         * 账户名
         */
        @JsonProperty("accountName")
        private String accountName;

        /**
         * 账户币种
         */
        @JsonProperty("accountCurrency")
        private String accountCurrency;

        /**
         * 支付类型
         */
        @JsonProperty("paymentType")
        private String paymentType;

        /**
         * 银行国家
         */
        @JsonProperty("bankCountry")
        private String bankCountry;

        @JsonProperty("swiftCode")
        private String swiftCode;

        @JsonProperty("bankName")
        private String bankName;

        @JsonProperty("bankAddress")
        private String bankAddress;

        @JsonProperty("routingNumber")
        private String routingNumber;

        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getAccountCurrency() {
            return accountCurrency;
        }

        public void setAccountCurrency(String accountCurrency) {
            this.accountCurrency = accountCurrency;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getBankCountry() {
            return bankCountry;
        }

        public void setBankCountry(String bankCountry) {
            this.bankCountry = bankCountry;
        }

        public String getSwiftCode() {
            return swiftCode;
        }

        public void setSwiftCode(String swiftCode) {
            this.swiftCode = swiftCode;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankAddress() {
            return bankAddress;
        }

        public void setBankAddress(String bankAddress) {
            this.bankAddress = bankAddress;
        }

        public String getRoutingNumber() {
            return routingNumber;
        }

        public void setRoutingNumber(String routingNumber) {
            this.routingNumber = routingNumber;
        }

        @Override
        public String toString() {
            return "AccountInfo{" +
                    "accountNo='" + accountNo + '\'' +
                    ", accountName='" + accountName + '\'' +
                    ", accountCurrency='" + accountCurrency + '\'' +
                    ", paymentType='" + paymentType + '\'' +
                    ", bankCountry='" + bankCountry + '\'' +
                    ", swiftCode='" + swiftCode + '\'' +
                    ", bankName='" + bankName + '\'' +
                    ", bankAddress='" + bankAddress + '\'' +
                    ", routingNumber='" + routingNumber + '\'' +
                    '}';
        }
    }
}
