package com.flyway.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: paulFang
 * @date: 2025/12/22
 * @description: com.flyway.common.model
 * @version: 1.0
 */
public class CipherRequest {

    /**
     * 请求是否成功
     */
    @JsonProperty("ciphertext")
    private String ciphertext;

    public String getCiphertext() {
        return ciphertext;
    }
}
