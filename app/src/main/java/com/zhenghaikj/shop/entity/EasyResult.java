package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class EasyResult implements Serializable {

    /**
     * Success : true
     * ErrorCode : null
     * ErrorMsg : null
     */

    private boolean Success;
    private String ErrorCode;
    private String ErrorMsg;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        this.Success = success;
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
