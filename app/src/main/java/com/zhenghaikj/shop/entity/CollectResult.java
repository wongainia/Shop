package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class CollectResult implements Serializable {
    private String productId;
    private String Userkey;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserkey() {
        return Userkey;
    }

    public void setUserkey(String userkey) {
        Userkey = userkey;
    }
}
