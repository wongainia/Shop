package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class SendMessage implements Serializable {
    /*
    * success 是否成功
    * msg：失败时的消息
    * certificate：验证成功的凭证
    * */

    private boolean success;
    private String msg;
    private String certificate;

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

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }
}
