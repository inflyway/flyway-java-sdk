package com.flyway.exception;

/**
 * Inflyway SDK异常基类
 */
public class FlywayException extends Exception {
    
    private Integer errorCode;
    private String errorMessage;
    
    public FlywayException(String message) {
        super(message);
        this.errorMessage = message;
    }
    
    public FlywayException(String message, Throwable cause) {
        super(message, cause);
        this.errorMessage = message;
    }
    
    public FlywayException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    public FlywayException(Integer errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    public Integer getErrorCode() {
        return errorCode;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    @Override
    public String toString() {
        return "InflywayException{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}