package com.flyway.account.model;

import com.flyway.common.model.CommonResponse;

/**
 * VA进件开户响应结果
 */
public class VaAccountOpenResponse extends CommonResponse {

    public VaAccountOpenResponse() {
        super();
    }

    @Override
    public String toString() {
        return "VaAccountOpenResponse{" +
                "code='" + getCode() + '\'' +
                ", message=" + getMessage() +
                '}';
    }
}