package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class PostOrderComplaint implements Serializable {
    private Boolean success;
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
