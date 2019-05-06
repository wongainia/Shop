package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class UploadImgResult implements Serializable {

    private boolean Success;
    private String Src;
    private String Msg;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getSrc() {
        return Src;
    }

    public void setSrc(String src) {
        Src = src;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }
}
