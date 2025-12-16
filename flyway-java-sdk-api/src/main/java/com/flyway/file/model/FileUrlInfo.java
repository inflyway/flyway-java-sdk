package com.flyway.file.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 文件上传响应数据
 */
public class FileUrlInfo  {
    
    /**
     * 文件URL
     */
    @JsonProperty("url")
    private String url;

    public FileUrlInfo() {
    }

    public FileUrlInfo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}