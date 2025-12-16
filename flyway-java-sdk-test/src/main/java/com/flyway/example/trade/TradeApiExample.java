package com.flyway.example.trade;


import com.alibaba.fastjson.JSON;
import com.flyway.common.FlywayConfig;
import com.flyway.common.TokenApi;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;
import com.flyway.trade.OpenTradeApi;
import com.flyway.trade.model.TradeOderBindInfo;
import com.flyway.trade.model.TradeOderCreateInfo;
import com.flyway.trade.model.TradeOderCreateRequest;
import com.flyway.trade.model.TradeOrderBindRequest;
import com.flyway.trade.model.TradeOrderQueryAuditInfo;
import com.flyway.trade.model.TradeOrderQueryAuditRequest;
import com.flyway.trade.model.TradeOrderQueryInfo;
import com.flyway.trade.model.TradeOrderQueryRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Inflyway API调用示例
 * <p>
 * 使用前请先配置OAuth2认证信息：
 * <p>
 * 方式1：修改配置文件 src/main/resources/inflyway-sdk.properties
 * inflyway.oauth2.client.id=您的实际client_id
 * inflyway.oauth2.client.secret=您的实际client_secret
 * <p>
 * 方式：直接在代码中配置（参见 example 方法）
 * <p>
 * 注意：如果没有正确配置认证信息，会出现401错误："未携带token"
 */
public class TradeApiExample {

    public static void main(String[] args) {
        createTradeExample();
//        queryDetailExample();
//        bindAndApplyExample();
//        queryFlowIDStatusExample();
    }

