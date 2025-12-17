package com.flyway.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Token数据
 */
public class TokenData {
    
    /**
     * 访问令牌
     */
    @JsonProperty("access_token")
    private String accessToken;
    
    /**
     * 令牌类型
     */
    @JsonProperty("token_type")
    private String tokenType;
    
    /**
     * 过期时间（秒）
     */
    @JsonProperty("expires_in")
    private Integer expiresIn;
    
    /**
     * 作用域
     */
    @JsonProperty("scope")
    private String scope;
    
    /**
     * 授权类型
     */
    @JsonProperty("grant_type")
    private String grantType;
    
    /**
     * JWT ID
     */
    @JsonProperty("jti")
    private String jti;
    
    /**
     * token创建时间戳（毫秒）
     */
    private long createdAt;
    
    public TokenData() {
        this.createdAt = System.currentTimeMillis();
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public String getTokenType() {
        return tokenType;
    }
    
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    
    public Integer getExpiresIn() {
        return expiresIn;
    }
    
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
    
    public String getScope() {
        return scope;
    }
    
    public void setScope(String scope) {
        this.scope = scope;
    }
    
    public String getGrantType() {
        return grantType;
    }
    
    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
    
    public String getJti() {
        return jti;
    }
    
    public void setJti(String jti) {
        this.jti = jti;
    }
    
    public long getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
    
    /**
     * 检查token是否已过期
     * @param bufferSeconds 缓冲时间（秒），提前多少秒认为过期
     * @return true表示已过期
     */
    public boolean isExpired(int bufferSeconds) {
        if (expiresIn == null) {
            return true;
        }
        
        long currentTime = System.currentTimeMillis();
        long expireTime = createdAt + (expiresIn - bufferSeconds) * 1000L;
        
        return currentTime >= expireTime;
    }
    
    /**
     * 检查token是否已过期（默认缓冲时间300秒）
     */
    public boolean isExpired() {
        return isExpired(300); // 默认提前5分钟过期
    }
    
    /**
     * 获取完整的Authorization头值
     */
    public String getAuthorizationHeader() {
        if (accessToken == null || tokenType == null) {
            return null;
        }
        return "Bearer " + accessToken;
    }
    
    @Override
    public String toString() {
        return "TokenData{" +
                "accessToken='" + (accessToken != null ? accessToken.substring(0, Math.min(20, accessToken.length())) + "..." : null) + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                ", scope='" + scope + '\'' +
                ", grantType='" + grantType + '\'' +
                ", jti='" + jti + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}