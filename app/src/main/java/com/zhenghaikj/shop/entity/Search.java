package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class Search implements Serializable {
    private String title;

    public Search(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
