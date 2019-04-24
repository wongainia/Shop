package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class Logout implements Serializable {
    /*
    * Success = "true"
    * */

    private boolean Success;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }
}
