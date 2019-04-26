package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class AddtoCartResult implements Serializable {
    private String Success;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }
}
