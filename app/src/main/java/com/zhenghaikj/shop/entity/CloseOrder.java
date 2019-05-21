package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class CloseOrder implements Serializable {
    private boolean Success;
    private String ErrorMsg;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }
}
