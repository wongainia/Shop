package com.zhenghaikj.shop.entity;

import com.chad.library.adapter.base.BaseViewHolder;

import java.io.Serializable;

public class StoreDetailGoodsEntity implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
