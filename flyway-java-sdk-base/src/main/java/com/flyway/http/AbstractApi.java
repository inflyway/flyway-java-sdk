package com.flyway.http;

import com.flyway.common.FlywayConfig;
import com.flyway.common.model.CommonRequest;
import com.flyway.common.model.FileUploadRequest;
import com.flyway.exception.FlywayApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Inflyway SDK客户端
 */
public abstract class AbstractApi implements AutoCloseable {

    private static final Logger logger = LoggerFactory.getLogger(AbstractApi.class);

    protected FlywayConfig config;
    protected HttpClientUtil httpClient;

    /**
     * 使用指定配置构造客户端
     */
    public AbstractApi(FlywayConfig config) {
        this.config = config;
        this.httpClient = new HttpClientUtil(config);
    }

    /**
     * 执行POST请求
     */
    public <T> T execute(CommonRequest request, String path, Class<T> responseType) throws FlywayApiException {
        String url = config.getServerUrl() + path;
        // 请求接口
        return httpClient.postJsonWithEncryptionAndSignature(url, request, responseType,
                request.getToken(), config.getAesKey(), config.getRsaPrivateKey());
    }

    /**
     * 执行GET请求
     */
    public <T> T executeGet(CommonRequest request, String path, Class<T> responseType) throws FlywayApiException {
        logger.debug("执行GET请求: {}{}", config.getServerUrl(), path);
            // GET请求接口 - 将请求参数转换为查询字符串
        return httpClient.getJsonWithEncryptionAndSignature(config.getServerUrl() + path, request, responseType,
                request.getToken(), config.getAesKey(), config.getRsaPrivateKey());
    }
    /**
     * 执行GET请求（无请求体版本）
     */
    public <T> T executeGet(String token, String path, Class<T> responseType) throws FlywayApiException {
        logger.debug("执行简单GET请求: {}{}", config.getServerUrl(), path);
        // GET请求接口（仅token认证）
        return httpClient.getWithToken(config.getServerUrl() + path, responseType, token, config.getAesKey(), config.getRsaPrivateKey());
    }
    /**
     * 执行GET请求（带查询参数字符串）
     */
    public <T> T executeGetWithParams(String path, String queryParams, String token, Class<T> responseType) throws FlywayApiException {
        String url = config.getServerUrl() + path;
        if (queryParams != null && !queryParams.isEmpty()) {
            url += (url.contains("?") ? "&" : "?") + queryParams;
        }

        logger.debug("执行带参数GET请求: {}", url);

            return httpClient.getWithToken(url, responseType, token, config.getAesKey(), config.getRsaPrivateKey());
    }

    /**
     * 处理回调请求，包括验签和解密
     *
     * @param request HttpServletRequest对象
     * @return 解密后的JSON报文，处理失败返回null
     */
    public String handleCallbackRequest(HttpServletRequest request) {
        return httpClient.handleCallback(request);
    }

    public <T> T multipartExecute(FileUploadRequest request, String path, Class<T> responseType) throws FlywayApiException {
        String url = config.getFileServerUrl() + path;
        // 请求接口
        return  httpClient.postFormDataWithEncryptionAndSignature(url, request, responseType,
                request.getToken(), config.getAesKey(), config.getRsaPrivateKey());
    }

    /**
     * 关闭客户端，释放资源
     */
    public void close() {
        if (httpClient != null) {
            httpClient.close();
        }
    }
}