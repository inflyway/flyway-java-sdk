package com.flyway.common.model;


import java.io.File;

/**
 * 文件上传请求参数
 */
public class FileUploadRequest extends CommonRequest {
    
    /**
     * 文件内容
     */
    private File file;
    
    /**
     * 请求号
     */
    private String requestNo;
    
    /**
     * 开放ID
     */
    private String openID;

    public FileUploadRequest() {
    }

    public FileUploadRequest(File file, String requestNo, String openID) {
        this.file = file;
        this.requestNo = requestNo;
        this.openID = openID;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }
}