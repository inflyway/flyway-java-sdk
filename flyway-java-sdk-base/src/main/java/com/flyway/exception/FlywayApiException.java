package com.flyway.exception;

/**
 * Inflyway API异常
 */
public class FlywayApiException extends FlywayException {
    
    private int httpStatus;
    
    public FlywayApiException(String message) {
        super(message);
    }
    
    public FlywayApiException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public FlywayApiException(Integer errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
    
    public FlywayApiException(Integer errorCode, String errorMessage, Throwable cause) {
        super(errorCode, errorMessage, cause);
    }
    
    public FlywayApiException(int httpStatus, Integer errorCode, String errorMessage) {
        super(errorCode, errorMessage);
        this.httpStatus = httpStatus;
    }
    
    public int getHttpStatus() {
        return httpStatus;
    }
    
    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
    
}