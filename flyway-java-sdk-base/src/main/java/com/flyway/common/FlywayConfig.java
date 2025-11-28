package com.flyway.common;

/**
 * Inflyway SDK配置类
 */
public class FlywayConfig {
    
    /**
     * 服务器地址
     */
    private String serverUrl = "https://open.inflyway.com";
    
    /**
     * 连接超时时间（毫秒）
     */
    private int connectTimeout = 10000;
    
    /**
     * 读取超时时间（毫秒）
     */
    private int readTimeout = 30000;
    
    /**
     * 最大重试次数
     */
    private int maxRetries = 3;
    
    /**
     * 是否启用调试模式
     */
    private boolean debug = false;
    
    /**
     * OAuth2客户端ID
     */
    private String clientId;

    /**
     * OAuth2客户端密钥
     */
    private String clientSecret;

    /**
     * 是否启用自动认证
     */
    private boolean autoAuth = true;

    /**
     * Token缓冲时间（秒），提前多少秒认为token过期
     */
    private int tokenBufferSeconds = 300;

    /**
     * OAuth2 Token端点
     */
    private String tokenEndpoint = "/oauth2/oauth/token";

    /**
     * 是否启用签名
     */
    private boolean enableSignature;
    /**
     * 签名算法
     */
    private String signatureAlgorithm;
    /**
     * 签名密钥
     */
    private String signatureSecret;
    /**
     * 签名请求头名称
     */
    private String signatureHeader;

    /**
     * 是否包含时间戳
     */
    private boolean includeTimestamp;

    /**
     * 时间戳请求头名称
     */
    private String timestampHeader;

    /**
     * 是否包含随机数
     */
    private boolean includeNonce;

    /**
     * 随机数请求头名称
     */
    private String nonceHeader = "X-Nonce";

    /**
     * AES加密密钥
     */
    private String aesKey;
    /**
     * RSA私钥（用于签名）
     */
    private String rsaPrivateKey;
    /**
     * 是否启用加密和签名功能
     */
    private boolean enableEncryptionAndSignature = false;
    public FlywayConfig() {
    }

