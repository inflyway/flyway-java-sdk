package com.flyway.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.flyway.common.FlywayConfig;
import com.flyway.common.model.CipherResponse;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;
import com.flyway.util.OpenAesUtil;
import com.flyway.util.OpenRsaUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * HTTP客户端工具类
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private final FlywayConfig config;
    private final ObjectMapper objectMapper;
    private final CloseableHttpClient httpClient;

    public HttpClientUtil(FlywayConfig config) {
        this.config = config;
        this.objectMapper = new ObjectMapper();
        this.httpClient = createHttpClient();
    }

    private CloseableHttpClient createHttpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(config.getConnectTimeout())
                .setSocketTimeout(config.getReadTimeout())
                .setConnectionRequestTimeout(config.getConnectTimeout())
                .build();

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    /**
     * 发送POST JSON请求（带加密和签名）
     */
    public <T> T postJsonWithEncryptionAndSignature(String url, Object requestBody, Class<T> responseType, String token, String aesKey, String rsaPrivateKey) throws FlywayApiException {
        HttpPost httpPost = null;
        HttpResponse response = null;

        try {
            httpPost = new HttpPost(url);

            // 设置基本请求头
            httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");

            // 设置认证头
            if (token != null && !token.trim().isEmpty()) {
                httpPost.setHeader("Authorization", token);
            }

            // 设置请求ID
            String requestId = UUID.randomUUID().toString().replace("-", "");
            httpPost.setHeader("Request-ID", requestId);

            // 设置时间戳
            long timestamp = System.currentTimeMillis();
            httpPost.setHeader("Tuotuo-Timestamp", String.valueOf(timestamp));

            if (config.isDebug()) {
                logger.info("Request URL: {}", url);
                logger.info("Request Authorization: {}", token);

            }

            // RSA签名
            String dataToSign = requestId + "&" + timestamp;
            String signature = OpenRsaUtil.rsaSign(dataToSign, rsaPrivateKey);
            httpPost.setHeader("Tuotuo-Signature", signature);
            if (config.isDebug()) {
                for (Header allHeader : httpPost.getAllHeaders()) {
                    logger.info("Header: {} - {}", allHeader.getName(), allHeader.getValue());
                }
            }

            // 设置请求体
            if (requestBody != null) {
                String jsonContent = objectMapper.writeValueAsString(requestBody);

                // AES加密请求体
                String encryptedContent = OpenAesUtil.encryptAndBase64Encode(jsonContent, aesKey);

                Map<String, String> realBody = new HashMap<>();
                realBody.put("ciphertext", encryptedContent);
                String realJson = objectMapper.writeValueAsString(realBody);

                StringEntity entity = new StringEntity(realJson, StandardCharsets.UTF_8);

                if (config.isDebug()) {
                    logger.info("Original Request Body: {}", jsonContent);
                    logger.info("Encrypted Request Body: {}", encryptedContent);
                    logger.info("real Request Body: {}", realJson);
                }
                httpPost.setEntity(entity);
            }
            // 发送请求
            response = httpClient.execute(httpPost);

            // 处理响应
            return handleResponse2(response, responseType);

        } catch (IOException e) {
            logger.error("HTTP request failed", e);
            throw new FlywayApiException(500, "HTTP request failed: " + e.getMessage(), e);
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
    }

    /**
     * 发送POST表单URL编码请求（用于OAuth2 token请求）
     */
    public <T> T postFormUrlEncoded(String url, Class<T> responseType) throws FlywayApiException {
        HttpPost httpPost = null;
        HttpResponse response = null;
        try {
            httpPost = new HttpPost(url);

            // 设置请求头
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("User-Agent", "Inflyway-Java-SDK/1.0.0");
            httpPost.setHeader("Connection", "keep-alive");
            if (config.isDebug()) {
                logger.info("Form Request URL: {}", url);
                logger.info("Form Request Headers: Content-Type=application/x-www-form-urlencoded");
            }

            // 发送请求（URL中已包含参数，不需要设置请求体）
            response = httpClient.execute(httpPost);

            // 处理响应
            return handleResponse(response, responseType);

        } catch (IOException e) {
            logger.error("HTTP form request failed", e);
            throw new FlywayApiException(500, "HTTP form request failed: " + e.getMessage(), e);
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
    }

    /**
     * 处理HTTP响应
     */
    private <T> T handleResponse(HttpResponse response, Class<T> responseType) throws FlywayApiException, IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity responseEntity = response.getEntity();
        String responseContent = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);

        if (config.isDebug()) {
            logger.info("Response Status: {}", statusCode);
            logger.info("Response Body: {}", responseContent);
        }

        if (statusCode >= 200 && statusCode < 300) {
            T t = objectMapper.readValue(responseContent, responseType);
            return t;
        } else {
            throw new FlywayApiException(statusCode, statusCode,
                    "HTTP request failed with status: " + statusCode + ", response: " + responseContent);
        }
    }

    /**
     * 处理HTTP响应
     */
    private <T> T handleResponse2(HttpResponse response, Class<T> responseType) throws FlywayApiException, IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity responseEntity = response.getEntity();
        String responseContent = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);

        if (config.isDebug()) {
            logger.info("Response Status: {}", statusCode);
            logger.info("Response Body: {}", responseContent);
        }

        if (statusCode >= 200 && statusCode < 300) {
            CipherResponse cipherResponse = objectMapper.readValue(responseContent, CipherResponse.class);
            String jsonData = OpenAesUtil.decryptAfterBase64Decode(cipherResponse.getData().getCiphertext(), config.getAesKey());
            if (StringUtils.isNotBlank(jsonData) && !"null".equals(jsonData)) {
                // 构建完整的响应JSON对象
                ObjectNode fullResponseNode = objectMapper.createObjectNode();
                fullResponseNode.put("code", cipherResponse.getCode());
                fullResponseNode.put("message", cipherResponse.getMessage());

                // 将解密后的data内容添加到响应JSON中
                JsonNode dataNode = objectMapper.readTree(jsonData);
                fullResponseNode.set("data", dataNode);

                // 直接反序列化整个响应对象，让Jackson处理所有字段映射和类型转换
                // 使用Reader配置忽略未知属性，提供更好的兼容性
                T t = objectMapper.readerFor(responseType)
                        .without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                        .readValue(fullResponseNode.toString());
                return t;
            }
            // 异常场景：解密后数据为空或null，返回对象但忽略data
            if (CommonResponse.class.isAssignableFrom(responseType)) {
                try {
                    T errorResponse = responseType.getDeclaredConstructor().newInstance();
                    if (errorResponse instanceof CommonResponse) {
                        CommonResponse commonResponse = (CommonResponse) errorResponse;
                        commonResponse.setCode(cipherResponse.getCode());
                        commonResponse.setMessage(cipherResponse.getMessage());
                    }
                    return errorResponse;
                } catch (Exception e) {
                    logger.error("Failed to create response object for empty data scenario", e);
                    throw new FlywayApiException(statusCode, "Failed to process empty response data: " + e.getMessage(), e);
                }
            } else {
                throw new FlywayApiException(statusCode, "Response data is empty, cannot create response object");
            }
        } else {
            throw new FlywayApiException(statusCode, statusCode,
                    "HTTP request failed with status: " + statusCode + ", response: " + responseContent);
        }
    }

    /**
     * 关闭HTTP客户端
     */
    public void close() {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            logger.warn("Failed to close HTTP client", e);
        }
    }
}
