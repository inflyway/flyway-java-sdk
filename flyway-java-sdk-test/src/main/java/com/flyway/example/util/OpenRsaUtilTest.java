package com.flyway.example.util;

import com.flyway.util.OpenRsaUtil;

import java.util.Map;

/**
 * OpenRsaUtil 测试类
 */
public class OpenRsaUtilTest {

    public static void main(String[] args) {
        try {
            // 测试 createKey 方法
            System.out.println("=== 测试 createKey 方法 ===");
            testCreateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试生成RSA公私钥对
     */
    public static void testCreateKey() throws Exception {
        // 使用默认密钥长度 1024
        Map<String, String> keyMap = OpenRsaUtil.createKey(1024);
        
        String publicKey = keyMap.get("publicKey");
        String privateKey = keyMap.get("privateKey");
        
        System.out.println("生成的公钥: " + publicKey);
        System.out.println("生成的私钥: " + privateKey);
        System.out.println("公钥长度: " + publicKey.length());
        System.out.println("私钥长度: " + privateKey.length());
        
        // 验证密钥不为空
        assert !publicKey.isEmpty() : "公钥不应为空";
        assert !privateKey.isEmpty() : "私钥不应为空";
    }
}