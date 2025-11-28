package com.flyway.http;

import com.flyway.common.FlywayConfig;
import com.flyway.common.TokenApi;
import com.flyway.common.model.CommonRequest;
import com.flyway.exception.FlywayApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

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

    public <T> T execute(CommonRequest request, String path, Class<T> responseType) throws FlywayApiException {
        String url = config.getServerUrl() + path;
        // 请求接口
        return  httpClient.postJsonWithEncryptionAndSignature(url, request, responseType,
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
