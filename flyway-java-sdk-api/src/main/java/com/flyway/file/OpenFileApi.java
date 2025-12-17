package com.flyway.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flyway.common.FlywayConfig;
import com.flyway.common.FlywayUrlConstants;
import com.flyway.common.model.CommonResponse;
import com.flyway.common.model.FileUploadRequest;
import com.flyway.exception.FlywayApiException;
import com.flyway.file.model.FileUrlInfo;
import com.flyway.http.AbstractApi;
import com.flyway.http.HttpClientUtil;

/**
 * 文件上传相关接口
 */
public class OpenFileApi extends AbstractApi {

    private String fileUploadUrl;

    /**
     * 使用指定配置构造客户端
     *
     * @param config
     */
    public OpenFileApi(FlywayConfig config) {
        super(config);
        this.config = config;
        this.httpClient = new HttpClientUtil(config);
        this.fileUploadUrl = FlywayUrlConstants.FILE_UPLOAD_PRIVATE;
    }

    /**
     * 上传文件
     * @param request 文件上传请求参数
     * @return 文件上传结果
     * @throws FlywayApiException API调用异常
     */
    public CommonResponse<FileUrlInfo> uploadFile(FileUploadRequest request) throws FlywayApiException {
        return multipartExecute(request, this.fileUploadUrl, new TypeReference<CommonResponse<FileUrlInfo>>() {
        });
    }


}