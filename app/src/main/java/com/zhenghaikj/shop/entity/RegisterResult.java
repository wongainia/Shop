package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class RegisterResult implements Serializable {
    /*
    * "Success": "true",
    * "UserKey": "dsdfgsfd",
    * "UserId": 110
    * */

    private boolean Success;
    private String UserKey;
    private String UserId;
    private String msg;
    private String ErrorMsg;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getUserKey() {
        return UserKey;
    }

    public void setUserKey(String userKey) {
        UserKey = userKey;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }
}
