package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class ChangeOrderAddress implements Serializable {

    /**
     * Success : true
     * msg : 修改成功
     */

    private boolean Success;
    private String msg;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