    public FlywayConfig(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public FlywayConfig(String serverUrl, String clientId, String clientSecret) {
        this.serverUrl = serverUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public boolean isAutoAuth() {
        return autoAuth;
    }

    public void setAutoAuth(boolean autoAuth) {
        this.autoAuth = autoAuth;
    }

    public int getTokenBufferSeconds() {
        return tokenBufferSeconds;
    }

    public void setTokenBufferSeconds(int tokenBufferSeconds) {
        this.tokenBufferSeconds = tokenBufferSeconds;
    }

    public String getTokenEndpoint() {
        return tokenEndpoint;
    }

    public void setTokenEndpoint(String tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    public boolean isEnableSignature() {
        return enableSignature;
    }

    public void setEnableSignature(boolean enableSignature) {
        this.enableSignature = enableSignature;
    }

    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public String getSignatureSecret() {
        return signatureSecret;
    }

    public void setSignatureSecret(String signatureSecret) {
        this.signatureSecret = signatureSecret;
    }

    public String getSignatureHeader() {
        return signatureHeader;
    }

    public void setSignatureHeader(String signatureHeader) {
        this.signatureHeader = signatureHeader;
    }

    public boolean isIncludeTimestamp() {
        return includeTimestamp;
    }

    public void setIncludeTimestamp(boolean includeTimestamp) {
        this.includeTimestamp = includeTimestamp;
    }

    public String getTimestampHeader() {
        return timestampHeader;
    }

    public void setTimestampHeader(String timestampHeader) {
        this.timestampHeader = timestampHeader;
    }

    public boolean isIncludeNonce() {
        return includeNonce;
    }

    public void setIncludeNonce(boolean includeNonce) {
        this.includeNonce = includeNonce;
    }

    public String getNonceHeader() {
        return nonceHeader;
    }

    public void setNonceHeader(String nonceHeader) {
        this.nonceHeader = nonceHeader;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public boolean isEnableEncryptionAndSignature() {
        return enableEncryptionAndSignature;
    }

    public void setEnableEncryptionAndSignature(boolean enableEncryptionAndSignature) {
        this.enableEncryptionAndSignature = enableEncryptionAndSignature;
    }

    /**
     * 检查是否配置了OAuth2认证信息
     */
    public boolean hasAuthConfig() {
        return clientId != null && !clientId.trim().isEmpty() &&
               clientSecret != null && !clientSecret.trim().isEmpty();
    }

    /**
     * 检查是否配置了加密和签名信息
     */
    public boolean hasEncryptionAndSignatureConfig() {
        return enableEncryptionAndSignature &&
               aesKey != null && !aesKey.trim().isEmpty() &&
               rsaPrivateKey != null && !rsaPrivateKey.trim().isEmpty();
    }

    /**
     * 检查是否配置了签名信息
     */
    public boolean hasSignatureConfig() {
        return enableSignature &&
               signatureSecret != null && !signatureSecret.trim().isEmpty() &&
               signatureAlgorithm != null && !signatureAlgorithm.trim().isEmpty();
    }

    /**
     * 从默认配置文件创建配置
     */
    public static FlywayConfig fromProperties() {
        return fromProperties(PropertiesLoader.DEFAULT_CONFIG_FILE);
    }

    /**
     * 从指定配置文件创建配置
     */
    public static FlywayConfig fromProperties(String configFileName) {
        java.util.Properties properties = PropertiesLoader.loadProperties(configFileName);
        properties = PropertiesLoader.mergeSystemProperties(properties);
        return fromProperties(properties);
    }

    /**
     * 从Properties对象创建配置
     */
    public static FlywayConfig fromProperties(java.util.Properties properties) {
        FlywayConfig config = new FlywayConfig();

        // 服务器配置
        config.setServerUrl(PropertiesLoader.getString(properties,
                "inflyway.server.url", config.getServerUrl()));

        // OAuth2配置
        String clientId = PropertiesLoader.getString(properties,
                "inflyway.oauth2.client.id", null);
        String clientSecret = PropertiesLoader.getString(properties,
                "inflyway.oauth2.client.secret", null);

        // 检查是否为占位符
        if (clientId != null && !PropertiesLoader.isPlaceholder(clientId)) {
            config.setClientId(clientId);
        }
        if (clientSecret != null && !PropertiesLoader.isPlaceholder(clientSecret)) {
            config.setClientSecret(clientSecret);
        }

        config.setAutoAuth(PropertiesLoader.getBoolean(properties,
                "inflyway.oauth2.auto.auth", config.isAutoAuth()));
        config.setTokenBufferSeconds(PropertiesLoader.getInt(properties,
                "inflyway.oauth2.token.buffer.seconds", config.getTokenBufferSeconds()));
        config.setTokenEndpoint(PropertiesLoader.getString(properties,
                "inflyway.oauth2.token.endpoint", config.getTokenEndpoint()));

        // 连接配置
        config.setConnectTimeout(PropertiesLoader.getInt(properties,
                "inflyway.timeout.connect", config.getConnectTimeout()));
        config.setReadTimeout(PropertiesLoader.getInt(properties,
                "inflyway.timeout.read", config.getReadTimeout()));
        config.setMaxRetries(PropertiesLoader.getInt(properties,
                "inflyway.retry.max", config.getMaxRetries()));

        // 调试配置
        config.setDebug(PropertiesLoader.getBoolean(properties,
                "inflyway.debug.enabled", config.isDebug()));

        // 签名配置
        config.setEnableSignature(PropertiesLoader.getBoolean(properties,
                "inflyway.signature.enabled", config.isEnableSignature()));

        String signatureSecret = PropertiesLoader.getString(properties,
                "inflyway.signature.secret", null);
        if (signatureSecret != null && !PropertiesLoader.isPlaceholder(signatureSecret)) {
            config.setSignatureSecret(signatureSecret);
        }

        config.setSignatureAlgorithm(PropertiesLoader.getString(properties,
                "inflyway.signature.algorithm", config.getSignatureAlgorithm()));
        config.setSignatureHeader(PropertiesLoader.getString(properties,
                "inflyway.signature.header", config.getSignatureHeader()));

        config.setIncludeTimestamp(PropertiesLoader.getBoolean(properties,
                "inflyway.signature.include.timestamp", config.isIncludeTimestamp()));
        config.setTimestampHeader(PropertiesLoader.getString(properties,
                "inflyway.signature.timestamp.header", config.getTimestampHeader()));

        config.setIncludeNonce(PropertiesLoader.getBoolean(properties,
                "inflyway.signature.include.nonce", config.isIncludeNonce()));
        config.setNonceHeader(PropertiesLoader.getString(properties,
                "inflyway.signature.nonce.header", config.getNonceHeader()));

        // 加密和签名配置
        config.setEnableEncryptionAndSignature(PropertiesLoader.getBoolean(properties,
                "inflyway.encryption.enabled", config.isEnableEncryptionAndSignature()));
        String aesKey = PropertiesLoader.getString(properties,
                "inflyway.encryption.aes.key", null);
        if (aesKey != null && !PropertiesLoader.isPlaceholder(aesKey)) {
            config.setAesKey(aesKey);
        }

        String rsaPrivateKey = PropertiesLoader.getString(properties,
                "inflyway.encryption.rsa.private.key", null);
        if (rsaPrivateKey != null && !PropertiesLoader.isPlaceholder(rsaPrivateKey)) {
            config.setRsaPrivateKey(rsaPrivateKey);
        }

        return config;
    }

    @Override
    public String toString() {
        return "FlywayConfig{" +
                "serverUrl='" + serverUrl + '\'' +
                ", connectTimeout=" + connectTimeout +
                ", readTimeout=" + readTimeout +
                ", maxRetries=" + maxRetries +
                ", debug=" + debug +
                ", clientId='" + clientId + '\'' +
                ", clientSecret='***'" +  // 隐藏敏感信息
                ", autoAuth=" + autoAuth +
                ", tokenBufferSeconds=" + tokenBufferSeconds +
                ", tokenEndpoint='" + tokenEndpoint + '\'' +
                ", enableSignature=" + enableSignature +
                ", signatureAlgorithm='" + signatureAlgorithm + '\'' +
                ", signatureSecret='***'" +  // 隐藏敏感信息
                ", signatureHeader='" + signatureHeader + '\'' +
                ", includeTimestamp=" + includeTimestamp +
                ", timestampHeader='" + timestampHeader + '\'' +
                ", includeNonce=" + includeNonce +
                ", nonceHeader='" + nonceHeader + '\'' +
                ", enableEncryptionAndSignature=" + enableEncryptionAndSignature +
                ", aesKey='***'" +  // 隐藏敏感信息
                ", rsaPrivateKey='***'" +  // 隐藏敏感信息
                '}';
    }
}