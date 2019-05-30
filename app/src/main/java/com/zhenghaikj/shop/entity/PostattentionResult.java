package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class PostattentionResult implements Serializable {


    /**
     * Success : true
     * Msg : 成功关注该微店.
     */

    private boolean Success;
    private String Msg;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }
}
