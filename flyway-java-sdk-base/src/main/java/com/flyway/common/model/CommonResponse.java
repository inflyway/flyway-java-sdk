package com.flyway.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 查询FLH余额响应结果
 */
public class CommonResponse {


    /**
     * 响应代码
     */
    @JsonProperty("code")
    private Integer code;

    /**
     * 响应描述
     */
    @JsonProperty("message")
    private String message;

    public CommonResponse() {
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



}