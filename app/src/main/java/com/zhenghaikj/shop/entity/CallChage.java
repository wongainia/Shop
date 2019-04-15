package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class CallChage implements Serializable {
    private String money;
    private String price;

    public CallChage(String money, String price) {
        this.money = money;
        this.price = price;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
