package com.flyway.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Properties配置文件加载工具
 */
public class PropertiesLoader {
    
    private static final Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);
    
    /**
     * 默认配置文件路径
     */
    public static final String DEFAULT_CONFIG_FILE = "inflyway-sdk.properties";
    
    /**
     * 备用配置文件路径
     */
    public static final String FALLBACK_CONFIG_FILE = "application.properties";
    
    /**
     * 从默认配置文件加载Properties
     */
    public static Properties loadDefaultProperties() {
        return loadProperties(DEFAULT_CONFIG_FILE);
    }
    
    /**
     * 从指定配置文件加载Properties
     * 
     * @param configFileName 配置文件名
     * @return Properties对象，如果加载失败返回空的Properties
     */
    public static Properties loadProperties(String configFileName) {
        Properties properties = new Properties();
        
        try {
            // 首先尝试从classpath加载
            InputStream inputStream = PropertiesLoader.class.getClassLoader()
                    .getResourceAsStream(configFileName);
            
            if (inputStream != null) {
                properties.load(inputStream);
                logger.info("Successfully loaded configuration from classpath: {}", configFileName);
                inputStream.close();
                return properties;
            }
            
            // 如果是默认文件且未找到，尝试加载备用文件
            if (DEFAULT_CONFIG_FILE.equals(configFileName)) {
                logger.warn("Default config file {} not found, trying fallback: {}", 
                           configFileName, FALLBACK_CONFIG_FILE);
                return loadProperties(FALLBACK_CONFIG_FILE);
            }
            
            logger.warn("Configuration file not found: {}", configFileName);
            
        } catch (IOException e) {
            logger.error("Error loading configuration file: {}", configFileName, e);
        }
        
        return properties;
    }
    
    /**
     * 从系统属性和环境变量中加载配置，优先级高于配置文件
     * 
     * @param properties 基础Properties对象
     * @return 合并后的Properties对象
     */
    public static Properties mergeSystemProperties(Properties properties) {
        Properties mergedProperties = new Properties(properties);
        
        // 从系统属性中覆盖配置
        for (String key : properties.stringPropertyNames()) {
            String systemValue = System.getProperty(key);
            if (systemValue != null) {
                mergedProperties.setProperty(key, systemValue);
                logger.debug("Override property from system: {} = {}", key, systemValue);
            }
        }
        
        // 从环境变量中覆盖配置（将点号替换为下划线，转为大写）
        for (String key : properties.stringPropertyNames()) {
            String envKey = key.replace('.', '_').toUpperCase();
            String envValue = System.getenv(envKey);
            if (envValue != null) {
                mergedProperties.setProperty(key, envValue);
                logger.debug("Override property from environment: {} = {}", key, envValue);
            }
        }
        
        return mergedProperties;
    }
    
    /**
     * 获取字符串属性值
     */
    public static String getString(Properties properties, String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * 获取整数属性值
     */
    public static int getInt(Properties properties, String key, int defaultValue) {
        String value = properties.getProperty(key);
        if (value != null) {
            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException e) {
                logger.warn("Invalid integer value for property {}: {}, using default: {}", 
                           key, value, defaultValue);
            }
        }
        return defaultValue;
    }
    
    /**
     * 获取布尔属性值
     */
    public static boolean getBoolean(Properties properties, String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        if (value != null) {
            return Boolean.parseBoolean(value.trim());
        }
        return defaultValue;
    }
    
    /**
     * 验证必需的属性是否存在
     */
    public static void validateRequiredProperties(Properties properties, String... requiredKeys) {
        for (String key : requiredKeys) {
            String value = properties.getProperty(key);
            if (value == null || value.trim().isEmpty()) {
                throw new IllegalArgumentException("Required property is missing or empty: " + key);
            }
        }
    }
    
    /**
     * 检查属性值是否为占位符（未配置的默认值）
     */
    public static boolean isPlaceholder(String value) {
        return value != null && (
                value.startsWith("your_") ||
                value.equals("${") ||
                value.contains("_here") ||
                value.equals("TODO") ||
                value.equals("CHANGE_ME")
        );
    }
}