package com.flyway.kyc.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: paulFang
 * @date: 2022-05-31 14:57
 * @description: com.kamelnet.open.business.modules.certify.response
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
public class KycCo { 
    /**
     * 受理状态
     */
    private String statusCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 请求号
     */
    private String requestNo;
    
  
}
