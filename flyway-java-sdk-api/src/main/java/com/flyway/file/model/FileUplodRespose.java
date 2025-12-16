package com.flyway.file.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flyway.common.model.CommonResponse;

public class FileUplodRespose extends CommonResponse {

    /**
     * 余额数据列表
     */
    @JsonProperty("data")
    private FileUrlInfo data;

    public FileUplodRespose() {
        super();
    }

    public FileUrlInfo getData() {
        return data;
    }

    public void setData(FileUrlInfo data) {
        this.data = data;
    }
}
