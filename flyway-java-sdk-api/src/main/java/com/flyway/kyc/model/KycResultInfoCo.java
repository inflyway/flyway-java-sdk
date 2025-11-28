package com.flyway.kyc.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: paulFang
 * @date: 2022-05-31 15:04
 * @description: com.kamelnet.open.business.modules.certify.response
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
public class KycResultInfoCo  {

   
    /**
     * 实体编码   驼驼为渠道生成的企业或个人编码(根据请求实体类型)，作为该渠道使用驼驼实体认证及相关操作的唯一实体编号；如果没有数据则返回空
     */
    private String openID;

    /**
     * 审核结果状态
     */
    private String auditStatus;

    

    /**
     * 审核说明
     */
    private String auditInstructions;
    
    /**
     * 请求号
     */
    private String requsestNo;

    
}
