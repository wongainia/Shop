package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class GetCode implements Serializable {

    /**
     * success : true
     * msg : 验证码发送成功
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
