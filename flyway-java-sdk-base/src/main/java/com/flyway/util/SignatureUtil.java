package com.flyway.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 签名工具类 - 用于对请求体进行签名加密
 */
public class SignatureUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(SignatureUtil.class);
    
    // 签名算法常量
    public static final String HMAC_SHA256 = "HmacSHA256";
    public static final String HMAC_SHA1 = "HmacSHA1";
    public static final String MD5 = "MD5";
    public static final String SHA256 = "SHA-256";
    
    /**
     * 使用HMAC-SHA256算法对数据进行签名
     * 
     * @param data 要签名的数据
     * @param secret 密钥
     * @return Base64编码的签名结果
     */
    public static String signWithHmacSha256(String data, String secret) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
            mac.init(secretKeySpec);
            
            byte[] signedBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(signedBytes);
            
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            logger.error("Failed to sign data with HMAC-SHA256", e);
            throw new RuntimeException("Failed to sign data", e);
        }
    }
    
    /**
     * 使用HMAC-SHA256算法对数据进行签名，返回十六进制字符串
     * 
     * @param data 要签名的数据
     * @param secret 密钥
     * @return 十六进制格式的签名结果
     */
    public static String signWithHmacSha256Hex(String data, String secret) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
            mac.init(secretKeySpec);
            
            byte[] signedBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(signedBytes);
            
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            logger.error("Failed to sign data with HMAC-SHA256", e);
            throw new RuntimeException("Failed to sign data", e);
        }
    }
    
    /**
     * 使用HMAC-SHA1算法对数据进行签名
     * 
     * @param data 要签名的数据
     * @param secret 密钥
     * @return Base64编码的签名结果
     */
    public static String signWithHmacSha1(String data, String secret) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA1);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_SHA1);
            mac.init(secretKeySpec);
            
            byte[] signedBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(signedBytes);
            
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            logger.error("Failed to sign data with HMAC-SHA1", e);
            throw new RuntimeException("Failed to sign data", e);
        }
    }
    
    /**
     * 使用MD5算法对数据进行哈希
     * 
     * @param data 要哈希的数据
     * @return 十六进制格式的哈希结果
     */
    public static String md5Hash(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            byte[] hashBytes = md.digest(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashBytes);
            
        } catch (NoSuchAlgorithmException e) {
            logger.error("Failed to hash data with MD5", e);
            throw new RuntimeException("Failed to hash data", e);
        }
    }
    
    /**
     * 使用SHA256算法对数据进行哈希
     * 
     * @param data 要哈希的数据
     * @return 十六进制格式的哈希结果
     */
    public static String sha256Hash(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance(SHA256);
            byte[] hashBytes = md.digest(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashBytes);
            
        } catch (NoSuchAlgorithmException e) {
            logger.error("Failed to hash data with SHA256", e);
            throw new RuntimeException("Failed to hash data", e);
        }
    }
    
    /**
     * 计算请求体的签名
     * 
     * @param requestBody JSON格式的请求体字符串
     * @param secret 签名密钥
     * @param algorithm 签名算法 (HMAC_SHA256, HMAC_SHA1, MD5, SHA256)
     * @return 签名结果
     */
    public static String signRequestBody(String requestBody, String secret, String algorithm) {
        if (requestBody == null || requestBody.trim().isEmpty()) {
            logger.warn("Request body is empty, returning empty signature");
            return "";
        }
        
        if (secret == null || secret.trim().isEmpty()) {
            logger.warn("Secret key is empty for algorithm: {}", algorithm);
            throw new IllegalArgumentException("Secret key cannot be empty for HMAC algorithms");
        }
        
        logger.debug("Signing request body with algorithm: {}", algorithm);
        logger.debug("Request body length: {}", requestBody.length());
        
        switch (algorithm) {
            case HMAC_SHA256:
                return signWithHmacSha256(requestBody, secret);
            case HMAC_SHA1:
                return signWithHmacSha1(requestBody, secret);
            case MD5:
                return md5Hash(requestBody);
            case SHA256:
                return sha256Hash(requestBody);
            default:
                logger.error("Unsupported signature algorithm: {}", algorithm);
                throw new IllegalArgumentException("Unsupported signature algorithm: " + algorithm);
        }
    }
    
    /**
     * 计算请求体的签名（默认使用HMAC-SHA256）
     * 
     * @param requestBody JSON格式的请求体字符串
     * @param secret 签名密钥
     * @return Base64编码的签名结果
     */
    public static String signRequestBody(String requestBody, String secret) {
        return signRequestBody(requestBody, secret, HMAC_SHA256);
    }
    
    /**
     * 验证签名是否正确
     * 
     * @param requestBody 原始请求体
     * @param secret 密钥
     * @param expectedSignature 期望的签名值
     * @param algorithm 签名算法
     * @return 是否验证通过
     */
    public static boolean verifySignature(String requestBody, String secret, String expectedSignature, String algorithm) {
        try {
            String actualSignature = signRequestBody(requestBody, secret, algorithm);
            return actualSignature.equals(expectedSignature);
        } catch (Exception e) {
            logger.error("Failed to verify signature", e);
            return false;
        }
    }
    
    /**
     * 验证签名是否正确（默认使用HMAC-SHA256）
     * 
     * @param requestBody 原始请求体
     * @param secret 密钥
     * @param expectedSignature 期望的签名值
     * @return 是否验证通过
     */
    public static boolean verifySignature(String requestBody, String secret, String expectedSignature) {
        return verifySignature(requestBody, secret, expectedSignature, HMAC_SHA256);
    }
    
    /**
     * 将字节数组转换为十六进制字符串
     * 
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
    
    /**
     * 生成带时间戳的签名
     * 
     * @param requestBody 请求体
     * @param secret 密钥
     * @param timestamp 时间戳
     * @param algorithm 签名算法
     * @return 签名结果
     */
    public static String signWithTimestamp(String requestBody, String secret, long timestamp, String algorithm) {
        String data = requestBody + timestamp;
        return signRequestBody(data, secret, algorithm);
    }
    
    /**
     * 生成带随机数的签名
     * 
     * @param requestBody 请求体
     * @param secret 密钥
     * @param nonce 随机数
     * @param algorithm 签名算法
     * @return 签名结果
     */
    public static String signWithNonce(String requestBody, String secret, String nonce, String algorithm) {
        String data = requestBody + nonce;
        return signRequestBody(data, secret, algorithm);
    }
    
    /**
     * 生成带时间戳和随机数的签名
     * 
     * @param requestBody 请求体
     * @param secret 密钥
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param algorithm 签名算法
     * @return 签名结果
     */
    public static String signWithTimestampAndNonce(String requestBody, String secret, long timestamp, String nonce, String algorithm) {
        String data = requestBody + timestamp + nonce;
        return signRequestBody(data, secret, algorithm);
    }
}