    /**
     * 创建交易单示例
     */
    public static void createTradeExample() {
        try {
            System.out.println(">> 直接在代码中配置认证信息（带加密和签名）：");

            // 1： 飞来汇openApi配置信息
            String clientId = "";
            String clientSecret = "";
            String aesKey = ""; // 16位AES密钥
            String rsaPrivateKey = "";
            // 2： 设置配置
            FlywayConfig flywayConfig = new FlywayConfig();
            flywayConfig.setServerUrl("https://open-test.inflyway.com");//测试环境地址
            flywayConfig.setClientId(clientId);
            flywayConfig.setClientSecret(clientSecret);
            flywayConfig.setAesKey(aesKey);
            flywayConfig.setRsaPrivateKey(rsaPrivateKey);
            flywayConfig.setDebug(true);

            // 3： 获取token（有效期2小时）
            TokenApi tokenApi = new TokenApi(flywayConfig);
            String token = tokenApi.getToken();

            // 4： 声明api对象并调用api
            OpenTradeApi openTradeApi = new OpenTradeApi(flywayConfig);
            TradeOderCreateRequest tradeOderCreateRequest = new TradeOderCreateRequest();
            tradeOderCreateRequest.setAmount(new BigDecimal("200"));
            tradeOderCreateRequest.setBuyerName("买家名称");

            // 附件信息
            List<TradeOderCreateRequest.Appendix> clearanceAppendixs = new ArrayList<>();
            TradeOderCreateRequest.Appendix clearanceAppendix = new TradeOderCreateRequest.Appendix();
            clearanceAppendix.setFileUrl("ttwallet/1764748803400_e5fb6268e9407ab5c0942af119834767.png");
            clearanceAppendix.setName("进口清关文件.png");
            clearanceAppendixs.add(clearanceAppendix);
            tradeOderCreateRequest.setClearanceAppendixs(clearanceAppendixs);

            List<TradeOderCreateRequest.Appendix> clearanceProductsAppendixs = new ArrayList<>();
            TradeOderCreateRequest.Appendix productsAppendix = new TradeOderCreateRequest.Appendix();
            productsAppendix.setFileUrl("ttwallet/1764748803400_e5fb6268e9407ab5c0942af119834767.png");
            productsAppendix.setName("清关产品图片.png");
            clearanceProductsAppendixs.add(productsAppendix);
            tradeOderCreateRequest.setClearanceProductsAppendixs(clearanceProductsAppendixs);

            List<TradeOderCreateRequest.Appendix> contractAppendixs = new ArrayList<>();
            TradeOderCreateRequest.Appendix contractAppendix = new TradeOderCreateRequest.Appendix();
            contractAppendix.setFileUrl("ttwallet/1764748803400_e5fb6268e9407ab5c0942af119834767.png");
            contractAppendix.setName("合同文件.png");
            contractAppendixs.add(contractAppendix);
            tradeOderCreateRequest.setContractAppendixs(contractAppendixs);

            List<TradeOderCreateRequest.Appendix> goodsAppendixs = new ArrayList<>();
            TradeOderCreateRequest.Appendix goodsAppendix = new TradeOderCreateRequest.Appendix();
            goodsAppendix.setFileUrl("ttwallet/1764748803400_e5fb6268e9407ab5c0942af119834767.png");
            goodsAppendix.setName("商品采购明细.png");
            goodsAppendixs.add(goodsAppendix);
            tradeOderCreateRequest.setGoodsAppendixs(goodsAppendixs);

            List<TradeOderCreateRequest.Appendix> orderAppendixs = new ArrayList<>();
            TradeOderCreateRequest.Appendix orderAppendix = new TradeOderCreateRequest.Appendix();
            orderAppendix.setFileUrl("ttwallet/1764748747299_5bfa07d322d1f454bfb72605750a4d7c.jpg");
            orderAppendix.setName("PICI.jpg");
            orderAppendixs.add(orderAppendix);
            tradeOderCreateRequest.setOrderAppendixs(orderAppendixs);

            List<TradeOderCreateRequest.Appendix> otherAppendixs = new ArrayList<>();
            TradeOderCreateRequest.Appendix otherAppendix = new TradeOderCreateRequest.Appendix();
            otherAppendix.setFileUrl("ttwallet/1764748747299_5bfa07d322d1f454bfb72605750a4d7c.jpg");
            otherAppendix.setName("other.jpg");
            otherAppendixs.add(otherAppendix);
            tradeOderCreateRequest.setOtherAppendixs(otherAppendixs);

            // 物流信息
            List<TradeOderCreateRequest.Shipping> shippings = new ArrayList<>();
            TradeOderCreateRequest.Shipping shipping = new TradeOderCreateRequest.Shipping();
            shipping.setAddressLine1("详细地址1");
            shipping.setAddressLine2("详细地址2");
            shipping.setConsigneeName("收货人");
            shipping.setCountry("CN");
            shipping.setCustomsDeclarationNo("bgdh20251205");
            shipping.setLogisticsCompany("物流公司名称");
            shipping.setLogisticsNo("wldh20251205");
            shipping.setPhone("18395552222");
            shipping.setRemark("物流信息备注");
            List<TradeOderCreateRequest.Appendix> shippingAppendixs = new ArrayList<>();
            TradeOderCreateRequest.Appendix shippingAppendix = new TradeOderCreateRequest.Appendix();
            shippingAppendix.setFileUrl("ttwallet/1764748785205_af056c1fed84c90ee76420106644f28b.jpg");
            shippingAppendix.setName("发货凭证.jpg");
            shippingAppendixs.add(shippingAppendix);
            shipping.setAppendixs(shippingAppendixs);
            List<TradeOderCreateRequest.Appendix> cusDeclaAppendixs = new ArrayList<>();
            TradeOderCreateRequest.Appendix cusDeclaAppendix = new TradeOderCreateRequest.Appendix();
            cusDeclaAppendix.setFileUrl("ttwallet/1764748785205_af056c1fed84c90ee76420106644f28b.jpg");
            cusDeclaAppendix.setName("报关单文件.jpg");
            cusDeclaAppendixs.add(cusDeclaAppendix);
            shipping.setCusDeclaAppendixs(cusDeclaAppendixs);
            List<TradeOderCreateRequest.Appendix> cusDetailAppendixs = new ArrayList<>();
            TradeOderCreateRequest.Appendix cusDetailAppendix = new TradeOderCreateRequest.Appendix();
            cusDetailAppendix.setFileUrl("ttwallet/1764748785205_af056c1fed84c90ee76420106644f28b.jpg");
            cusDetailAppendix.setName("报关单明细文件.jpg");
            cusDetailAppendixs.add(cusDetailAppendix);
            shipping.setCusDetailAppendixs(cusDetailAppendixs);
            List<TradeOderCreateRequest.Appendix> shippingOtherAppendixs = new ArrayList<>();
            TradeOderCreateRequest.Appendix shippingOtherAppendix = new TradeOderCreateRequest.Appendix();
            shippingOtherAppendix.setFileUrl("ttwallet/1764748785205_af056c1fed84c90ee76420106644f28b.jpg");
            shippingOtherAppendix.setName("物流其他文件.jpg");
            shippingOtherAppendixs.add(shippingOtherAppendix);
            shipping.setOtherAppendixs(shippingOtherAppendixs);

            shippings.add(shipping);
            tradeOderCreateRequest.setShippings(shippings);

            // 其他基本信息
            tradeOderCreateRequest.setCurrency("USD");
            tradeOderCreateRequest.setCustomerWebsite("https://www.baidu.com/");
            tradeOderCreateRequest.setDeliveryMethod("EXW");
            tradeOderCreateRequest.setDeliveryTime("1764839724000");
            tradeOderCreateRequest.setEstimatedDeliverTime("");
            tradeOderCreateRequest.setGoodName("交易名称");
            tradeOderCreateRequest.setMainTradeType("GOODS_TRADE");
            tradeOderCreateRequest.setOrderTime(1762011510673L);
            tradeOderCreateRequest.setQuantity(1);
            tradeOderCreateRequest.setType("1");
            tradeOderCreateRequest.setOpenID("223344556677");
            tradeOderCreateRequest.setToken(token);
            tradeOderCreateRequest.setRequestNo("20251127000001");
            CommonResponse<TradeOderCreateInfo> tradeOrderId = openTradeApi.createTrade(tradeOderCreateRequest);
            System.out.println(JSON.toJSONString(tradeOrderId));

        } catch (FlywayApiException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 查询入账交易单详情示例
     */
    public static void queryDetailExample() {
        try {
            System.out.println(">> 直接在代码中配置认证信息（带加密和签名）：");

            // 1： 飞来汇openApi配置信息
            String clientId = "";
            String clientSecret = "";
            String aesKey = ""; // 16位AES密钥
            String rsaPrivateKey = "";
            // 2： 设置配置
            FlywayConfig flywayConfig = new FlywayConfig();
            flywayConfig.setServerUrl("https://open-test.inflyway.com");//测试环境地址
            flywayConfig.setClientId(clientId);
            flywayConfig.setClientSecret(clientSecret);
            flywayConfig.setAesKey(aesKey);
            flywayConfig.setRsaPrivateKey(rsaPrivateKey);
            flywayConfig.setDebug(true);

            // 3： 获取token（有效期2小时）
            TokenApi tokenApi = new TokenApi(flywayConfig);
            String token = tokenApi.getToken();

            // 4： 声明api对象并调用api
            OpenTradeApi openTradeApi = new OpenTradeApi(flywayConfig);
            TradeOrderQueryRequest tradeOrderQueryRequest = new TradeOrderQueryRequest();
            tradeOrderQueryRequest.setTradeOrderId("2000490084782923777");
            tradeOrderQueryRequest.setOpenID("223344556677");
            tradeOrderQueryRequest.setToken(token);
            CommonResponse<TradeOrderQueryInfo> tradeOrderQueryResponse = openTradeApi.queryTrade(tradeOrderQueryRequest);
            System.out.println(JSON.toJSONString(tradeOrderQueryResponse));

        } catch (FlywayApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关联入账流水并发起入账申请 示例
     */
    public static void bindAndApplyExample() {
        try {
            System.out.println(">> 直接在代码中配置认证信息（带加密和签名）：");

            // 1： 飞来汇openApi配置信息
            String clientId = "";
            String clientSecret = "";
            String aesKey = ""; // 16位AES密钥
            String rsaPrivateKey = "";
            // 2： 设置配置
            FlywayConfig flywayConfig = new FlywayConfig();
            flywayConfig.setServerUrl("https://open-test.inflyway.com");//测试环境地址
            flywayConfig.setClientId(clientId);
            flywayConfig.setClientSecret(clientSecret);
            flywayConfig.setAesKey(aesKey);
            flywayConfig.setRsaPrivateKey(rsaPrivateKey);
            flywayConfig.setDebug(true);

            // 3： 获取token（有效期2小时）
            TokenApi tokenApi = new TokenApi(flywayConfig);
            String token = tokenApi.getToken();

            // 4： 声明api对象并调用api
            OpenTradeApi openTradeApi = new OpenTradeApi(flywayConfig);
            TradeOrderBindRequest tradeOrderBindRequest = new TradeOrderBindRequest();
            tradeOrderBindRequest.setFlowId("FTT20251212191241982000720");
            List<TradeOrderBindRequest.TradeOrder> tradeOrderList = new ArrayList<>();
            TradeOrderBindRequest.TradeOrder tradeOrder = new TradeOrderBindRequest.TradeOrder();
            tradeOrder.setTradeOrderId("1999415577426599937");
            tradeOrder.setMatchAmount(new BigDecimal("1000"));
            tradeOrderList.add(tradeOrder);
            TradeOrderBindRequest.TradeOrder tradeOrder2 = new TradeOrderBindRequest.TradeOrder();
            tradeOrder2.setTradeOrderId("1999435790570299394");
            tradeOrder2.setMatchAmount(new BigDecimal("300"));
            tradeOrderList.add(tradeOrder2);
            tradeOrderBindRequest.setTradeOrderList(tradeOrderList);
            tradeOrderBindRequest.setOpenID("223344556677");
            tradeOrderBindRequest.setRequestNo("202512150001");
            tradeOrderBindRequest.setCallbackUrl("https://www.baidu.com");
            tradeOrderBindRequest.setToken(token);
            CommonResponse<TradeOderBindInfo> tradeOderBindResponse = openTradeApi.bindOpenTrade(tradeOrderBindRequest);
            System.out.println(JSON.toJSONString(tradeOderBindResponse));

        } catch (FlywayApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 入账申请审核结果查询示例
     */
    public static void queryFlowIDStatusExample() {
        try {
            System.out.println(">> 直接在代码中配置认证信息（带加密和签名）：");

            // 1： 飞来汇openApi配置信息
            String clientId = "";
            String clientSecret = "";
            String aesKey = ""; // 16位AES密钥
            String rsaPrivateKey = "";
            // 2： 设置配置
            FlywayConfig flywayConfig = new FlywayConfig();
            flywayConfig.setServerUrl("https://open-test.inflyway.com");//测试环境地址
            flywayConfig.setClientId(clientId);
            flywayConfig.setClientSecret(clientSecret);
            flywayConfig.setAesKey(aesKey);
            flywayConfig.setRsaPrivateKey(rsaPrivateKey);
            flywayConfig.setDebug(true);

            // 3： 获取token（有效期2小时）
            TokenApi tokenApi = new TokenApi(flywayConfig);
            String token = tokenApi.getToken();

            // 4： 声明api对象并调用api
            OpenTradeApi openTradeApi = new OpenTradeApi(flywayConfig);
            TradeOrderQueryAuditRequest queryAuditRequest = new TradeOrderQueryAuditRequest();
            queryAuditRequest.setAuditId("RZSH20251215103422160008677");
            queryAuditRequest.setOpenID("223344556677");
            queryAuditRequest.setToken(token);
            CommonResponse<TradeOrderQueryAuditInfo> tradeOrderQueryAuditResponse = openTradeApi.queryAuditStatus(queryAuditRequest);
            System.out.println(JSON.toJSONString(tradeOrderQueryAuditResponse));

        } catch (FlywayApiException e) {
            throw new RuntimeException(e);
        }
    }
}