package com.flyway.trade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonRequest;

import java.math.BigDecimal;
import java.util.List;

/**
 * 交易订单创建请求
 */
public class TradeOderCreateRequest extends CommonRequest {

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
     * 交易单id
     */
    @JsonProperty("tradeOrderId")
    private String tradeOrderId;

    /**
     * 金额
     */
    @JsonProperty("amount")
    private BigDecimal amount;

    /**
     * 买家名称
     */
    @JsonProperty("buyerName")
    private String buyerName;

    @JsonProperty("isDelivery")
    private Boolean isDelivery;

    @JsonProperty("enterpriseNumber")
    private String enterpriseNumber;

    /**
     * 进口清关文件
     */
    @JsonProperty("clearanceAppendixs")
    private List<Appendix> clearanceAppendixs;

    /**
     * 清关产品图片
     */
    @JsonProperty("clearanceProductsAppendixs")
    private List<Appendix> clearanceProductsAppendixs;

    /**
     * 合同文件
     */
    @JsonProperty("contractAppendixs")
    private List<Appendix> contractAppendixs;

    /**
     * 币种
     */
    @JsonProperty("currency")
    private String currency;

    /**
     * 客户网站
     */
    @JsonProperty("customerWebsite")
    private String customerWebsite;

    /**
     * 交付方式
     */
    @JsonProperty("deliveryMethod")
    private String deliveryMethod;

    /**
     * 交付时间
     */
    @JsonProperty("deliveryTime")
    private String deliveryTime;

    /**
     * 预计送达时间
     */
    @JsonProperty("estimatedDeliverTime")
    private String estimatedDeliverTime;

    /**
     * 商品名称
     */
    @JsonProperty("goodName")
    private String goodName;

    /**
     * 商品采购明细
     */
    @JsonProperty("goodsAppendixs")
    private List<Appendix> goodsAppendixs;

    /**
     * 订单附件
     */
    @JsonProperty("orderAppendixs")
    private List<Appendix> orderAppendixs;

    /**
     * 下单时间
     */
    @JsonProperty("orderTime")
    private Long orderTime;

    /**
     * 其他附件
     */
    @JsonProperty("otherAppendixs")
    private List<Appendix> otherAppendixs;

    /**
     * 数量
     */
    @JsonProperty("quantity")
    private Integer quantity;

    /**
     * 物流信息
     */
    @JsonProperty("shipping")
    private Shipping shipping;

    /**
     * 类型
     */
    @JsonProperty("type")
    private String type;

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

    public String getTradeOrderId() {
        return tradeOrderId;
    }

    public void setTradeOrderId(String tradeOrderId) {
        this.tradeOrderId = tradeOrderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
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

    public List<Appendix> getContractAppendixs() {
        return contractAppendixs;
    }

    public void setContractAppendixs(List<Appendix> contractAppendixs) {
        this.contractAppendixs = contractAppendixs;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerWebsite() {
        return customerWebsite;
    }

    public void setCustomerWebsite(String customerWebsite) {
        this.customerWebsite = customerWebsite;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getEstimatedDeliverTime() {
        return estimatedDeliverTime;
    }

    public void setEstimatedDeliverTime(String estimatedDeliverTime) {
        this.estimatedDeliverTime = estimatedDeliverTime;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public List<Appendix> getGoodsAppendixs() {
        return goodsAppendixs;
    }

    public void setGoodsAppendixs(List<Appendix> goodsAppendixs) {
        this.goodsAppendixs = goodsAppendixs;
    }

    public List<Appendix> getOrderAppendixs() {
        return orderAppendixs;
    }

    public void setOrderAppendixs(List<Appendix> orderAppendixs) {
        this.orderAppendixs = orderAppendixs;
    }

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    public List<Appendix> getOtherAppendixs() {
        return otherAppendixs;
    }

    public void setOtherAppendixs(List<Appendix> otherAppendixs) {
        this.otherAppendixs = otherAppendixs;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class Appendix {
        /**
         * 文件URL
         */
        @JsonProperty("fileUrl")
        private String fileUrl;

        /**
         * 文件名称
         */
        @JsonProperty("name")
        private String name;

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Shipping {
        /**
         * 详细地址1
         */
        @JsonProperty("addressLine1")
        private String addressLine1;

        /**
         * 详细地址2
         */
        @JsonProperty("addressLine2")
        private String addressLine2;

        /**
         * 发货凭证
         */
        @JsonProperty("appendixs")
        private List<Appendix> appendixs;

        /**
         * 收货人
         */
        @JsonProperty("consigneeName")
        private String consigneeName;

        /**
         * 国家
         */
        @JsonProperty("country")
        private String country;

        /**
         * 报关单文件
         */
        @JsonProperty("cusDeclaAppendixs")
        private List<Appendix> cusDeclaAppendixs;

        /**
         * 报关单明细文件
         */
        @JsonProperty("cusDetailAppendixs")
        private List<Appendix> cusDetailAppendixs;

        /**
         * 报关单号
         */
        @JsonProperty("customsDeclarationNo")
        private String customsDeclarationNo;

        /**
         * 物流公司名称
         */
        @JsonProperty("logisticsCompany")
        private String logisticsCompany;

        /**
         * 物流单号
         */
        @JsonProperty("logisticsNo")
        private String logisticsNo;

        /**
         * 物流其他文件
         */
        @JsonProperty("otherAppendixs")
        private List<Appendix> otherAppendixs;

        /**
         * 电话
         */
        @JsonProperty("phone")
        private String phone;

        /**
         * 物流信息备注
         */
        @JsonProperty("remark")
        private String remark;

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

        public List<Appendix> getAppendixs() {
            return appendixs;
        }

        public void setAppendixs(List<Appendix> appendixs) {
            this.appendixs = appendixs;
        }

        public String getConsigneeName() {
            return consigneeName;
        }

        public void setConsigneeName(String consigneeName) {
            this.consigneeName = consigneeName;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public List<Appendix> getCusDeclaAppendixs() {
            return cusDeclaAppendixs;
        }

        public void setCusDeclaAppendixs(List<Appendix> cusDeclaAppendixs) {
            this.cusDeclaAppendixs = cusDeclaAppendixs;
        }

        public List<Appendix> getCusDetailAppendixs() {
            return cusDetailAppendixs;
        }

        public void setCusDetailAppendixs(List<Appendix> cusDetailAppendixs) {
            this.cusDetailAppendixs = cusDetailAppendixs;
        }

        public String getCustomsDeclarationNo() {
            return customsDeclarationNo;
        }

        public void setCustomsDeclarationNo(String customsDeclarationNo) {
            this.customsDeclarationNo = customsDeclarationNo;
        }

        public String getLogisticsCompany() {
            return logisticsCompany;
        }

        public void setLogisticsCompany(String logisticsCompany) {
            this.logisticsCompany = logisticsCompany;
        }

        public String getLogisticsNo() {
            return logisticsNo;
        }

        public void setLogisticsNo(String logisticsNo) {
            this.logisticsNo = logisticsNo;
        }

        public List<Appendix> getOtherAppendixs() {
            return otherAppendixs;
        }

        public void setOtherAppendixs(List<Appendix> otherAppendixs) {
            this.otherAppendixs = otherAppendixs;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
