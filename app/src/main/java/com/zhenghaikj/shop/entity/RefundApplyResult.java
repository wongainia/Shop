package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class RefundApplyResult implements Serializable {

//    { success = true, msg = "提交成功", id = info.Id }
//    或者
//    { success = false, msg = he.Message }
    private boolean success;
    private String msg;
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
