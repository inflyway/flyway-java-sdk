package com.flyway.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 查询FLH余额响应结果
 */


public class CommonResponseData {


    /**
     * 请求是否成功
     */
    @JsonProperty("ciphertext")
    private String ciphertext;

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }
}