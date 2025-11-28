package com.flyway.util;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA加签验签工具类
 * 【数据、公私钥都固定UTF-8编码】
 */
public class OpenRsaUtil {

    private static final String ENCODE = StandardCharsets.UTF_8.name();

    /**
     * RSA签名
     *
     * @param data   待签名数据
     * @param priKey RSA私钥
     */
    public static String rsaSign(String data, String priKey) {
        if (StringUtils.isBlank(priKey)) {
            throw new RuntimeException("签名私钥缺失");
        }
        if (data == null || data.length() == 0) {
            throw new RuntimeException("待签名信息为空");
        }
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(priKey));
            KeyFactory fac = KeyFactory.getInstance("RSA");
            RSAPrivateKey privateKey = (RSAPrivateKey) fac.generatePrivate(keySpec);
            Signature sigEng = Signature.getInstance("SHA256WithRSA");
            sigEng.initSign(privateKey);
            sigEng.update(data.getBytes(ENCODE));
            byte[] signature = sigEng.sign();
            return Base64.encodeBase64String(signature).replaceAll("[\r\n]", "");
        } catch (Exception e) {
            throw new RuntimeException("签名失败");
        }
    }

    /**
     * RSA验签
     *
     * @param data   待签名数据
     * @param pubKey RSA公钥
     * @param sign   待验签名串
     */
    public static void rsaVerify(String data, String pubKey, String sign) {
        if (StringUtils.isBlank(pubKey)) {
            throw new RuntimeException("验签公钥缺失");
        }
        if (data == null || data.length() == 0) {
            throw new RuntimeException("待签名信息为空");
        }
        if (StringUtils.isBlank(sign)) {
            throw new RuntimeException("签名为空");
        }
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(pubKey));
            KeyFactory fac = KeyFactory.getInstance("RSA");
            RSAPublicKey rsaPubKey = (RSAPublicKey) fac.generatePublic(keySpec);
            Signature sigEng = Signature.getInstance("SHA256WithRSA");
            sigEng.initVerify(rsaPubKey);
            sigEng.update(data.getBytes(ENCODE));
            byte[] signature = Base64.decodeBase64(sign);
            if (!sigEng.verify(signature)) {
                throw new RuntimeException("验签失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("验签失败");
        }
    }

    /**
     * 生成RSA公私钥
     *
     * @return Map对象中存放公私钥Base64编码
     */
    public static Map<String, String> createKey(int keySize) throws Exception {
        if (keySize <= 0) {
            throw new RuntimeException("密钥长度必须大于0");
        }
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(keySize);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        //公私钥对象存入map中
        Map<String, String> keyMap = new HashMap<>(2);
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKey = Base64.encodeBase64String(rsaPublicKey.getEncoded()).replaceAll("[\r\n]", "");
        keyMap.put("publicKey", publicKey);
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        String privateKey = Base64.encodeBase64String(rsaPrivateKey.getEncoded()).replaceAll("[\r\n]", "");
        keyMap.put("privateKey", privateKey);
        return keyMap;
    }
}
