package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class ConfirmOrder implements Serializable {


    /**
     * Success : true
     */

    private String Success;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }
}
