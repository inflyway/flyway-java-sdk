package com.flyway.handle;

import com.flyway.common.FlywayConfig;
import com.flyway.http.AbstractApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 回调处理API
 */
public class OpenCallBackHandler extends AbstractApi {
    
    private static final Logger logger = LoggerFactory.getLogger(OpenCallBackHandler.class);

    public OpenCallBackHandler(FlywayConfig config) {
        super(config);
        this.config = config;
    }
    
    /**
     * 处理回调请求，包括验签和解密
     * 
     * @param request HttpServletRequest对象
     * @return 解密后的JSON报文，处理失败返回null
     */
    public String handleCallbackRequest(HttpServletRequest request) {
        return super.handleCallbackRequest(request);
    }
}