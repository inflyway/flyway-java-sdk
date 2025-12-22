package com.flyway.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flyway.common.FlywayConfig;
import com.flyway.common.model.CommonRequest;
import com.flyway.common.model.FileUploadRequest;
import com.flyway.exception.FlywayApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
     * 执行POST请求 支持TypeReference
     */
    public <T> T executeWithTypeRef(CommonRequest request, String path, TypeReference<T> responseType) throws FlywayApiException {
        String url = config.getServerUrl() + path;
        // 请求接口
        return httpClient.postJsonWithEncryptionAndSignature(url, request, responseType,
                request.getToken(), config.getAesKey(), config.getRsaPrivateKey());
    }

    /**
     * 执行GET请求 支持TypeReference
     */
    public <T> T executeGetWithTypeRef(CommonRequest request, String path, TypeReference<T> responseType) throws FlywayApiException {
        logger.debug("执行GET请求: {}{}", config.getServerUrl(), path);
        // GET请求接口 - 将请求参数转换为查询字符串
        return httpClient.getJsonWithEncryptionAndSignature(config.getServerUrl() + path, request, responseType,
                request.getToken(), config.getAesKey(), config.getRsaPrivateKey());
    }

    /**
     * 处理回调请求，包括验签和解密
     *
     * @param request HttpServletRequest对象
     * @return 解密后的JSON报文，处理失败返回null
     */
    public String handleCallbackRequest(HttpServletRequest request) {
        return httpClient.handleCallbackRequest(request);
    }

    /**
     * 处理回调响应，生成加密的响应消息并写入HttpServletResponse
     *
     * @param response HttpServletResponse对象
     * @param responseBody 响应体内容
     * @throws IOException IO异常
     */
    public void handleCallbackResponse(HttpServletResponse response, Object responseBody) throws IOException {
        httpClient.handleCallbackResponse(response, responseBody);
    }

    /**
     * 执行文件上传请求 支持TypeReference
     */
    public <T> T multipartExecute(FileUploadRequest request, String path, TypeReference<T> responseType) throws FlywayApiException {
        String url = config.getFileServerUrl() + path;
        // 请求接口
        return httpClient.postFormDataWithEncryptionAndSignature(url, request, responseType,
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