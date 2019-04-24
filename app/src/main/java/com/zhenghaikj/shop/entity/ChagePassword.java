package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class ChagePassword implements Serializable {
    /*
    * "success":true,
    * "msg":"密码修改成功"
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
