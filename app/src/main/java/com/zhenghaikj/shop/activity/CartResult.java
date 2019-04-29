package com.zhenghaikj.shop.activity;

import java.io.Serializable;

public class CartResult implements Serializable {
    private String Success;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }
}
