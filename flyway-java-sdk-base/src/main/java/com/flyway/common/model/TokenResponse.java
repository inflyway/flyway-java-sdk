package com.flyway.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.TokenData;

/**
 * OAuth2 Token响应
 */
public class TokenResponse {
    
    /**
     * 响应代码
     */
    @JsonProperty("code")
    private Integer code;
    
    /**
     * 响应消息
     */
    @JsonProperty("message")
    private String message;
    
    /**
     * Token数据
     */
    @JsonProperty("data")
    private TokenData data;
    
    public TokenResponse() {
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public TokenData getData() {
        return data;
    }
    
    public void setData(TokenData data) {
        this.data = data;
    }
    
    /**
     * 检查响应是否成功
     */
    public boolean isSuccessful() {
        return code != null && code == 200 && data != null && data.getAccessToken() != null;
    }
    
    @Override
    public String toString() {
        return "TokenResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}