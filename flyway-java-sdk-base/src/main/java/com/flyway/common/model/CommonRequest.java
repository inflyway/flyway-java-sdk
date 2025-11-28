package com.flyway.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 查询FLH余额请求参数
 */
public class CommonRequest {

    /**
     * 开放平台账户ID
     */
    @JsonProperty("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}