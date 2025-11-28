package com.flyway.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

/**
 * AES加解密工具
 * 【数据、密钥都固定UTF-8编码】
 */
public class OpenAesUtil {

    private static final String ENCODE = StandardCharsets.UTF_8.name();
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_IV_LENGTH = 12; // GCM推荐的IV长度为12字节
    private static final int GCM_TAG_LENGTH = 128; // GCM标签长度为128位
    /**
     * aes加密并进行base64编码
     *
     * @param data   字节数组数据
     * @param secret aes密钥
     */
    public static String encryptAndBase64Encode(byte[] data, String secret) {
        if (null == data || data.length == 0) {
            return null;
        }
        if (StringUtils.isBlank(secret)) {
            throw new RuntimeException("加密密钥缺失");
        }
        try {
            // 生成随机IV
            byte[] iv = new byte[GCM_IV_LENGTH];
            new SecureRandom().nextBytes(iv);

            SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(ENCODE), ALGORITHM);
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
            byte[] encryptedData = cipher.doFinal(data);

            // 将IV和加密数据组合在一起进行Base64编码
            byte[] combined = new byte[iv.length + encryptedData.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encryptedData, 0, combined, iv.length, encryptedData.length);

            return Base64.encodeBase64String(combined).replaceAll("[\r\n]", "");
        } catch (Exception e) {
            throw new RuntimeException("加密失败");
        }
    }

    /**
     * aes加密并进行base64编码
     *
     * @param data   字符串数据
     * @param secret aes密钥
     */
    public static String encryptAndBase64Encode(String data, String secret) {
        if (null == data || data.length() == 0) {
            return null;
        }
        try {
            byte[] bytes = data.getBytes(ENCODE);
            return encryptAndBase64Encode(bytes, secret);
        } catch (Exception e) {
            throw new RuntimeException("加密失败");
        }
    }

    /**
     * base64解码并进行aes解密
     *
     * @param data   加密数据
     * @param secret aes密钥
     */
    public static byte[] decryptAfterBase64DecodeToBytes(String data, String secret) {
        if (data == null || data.length() == 0) {
            return null;
        }
        if (StringUtils.isBlank(secret)) {
            throw new RuntimeException("解密密钥缺失");
        }
        try {
            // 解码Base64数据
            byte[] combined = Base64.decodeBase64(data);

            // 提取IV和加密数据
            byte[] iv = new byte[GCM_IV_LENGTH];
            byte[] encryptedData = new byte[combined.length - GCM_IV_LENGTH];
            System.arraycopy(combined, 0, iv, 0, GCM_IV_LENGTH);
            System.arraycopy(combined, GCM_IV_LENGTH, encryptedData, 0, encryptedData.length);

            SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(ENCODE), ALGORITHM);
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException("解密失败");
        }
    }

    /**
     * base64解码并进行aes解密
     *
     * @param data   加密数据
     * @param secret aes密钥
     */
    public static String decryptAfterBase64Decode(String data, String secret) {
        if (data == null || data.length() == 0) {
            return null;
        }
        if (StringUtils.isBlank(secret)) {
            throw new RuntimeException("解密密钥缺失");
        }
        try {
            byte[] bytes = decryptAfterBase64DecodeToBytes(data, secret);
            return new String(bytes, ENCODE);
        } catch (Exception e) {
            throw new RuntimeException("解密失败");
        }
    }

    /**
     * 生成aes密钥
     */
    public static String createSecret(int length) {
        if (length <= 0) {
            throw new RuntimeException("密钥长度必须大于0");
        }
        String s = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(s.charAt(r.nextInt(62)));
        }
        return sb.toString();
    }
}