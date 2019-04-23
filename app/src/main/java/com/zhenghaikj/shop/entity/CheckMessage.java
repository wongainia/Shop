package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class CheckMessage implements Serializable {

    /*
    * success 是否成功
    * msg：失败时的消息
    * */

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
