package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class EasyResult implements Serializable {

    /**
     * Success : true
     * ErrorCode : null
     * ErrorMsg : null
     */

    private boolean success;
    private String ErrorCode;
    private String ErrorMsg;


    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        success = success;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

}
