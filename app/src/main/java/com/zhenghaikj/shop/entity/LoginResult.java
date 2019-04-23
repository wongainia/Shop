package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class LoginResult implements Serializable {

    /**
     * Success : true
     * ErrorCode : null
     * ErrorMsg : null
     * UserId : 110
     * UserKey : safsdasd
     */

    private boolean Success;
    private String ErrorCode;
    private String ErrorMsg;
    private String UserId;
    private String UserKey;
    private String IsPromoter;

    public String getIsPromoter() {
        return IsPromoter;
    }

    public void setIsPromoter(String isPromoter) {
        IsPromoter = isPromoter;
    }

    public boolean getSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
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

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getUserKey() {
        return UserKey;
    }

    public void setUserKey(String UserKey) {
        this.UserKey = UserKey;
    }
}
