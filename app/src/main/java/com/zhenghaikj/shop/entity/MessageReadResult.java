package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class MessageReadResult implements Serializable {

    /**
     * success : true
     * msg : null
     */

    private boolean success;
    private String msg;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
