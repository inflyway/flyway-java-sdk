package com.flyway.trade.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class TradeOrderQueryInfo {

    @JsonProperty("tradeOrderId")
    private String tradeOrderId;

    @JsonProperty("unmatchedAmount")
    private BigDecimal unmatchedAmount;

    @JsonProperty("matchedAmount")
    private BigDecimal matchedAmount;

    @JsonProperty("postedAmount")
    private BigDecimal postedAmount;

    @JsonProperty("buyerName")
    private String buyerName;

    @JsonProperty("type")
    private String type;

    @JsonProperty("customerWebsite")
    private String customerWebsite;

    @JsonProperty("goodName")
    private String goodName;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("orderTime")
    private Long orderTime;

    @JsonProperty("orderAppendixs")
    private List<Appendix> orderAppendixs;

    @JsonProperty("shipping")
    private Shipping shipping;

    @JsonProperty("contractAppendixs")
    private List<Appendix> contractAppendixs;

    @JsonProperty("clearanceAppendixs")
    private List<Appendix> clearanceAppendixs;

    @JsonProperty("clearanceProductsAppendixs")
    private List<Appendix> clearanceProductsAppendixs;

    @JsonProperty("goodsAppendixs")
    private List<Appendix> goodsAppendixs;

    @JsonProperty("deliveryMethod")
    private String deliveryMethod;

    @JsonProperty("estimatedDeliverTime")
    private Long estimatedDeliverTime;

    @JsonProperty("deliveryTime")
    private Long deliveryTime;

    @JsonProperty("isDelivery")
    private Boolean isDelivery;

    @JsonProperty("enterpriseNumber")
    private String enterpriseNumber;

    @JsonProperty("mainTradeType")
    private String mainTradeType;

    @JsonProperty("agreementAppendixs")
    private List<Appendix> agreementAppendixs;

    @JsonProperty("otherAppendixs")
    private List<Appendix> otherAppendixs;

    public String getTradeOrderId() {
        return tradeOrderId;
    }

    public void setTradeOrderId(String tradeOrderId) {
        this.tradeOrderId = tradeOrderId;
    }

    public BigDecimal getUnmatchedAmount() {
        return unmatchedAmount;
    }

    public void setUnmatchedAmount(BigDecimal unmatchedAmount) {
        this.unmatchedAmount = unmatchedAmount;
    }

    public BigDecimal getMatchedAmount() {
        return matchedAmount;
    }

    public void setMatchedAmount(BigDecimal matchedAmount) {
        this.matchedAmount = matchedAmount;
    }

    public BigDecimal getPostedAmount() {
        return postedAmount;
    }

    public void setPostedAmount(BigDecimal postedAmount) {
        this.postedAmount = postedAmount;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomerWebsite() {
        return customerWebsite;
    }

    public void setCustomerWebsite(String customerWebsite) {
        this.customerWebsite = customerWebsite;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    public List<Appendix> getOrderAppendixs() {
        return orderAppendixs;
    }

    public void setOrderAppendixs(List<Appendix> orderAppendixs) {
        this.orderAppendixs = orderAppendixs;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public List<Appendix> getContractAppendixs() {
        return contractAppendixs;
    }

    public void setContractAppendixs(List<Appendix> contractAppendixs) {
        this.contractAppendixs = contractAppendixs;
    }

    public List<Appendix> getClearanceAppendixs() {
        return clearanceAppendixs;
    }

    public void setClearanceAppendixs(List<Appendix> clearanceAppendixs) {
        this.clearanceAppendixs = clearanceAppendixs;
    }

    public List<Appendix> getClearanceProductsAppendixs() {
        return clearanceProductsAppendixs;
    }

    public void setClearanceProductsAppendixs(List<Appendix> clearanceProductsAppendixs) {
        this.clearanceProductsAppendixs = clearanceProductsAppendixs;
    }

    public List<Appendix> getGoodsAppendixs() {
        return goodsAppendixs;
    }

    public void setGoodsAppendixs(List<Appendix> goodsAppendixs) {
        this.goodsAppendixs = goodsAppendixs;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public Boolean getIsDelivery() {return isDelivery;}

    public void setIsDelivery(Boolean isDelivery) {
        this.isDelivery = isDelivery;
    }

    public String getEnterpriseNumber() {
        return enterpriseNumber;
    }

    public void setEnterpriseNumber(String enterpriseNumber) {
        this.enterpriseNumber = enterpriseNumber;
    }
    public Long getEstimatedDeliverTime() {
        return estimatedDeliverTime;
    }

    public void setEstimatedDeliverTime(Long estimatedDeliverTime) {
        this.estimatedDeliverTime = estimatedDeliverTime;
    }

    public Long getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Long deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getMainTradeType() {
        return mainTradeType;
    }

    public void setMainTradeType(String mainTradeType) {
        this.mainTradeType = mainTradeType;
    }

    public List<Appendix> getAgreementAppendixs() {
        return agreementAppendixs;
    }

    public void setAgreementAppendixs(List<Appendix> agreementAppendixs) {
        this.agreementAppendixs = agreementAppendixs;
    }

    public List<Appendix> getOtherAppendixs() {
        return otherAppendixs;
    }

    public void setOtherAppendixs(List<Appendix> otherAppendixs) {
        this.otherAppendixs = otherAppendixs;
    }

    public static class Appendix {
        @JsonProperty("name")
        private String name;

        @JsonProperty("fileUrl")
        private String fileUrl;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }
    }

    public static class Shipping {
        @JsonProperty("id")
        private String id;

        @JsonProperty("consigneeName")
        private String consigneeName;

        @JsonProperty("addressLine1")
        private String addressLine1;

        @JsonProperty("addressLine2")
        private String addressLine2;

        @JsonProperty("phone")
        private String phone;

        @JsonProperty("logisticsNo")
        private String logisticsNo;

        @JsonProperty("logisticsCompany")
        private String logisticsCompany;

        @JsonProperty("country")
        private String country;

        @JsonProperty("state")
        private String state;

        @JsonProperty("city")
        private String city;

        @JsonProperty("appendixs")
        private List<Appendix> appendixs;

        @JsonProperty("customsDeclarationNo")
        private String customsDeclarationNo;

        @JsonProperty("cusDeclaAppendixs")
        private List<Appendix> cusDeclaAppendixs;

        @JsonProperty("otherAppendixs")
        private List<Appendix> otherAppendixs;

        @JsonProperty("cusDetailAppendixs")
        private List<Appendix> cusDetailAppendixs;

        @JsonProperty("remark")
        private String remark;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getConsigneeName() {
            return consigneeName;
        }

        public void setConsigneeName(String consigneeName) {
            this.consigneeName = consigneeName;
        }

        public String getAddressLine1() {
            return addressLine1;
        }

        public void setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
        }

        public String getAddressLine2() {
            return addressLine2;
        }

        public void setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLogisticsNo() {
            return logisticsNo;
        }

        public void setLogisticsNo(String logisticsNo) {
            this.logisticsNo = logisticsNo;
        }

        public String getLogisticsCompany() {
            return logisticsCompany;
        }

        public void setLogisticsCompany(String logisticsCompany) {
            this.logisticsCompany = logisticsCompany;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public List<Appendix> getAppendixs() {
            return appendixs;
        }

        public void setAppendixs(List<Appendix> appendixs) {
            this.appendixs = appendixs;
        }

        public String getCustomsDeclarationNo() {
            return customsDeclarationNo;
        }

        public void setCustomsDeclarationNo(String customsDeclarationNo) {
            this.customsDeclarationNo = customsDeclarationNo;
        }

        public List<Appendix> getCusDeclaAppendixs() {
            return cusDeclaAppendixs;
        }

        public void setCusDeclaAppendixs(List<Appendix> cusDeclaAppendixs) {
            this.cusDeclaAppendixs = cusDeclaAppendixs;
        }

        public List<Appendix> getOtherAppendixs() {
            return otherAppendixs;
        }

        public void setOtherAppendixs(List<Appendix> otherAppendixs) {
            this.otherAppendixs = otherAppendixs;
        }

        public List<Appendix> getCusDetailAppendixs() {
            return cusDetailAppendixs;
        }

        public void setCusDetailAppendixs(List<Appendix> cusDetailAppendixs) {
            this.cusDetailAppendixs = cusDetailAppendixs;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
