package com.zhenghaikj.shop.activity;

import java.io.Serializable;

public class CartResult implements Serializable {
    private String Success;
    private String Url;
    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
