package com.flyway.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * JSON工具类
 * 基于Jackson库实现JSON序列化和反序列化功能
 * 
 * @author flyway
 * @version 1.0
 */
public class JsonUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    static {
        // 配置ObjectMapper
        // 忽略未知字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 忽略空字段
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 设置日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 配置空值处理
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    }
    
    /**
     * 对象转JSON字符串
     * 
     * @param object 待转换的对象
     * @return JSON字符串，转换失败返回null
     */
    public static String toJsonString(Object object) {
        if (object == null) {
            return null;
        }
        
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("对象转JSON字符串失败: {}", e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * 对象转格式化的JSON字符串（美化输出）
     * 
     * @param object 待转换的对象
     * @return 格式化的JSON字符串，转换失败返回null
     */
    public static String toPrettyJsonString(Object object) {
        if (object == null) {
            return null;
        }
        
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("对象转格式化JSON字符串失败: {}", e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * JSON字符串转对象
     * 
     * @param jsonString JSON字符串
     * @param clazz 目标对象类型
     * @param <T> 泛型类型
     * @return 转换后的对象，转换失败返回null
     */
    public static <T> T parseObject(String jsonString, Class<T> clazz) {
        if (StringUtils.isBlank(jsonString) || clazz == null) {
            return null;
        }
        
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            logger.error("JSON字符串转对象失败: {}, JSON: {}", e.getMessage(), jsonString, e);
            return null;
        }
    }
    
    /**
     * JSON字符串转对象（支持复杂泛型）
     * 
     * @param jsonString JSON字符串
     * @param typeReference 类型引用
     * @param <T> 泛型类型
     * @return 转换后的对象，转换失败返回null
     */
    public static <T> T parseObject(String jsonString, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(jsonString) || typeReference == null) {
            return null;
        }
        
        try {
            return objectMapper.readValue(jsonString, typeReference);
        } catch (IOException e) {
            logger.error("JSON字符串转复杂对象失败: {}, JSON: {}", e.getMessage(), jsonString, e);
            return null;
        }
    }
    
    /**
     * JSON字符串转List集合
     * 
     * @param jsonString JSON字符串
     * @param clazz List元素类型
     * @param <T> 泛型类型
     * @return List集合，转换失败返回null
     */
    public static <T> List<T> parseArray(String jsonString, Class<T> clazz) {
        if (StringUtils.isBlank(jsonString) || clazz == null) {
            return null;
        }
        
        try {
            return objectMapper.readValue(jsonString, 
                objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            logger.error("JSON字符串转List失败: {}, JSON: {}", e.getMessage(), jsonString, e);
            return null;
        }
    }
    
    /**
     * JSON字符串转Map
     * 
     * @param jsonString JSON字符串
     * @return Map对象，转换失败返回null
     */
    public static Map<String, Object> parseMap(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        
        try {
            return objectMapper.readValue(jsonString, 
                new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            logger.error("JSON字符串转Map失败: {}, JSON: {}", e.getMessage(), jsonString, e);
            return null;
        }
    }
    
    /**
     * 判断字符串是否为有效的JSON格式
     * 
     * @param jsonString 待检验的字符串
     * @return true表示有效JSON，false表示无效
     */
    public static boolean isValidJson(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return false;
        }
        
        try {
            objectMapper.readTree(jsonString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * 对象深度克隆（通过JSON序列化/反序列化实现）
     * 
     * @param object 待克隆的对象
     * @param clazz 对象类型
     * @param <T> 泛型类型
     * @return 克隆后的对象，克隆失败返回null
     */
    public static <T> T deepCopy(T object, Class<T> clazz) {
        if (object == null || clazz == null) {
            return null;
        }
        
        try {
            String json = objectMapper.writeValueAsString(object);
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error("对象深度克隆失败: {}", e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * 获取ObjectMapper实例（供高级用法使用）
     * 
     * @return ObjectMapper实例
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}