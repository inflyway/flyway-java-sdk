package com.flyway.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.flyway.common.FlywayConfig;
import com.flyway.common.model.CipherResponse;
import com.flyway.common.model.CommonResponse;
import com.flyway.common.model.FileUploadRequest;
import com.flyway.exception.FlywayApiException;
import com.flyway.util.JsonUtil;
import com.flyway.util.OpenAesUtil;
import com.flyway.util.OpenRsaUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
        this.objectMapper = JsonUtil.getObjectMapper();
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
    public <T> T postJsonWithEncryptionAndSignature(String url, Object requestBody, TypeReference<T> responseType, String token, String aesKey, String rsaPrivateKey) throws FlywayApiException {
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
            return handleResponseWithTypeRef(response, responseType);

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
     * 发送GET请求（带加密和签名）
     */
    public <T> T getJsonWithEncryptionAndSignature(String url, Object requestParams, TypeReference<T> responseType, String token, String aesKey, String rsaPrivateKey) throws FlywayApiException {
        HttpGet httpGet = null;
        HttpResponse response = null;

        try {
            // 如果有查询参数，将其添加到URL中
            String finalUrl = buildUrlWithParams(url, requestParams);
            httpGet = new HttpGet(finalUrl);

            // 设置基本请求头
            httpGet.setHeader("Accept", "application/json");

            // 设置认证头
            if (token != null && !token.trim().isEmpty()) {
                httpGet.setHeader("Authorization", token);
            }

            // 设置请求ID
            String requestId = UUID.randomUUID().toString().replace("-", "");
            httpGet.setHeader("Request-ID", requestId);

            // 设置时间戳
            long timestamp = System.currentTimeMillis();
            httpGet.setHeader("Tuotuo-Timestamp", String.valueOf(timestamp));

            if (config.isDebug()) {
                logger.info("GET Request URL: {}", finalUrl);
                logger.info("GET Request Authorization: {}", token);
            }

            // RSA签名
            String dataToSign = requestId + "&" + timestamp;
            String signature = OpenRsaUtil.rsaSign(dataToSign, rsaPrivateKey);
            httpGet.setHeader("Tuotuo-Signature", signature);
            if (config.isDebug()) {
                for (Header allHeader : httpGet.getAllHeaders()) {
                    logger.info("GET Header: {} - {}", allHeader.getName(), allHeader.getValue());
                }
            }

            // 发送请求
            response = httpClient.execute(httpGet);

            // 处理响应
            return handleResponseWithTypeRef(response, responseType);
        } catch (IOException e) {
            logger.error("HTTP GET request failed", e);
            throw new FlywayApiException(500, "HTTP GET request failed: " + e.getMessage(), e);
        } finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
        }
    }

    /**
     * 构建带参数的URL
     */
    private String buildUrlWithParams(String url, Object requestParams) throws IOException {
        if (requestParams == null) {
            return url;
        }

        // 将请求对象转换为Map
        String json = objectMapper.writeValueAsString(requestParams);
        JsonNode jsonNode = objectMapper.readTree(json);

        StringBuilder urlBuilder = new StringBuilder(url);
        boolean hasParams = url.contains("?");

        jsonNode.fields().forEachRemaining(entry -> {
            String key = entry.getKey();
            JsonNode value = entry.getValue();

            // 跳过null值和token字段
            if (!value.isNull() && !"token".equals(key)) {
                if (!hasParams && !urlBuilder.toString().contains("?")) {
                    urlBuilder.append("?");
                } else {
                    urlBuilder.append("&");
                }
                urlBuilder.append(key).append("=").append(value.asText());
            }
        });

        return urlBuilder.toString();
    }

    /**
     * 发送POST JSON请求（带加密和签名）
     */
    public <T> T postFormDataWithEncryptionAndSignature(String url, FileUploadRequest requestBody, TypeReference<T> responseType, String token, String aesKey, String rsaPrivateKey) throws FlywayApiException {
        HttpPost httpPost = null;
        HttpResponse response = null;

        try {
            httpPost = new HttpPost(url);

            // 设置基本请求头
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
                MultipartEntityBuilder multipartBuilder = MultipartEntityBuilder.create()
                        .setCharset(StandardCharsets.UTF_8)
                        .setContentType(ContentType.MULTIPART_FORM_DATA);
                File file = requestBody.getFile();
                if (file != null&& file.exists() && file.isFile()) {
                    byte[] fileBytes = Files.readAllBytes(file.toPath());
                    ContentType contentType = ContentType.APPLICATION_OCTET_STREAM;
                    // 构建文件体（使用原文件名 + 文件内容）
                    // 3. 构建文件体（原文件名用 file.getName() 替代 file.getOriginalFilename()）
                    ByteArrayBody fileBody = new ByteArrayBody(
                            fileBytes,
                            contentType,
                            file.getName() // 获取File的文件名（如 test.png）
                    );
                    multipartBuilder.addPart("file", fileBody);

                    if (config.isDebug()) {
                        logger.info("Upload file name: {}, size: {} bytes",
                                file.getName(), file.length());
                    }
                }

                // 7.2 处理普通参数（requestNo + openID）- 加密后传入
                Map<String, String> normalParams = new HashMap<>();
                normalParams.put("requestNo", requestBody.getRequestNo());
                normalParams.put("openID", requestBody.getOpenID());
                normalParams.put("biz", requestBody.getBiz());

                // 加密普通参数（转为JSON后AES加密 + Base64编码）
                String normalParamsJson = objectMapper.writeValueAsString(normalParams);

                // AES加密请求体
                String encryptedParams = OpenAesUtil.encryptAndBase64Encode(normalParamsJson, aesKey);

                // 将加密后的参数以 form-data 字段传入（字段名：ciphertext）
                StringBody ciphertextBody = new StringBody(
                        encryptedParams,
                        ContentType.TEXT_PLAIN.withCharset(StandardCharsets.UTF_8)
                );
                multipartBuilder.addPart("ciphertext", ciphertextBody);

                if (config.isDebug()) {
                    logger.info("Original Request Body: {}", normalParamsJson);
                    logger.info("Encrypted Request Body: {}", encryptedParams);
                }
                   httpPost.setEntity(multipartBuilder.build());
                }
                // 发送请求
                response = httpClient.execute(httpPost);

                // 处理响应
                return handleResponseWithTypeRef(response, responseType);

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
    private <T> T handleResponseWithTypeRef(HttpResponse response, TypeReference<T> responseType) throws FlywayApiException, IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity responseEntity = response.getEntity();
        String responseContent = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);

        if (config.isDebug()) {
            logger.info("Response Status: {}", statusCode);
            logger.info("Response Body: {}", responseContent);
        }

        if (statusCode >= 200 && statusCode < 300) {
            CipherResponse cipherResponse = objectMapper.readValue(responseContent, CipherResponse.class);
            // 检查cipherResponse及其data是否为null
            String jsonData = null;
            if (cipherResponse != null && cipherResponse.getData() != null) {
                jsonData = OpenAesUtil.decryptAfterBase64Decode(cipherResponse.getData().getCiphertext(), config.getAesKey());
            }

            if (config.isDebug()) {
                logger.info("Response Body decrypt: {}", jsonData);
            }
            
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
            
            return createEmptyResponseWithTypeRef(responseType, cipherResponse);
        } else {
            throw new FlywayApiException(statusCode, statusCode,
                    "HTTP request failed with status: " + statusCode + ", response: " + responseContent);
        }
    }

    /**
     * 创建空响应对象 (TypeReference版本)
     */
    private <T> T createEmptyResponseWithTypeRef(TypeReference<T> responseType, CipherResponse cipherResponse) throws FlywayApiException {
        try {
            // 获取TypeReference的原始类型
            java.lang.reflect.Type type = responseType.getType();
            
            // 尝试直接创建CommonResponse实例
            if (isCommonResponseType(type)) {
                T response = createCommonResponseInstance(type);
                if (response instanceof CommonResponse) {
                    CommonResponse commonResponse = (CommonResponse) response;
                    commonResponse.setCode(cipherResponse.getCode());
                    commonResponse.setMessage(cipherResponse.getMessage());
                }
                return response;
            }
            
            // 回退到ObjectMapper方式
            T errorResponse = objectMapper.readerFor(responseType)
                    .without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .readValue("{}");
            
            // 如果是CommonResponse类型，设置code和message
            if (errorResponse instanceof CommonResponse) {
                CommonResponse commonResponse = (CommonResponse) errorResponse;
                commonResponse.setCode(cipherResponse.getCode());
                commonResponse.setMessage(cipherResponse.getMessage());
            }
            return errorResponse;
        } catch (Exception e) {
            logger.error("Failed to create response object for empty data scenario", e);
            throw new FlywayApiException(cipherResponse.getCode(), 
                "Failed to process empty response data: " + e.getMessage(), e);
        }
    }

    /**
     * 检查是否为CommonResponse类型
     */
    private boolean isCommonResponseType(java.lang.reflect.Type type) {
        if (type instanceof Class) {
            return CommonResponse.class.isAssignableFrom((Class<?>) type);
        } else if (type instanceof java.lang.reflect.ParameterizedType) {
            java.lang.reflect.ParameterizedType paramType = (java.lang.reflect.ParameterizedType) type;
            Class<?> rawClass = (Class<?>) paramType.getRawType();
            return CommonResponse.class.isAssignableFrom(rawClass);
        }
        return false;
    }

    /**
     * 创建CommonResponse实例
     */
    private <T> T createCommonResponseInstance(java.lang.reflect.Type type) throws Exception {
        if (type instanceof Class) {
            return (T) ((Class<?>) type).getDeclaredConstructor().newInstance();
        } else if (type instanceof java.lang.reflect.ParameterizedType) {
            java.lang.reflect.ParameterizedType paramType = (java.lang.reflect.ParameterizedType) type;
            Class<?> rawClass = (Class<?>) paramType.getRawType();
            return (T) rawClass.getDeclaredConstructor().newInstance();
        }
        throw new IllegalArgumentException("Unsupported type: " + type);
    }

    /**
     * 处理回调请求，包括验签和解密
     *
     * @param request HttpServletRequest对象
     * @return 解密后的JSON报文，处理失败返回null
     */
    public String handleCallback(HttpServletRequest request) {
        try {
            // 从Header获取必要参数
            String timestamp = request.getHeader("Tuotuo-Timestamp");
            String requestId = request.getHeader("Request-ID");
            String signature = request.getHeader("Tuotuo-Signature");

            if (timestamp == null || requestId == null || signature == null) {
                logger.warn("回调请求缺少必要的Header参数");
                return null;
            }

            // 验签
            String verifyData = requestId + "&" + timestamp;
            String publicKey = config.getFlywayRsaPublicKey();
            if (publicKey == null || publicKey.isEmpty()) {
                logger.warn("验签公钥未配置");
                return null;
            }

            try {
                OpenRsaUtil.rsaVerify(verifyData, publicKey, signature);
                logger.debug("回调请求验签成功");
            } catch (Exception e) {
                logger.warn("回调请求验签失败: {}", e.getMessage());
                return null;
            }

            // 读取请求体
            String body = readRequestBody(request);
            if (body == null || body.isEmpty()) {
                logger.warn("回调请求体为空");
                return null;
            }

            // 解析并解密数据
            try {
                String decryptedData = OpenAesUtil.decryptAfterBase64Decode(body, config.getAesKey());
                logger.debug("回调请求解密成功");
                return decryptedData;
            } catch (Exception e) {
                logger.warn("回调请求解密失败: {}", e.getMessage());
                return null;
            }
        } catch (Exception e) {
            logger.error("处理回调请求时发生错误", e);
            return null;
        }
    }

    /**
     * 读取HttpServletRequest的请求体
     *
     * @param request HttpServletRequest对象
     * @return 请求体内容
     * @throws IOException IO异常
     */
    private String readRequestBody(HttpServletRequest request) throws IOException {
        try (BufferedReader reader = request.getReader()) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
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
