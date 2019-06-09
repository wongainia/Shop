package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class ConfirmOrderOverResult implements Serializable {

    /**
     * success : true
     * status : 1
     * msg : 订单完成
     */

    private boolean success;
    private int status;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
