package com.tgt.myretail.exceptionhandler;


public class BusinessException extends RuntimeException {

    private String errorCode;

    /**
     * @param errorCode
     */
    public BusinessException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public BusinessException() {
        super();
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
