package com.flyway.common;

import com.flyway.common.model.TokenResponse;
import com.flyway.exception.FlywayApiException;
import com.flyway.http.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 负责token的获取、缓存和刷新
 */
public class TokenApi {
    
    private static final Logger logger = LoggerFactory.getLogger(TokenApi.class);
    
    private final FlywayConfig config;
    private final HttpClientUtil httpClient;

    public TokenApi(FlywayConfig config) {
        this.config = config;
        this.httpClient = new HttpClientUtil(config);
    }
    

    /**
     * 获取完整的Authorization头值
     */
    public String getToken() throws FlywayApiException {
        return requestNewToken().getAuthorizationHeader();
    }
    
    /**
     * 请求新的token
     */
    private TokenData requestNewToken() throws FlywayApiException {
        if (!config.hasAuthConfig()) {
            throw new FlywayApiException(500,
                    "OAuth2 authentication is not configured. Please set clientId and clientSecret.");
        }
        
        String tokenUrl = config.getServerUrl() + config.getTokenEndpoint() +
                "?client_id=" + config.getClientId() +
                "&client_secret=" + config.getClientSecret() +
                "&grant_type=client_credentials";
        
        try {
            if (config.isDebug()) {
                logger.info("Requesting token from: {}", 
                           tokenUrl.replaceAll("client_secret=[^&]*", "client_secret=***"));
            }
            
            TokenResponse response = httpClient.postFormUrlEncoded(tokenUrl, TokenResponse.class);
            
            if (response != null && response.isSuccessful()) {
                TokenData tokenData = response.getData();
                if (tokenData != null) {
                    // 设置创建时间
                    tokenData.setCreatedAt(System.currentTimeMillis());
                    return tokenData;
                }
            }
            
            String errorMsg = response != null ? response.getMessage() : "Unknown error";
            throw new FlywayApiException(500,
                    "Failed to request token: " + errorMsg);
            
        } catch (FlywayApiException e) {
            logger.error("Failed to request token", e);
            throw e;
        }
    }
}